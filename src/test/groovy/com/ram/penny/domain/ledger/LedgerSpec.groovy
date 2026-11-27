package com.ram.penny.domain.ledger

import com.ram.penny.domain.account.Account
import com.ram.penny.domain.money.Money
import spock.lang.Specification

class LedgerSpec extends Specification{

    def "balance is the sum of credits minus debits"() {
        given:
        def entries = [
                new LedgerEntry(Direction.CREDIT, Money.ofCents(credit)),
                new LedgerEntry(Direction.DEBIT, Money.ofCents(debit))
        ]

        expect:
        Ledger.balance(entries) == Money.ofCents(expected);

        where:
        credit | debit | expected
        100    | 30    | 70
        50     | 0     | 50
        80     | 80    | 0
    }
}
