package com.ram.penny.domain.account

import com.ram.penny.domain.money.Money
import spock.lang.Specification

class AccountSpec extends Specification {

    def "withdrawing more then the balance is rejected and the balance is unchanged"() {
        given:
        def account = new Account(Money.ofCents(50))

        when:
        account.withDraw(Money.ofCents(80))

        then:
        thrown(IllegalArgumentException)
        account.balance() == Money.ofCents(50)
    }
}
