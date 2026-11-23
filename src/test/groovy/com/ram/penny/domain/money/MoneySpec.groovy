package com.ram.penny.domain.money

import spock.lang.Specification

class MoneySpec extends Specification {

    def "adding two amounts gives the exact sum in cents"() {
        expect:
        Money.ofCents(10).plus(Money.ofCents(20)).cents() == 30
    }

    def "creating a negative amount throws an exception"() {
        when:
        Money.ofCents(-5)

        then:
        thrown(IllegalArgumentException)
    }

    def "zero is valid amount"() {
        expect:
        Money.ofCents(0).cents() == 0
    }

    def "adding does not change the original amounts"() {
        given:
        def a = Money.ofCents(10)
        def b = Money.ofCents(20)

        when:
        a.plus(b)

        then:
        a.cents() == 10
        b.cents() == 20
    }

    def "two money values with the same cents are equal"() {
        expect:
        Money.ofCents(10) == Money.ofCents(10)
    }

    def "adding two zeros gives zero"() {
        expect:
        Money.ofCents(0).plus(Money.ofCents(0)).cents() == 0
    }

    def "adding beyond the maximun value throws an exception"() {
        when:
        Money.ofCents(Long.MAX_VALUE).plus(Money.ofCents(1))

        then:
        thrown(ArithmeticException)
    }
}