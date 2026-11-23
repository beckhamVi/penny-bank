package com.ram.penny.domain.money;

public record Money(long cents) {

    public static Money ofCents(long cents) {
        validateNegative(cents);
        return new Money(cents);
    }

    public Money plus(Money other) {
        return ofCents(Math.addExact(this.cents, other.cents));
    }

    private static void validateNegative(long cents) {
        if (cents < 0) {
            throw new IllegalArgumentException("Cents cannot be negative");
        }
    }
}