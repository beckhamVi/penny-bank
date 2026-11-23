package com.ram.penny.domain.money

class MoneySpec {

    def "sumar 2 montos da un centavo"() {
        expect:
        Money.ofCents(10).plus(Money.ofCents(20).cents() == 30)
    }
}

