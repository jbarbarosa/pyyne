package com.pyyne.challenge.bank.domain;

/**
 * Balance and Transaction are both part of the Domain of a bank.
 * Neither should know about external adapters and so on, because those
 * are implementation details.
 */
public class Transaction {

    private final double amount;
    private final TYPE type;
    private final String source;

    public Transaction(double amount, TYPE type, String source) {
        this.amount = amount;
        this.type = type;
        this.source = source;
    }

    public enum TYPE {
        INBOUND, OUTBOUND
    }

    public double getAmount() {
        return amount;
    }

    public TYPE getType() {
        return type;
    }

    public String getSource() {
        return source;
    }
}
