-- Clientes del banco
CREATE TABLE customers (
    id          BIGSERIAL    PRIMARY KEY,
    full_name   VARCHAR(120) NOT NULL,
    email       VARCHAR(160) NOT NULL UNIQUE,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now()
);

-- Cuentas de cada cliente. No guardamos saldo: se calcula desde ledger_entries.
CREATE TABLE accounts (
    id           BIGSERIAL   PRIMARY KEY,
    customer_id  BIGINT      NOT NULL REFERENCES customers(id),
    currency     CHAR(3)     NOT NULL DEFAULT 'USD',
    status       VARCHAR(10) NOT NULL DEFAULT 'ACTIVE'
                 CHECK (status IN ('ACTIVE', 'FROZEN', 'CLOSED')),
    created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Una operación completa (depósito, retiro, transferencia o reverso)
CREATE TABLE transfers (
    id           UUID         PRIMARY KEY,
    kind         VARCHAR(20)  NOT NULL
                 CHECK (kind IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER', 'REVERSAL')),
    description  VARCHAR(255),
    created_at   TIMESTAMPTZ  NOT NULL DEFAULT now()
);

-- Libro contable: asientos inmutables. Solo INSERT, nunca UPDATE ni DELETE.
CREATE TABLE ledger_entries (
    id            BIGSERIAL    PRIMARY KEY,
    transfer_id   UUID         NOT NULL REFERENCES transfers(id),
    account_id    BIGINT       NOT NULL REFERENCES accounts(id),
    direction     VARCHAR(6)   NOT NULL CHECK (direction IN ('DEBIT', 'CREDIT')),
    amount_cents  BIGINT       NOT NULL CHECK (amount_cents > 0),
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE INDEX idx_ledger_account  ON ledger_entries(account_id, id);
CREATE INDEX idx_ledger_transfer ON ledger_entries(transfer_id);

-- Respaldo duradero de idempotencia (Redis es la capa rápida)
CREATE TABLE idempotency_keys (
    idempotency_key  VARCHAR(100) PRIMARY KEY,
    transfer_id      UUID         REFERENCES transfers(id),
    request_hash     VARCHAR(64)  NOT NULL,
    created_at       TIMESTAMPTZ  NOT NULL DEFAULT now()
);