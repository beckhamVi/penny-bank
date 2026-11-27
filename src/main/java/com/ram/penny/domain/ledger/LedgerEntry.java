package com.ram.penny.domain.ledger;

import com.ram.penny.domain.money.Money;

public record LedgerEntry(Direction direction, Money amount) {
}
