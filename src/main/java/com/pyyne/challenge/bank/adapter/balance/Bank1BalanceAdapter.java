package com.pyyne.challenge.bank.adapter.balance;

import com.bank1.integration.Bank1AccountSource;
import com.pyyne.challenge.bank.domain.Balance;

/**
 * The core of the challenge here. I could implement the Adapter pattern either through
 * inheritance or through composition. I opted for composition, using an interface in this case,
 * as I do most of the time for a few reasons. First because I am coding a lot in Go these last
 * few months, and Go does not have inheritance. Second, inheritance couples too tightly a class
 * to it's parent, making changes difficult in the long run.
 */
public class Bank1BalanceAdapter implements Balanceable {

    private final String currency;
    private final double amount;

    public Bank1BalanceAdapter(long id, Bank1AccountSource source) {
        this.currency = source.getAccountCurrency(id);
        this.amount = source.getAccountBalance(id);
    }

    public Balance getBalance() {
        return new Balance(this.amount, this.currency);
    }
}
