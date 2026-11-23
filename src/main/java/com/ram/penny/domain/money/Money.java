package com.ram.penny.domain.money;

public record Money(long cents) {
    public static Money ofCents(long cents) {
        return new Money(cents);
    }

    public Money plus(Money other) {
        return new Money(this.cents + other.cents);
    }
}
