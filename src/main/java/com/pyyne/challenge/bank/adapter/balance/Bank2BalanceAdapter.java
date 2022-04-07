package com.pyyne.challenge.bank.adapter.balance;

import com.bank2.integration.Bank2AccountSource;
import com.pyyne.challenge.bank.domain.Balance;

public class Bank2BalanceAdapter implements Balanceable {

    private final String currency;
    private final double amount;

    public Bank2BalanceAdapter(long id, Bank2AccountSource source) {
        this.currency = source.getBalance(id).getCurrency();
        this.amount = source.getBalance(id).getBalance();
    }

    public Balance getBalance() {
        return new Balance(this.amount, this.currency);
    }
}
