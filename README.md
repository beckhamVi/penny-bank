# Penny Bank

Mini core banking API: double-entry ledger, atomic transfers, idempotent payments and concurrency-safe balances.

## Stack

Java 21 · Spring Boot 4 · PostgreSQL 18 · Redis 7 · jOOQ · Flyway · Testcontainers · Docker

## Run locally

Requirements: Docker Desktop and Java 21.

```bash
docker compose up -d
./gradlew bootRun
```

Spring Boot starts Postgres and Redis automatically through `compose.yaml`. Health check:

```
http://localhost:8080/actuator/health
```

## Core ideas

- The ledger is append-only: balances are never updated, they are summed.
- Every transfer produces entries that add up to zero.
- Money is stored as integer cents, never floating point.

## Roadmap

- [x] Project setup with Docker Compose and profiles
- [ ] Database schema (Flyway)
- [ ] Money value object (TDD)
- [ ] Ledger rules (TDD)
- [ ] Accounts and deposits
- [ ] Atomic transfers
- [ ] Concurrency-safe withdrawals
- [ ] Idempotency with Redis
- [ ] Kubernetes deployment