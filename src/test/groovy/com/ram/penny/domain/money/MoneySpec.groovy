package com.ram.penny.domain.money

import spock.lang.Specification

class MoneySpec extends Specification{
    def "sumar 2 montos da un total en centavos"() {
        expect:
        Money.ofCents(10).plus(Money.ofCents(20)).cents() == 30
    }
}
