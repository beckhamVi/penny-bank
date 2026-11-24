package com.ram.penny.domain.account;

import com.ram.penny.domain.money.Money;

public class Account {

    private long balanceCents;

    public Account(Money initialBalance) {
        this.balanceCents = initialBalance.cents();
    }

    public Money balance() {
        return Money.ofCents(balanceCents);
    }

    public void withDraw(Money amount) {
        if (amount.cents() > balanceCents) {
            throw new IllegalArgumentException("Insufficient funds!");
        }
        balanceCents -= amount.cents();
    }
}
