package com.ram.penny.domain.ledger;

import com.ram.penny.domain.money.Money;

import java.util.List;

public final class Ledger {
    private Ledger() {}

    public static Money balance(List<LedgerEntry> entries) {
        long total = 0;
        for (LedgerEntry entry : entries) {
            if (entry.direction() == Direction.CREDIT){
                total += entry.amount().cents();
            } else {
                total -= entry.amount().cents();
            }
        }
        return Money.ofCents(total);
    }
}
