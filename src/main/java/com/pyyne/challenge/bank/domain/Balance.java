package com.pyyne.challenge.bank.domain;

/**
 * Balance and Transaction are both part of the Domain of a bank.
 * Neither should know about external adapters and so on, because those
 * are implementation details.
 */
public class Balance {

    private final double amount;
    private final String currency;

    public Balance(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
