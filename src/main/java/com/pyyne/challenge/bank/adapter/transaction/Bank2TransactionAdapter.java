package com.pyyne.challenge.bank.adapter.transaction;

import com.bank2.integration.Bank2AccountSource;
import com.bank2.integration.Bank2AccountTransaction;
import com.pyyne.challenge.bank.domain.Transaction;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Bank2TransactionAdapter implements Transactionable {

    private final List<Bank2AccountTransaction> transactions;

    public Bank2TransactionAdapter(Bank2AccountSource source, long id, Date start, Date end) {
        this.transactions = source.getTransactions(id, start, end);
    }

    public List<Transaction> getTransactions() {
        return this.transactions.stream().map(this::convert).collect(Collectors.toList());
    }

    private Transaction convert(Bank2AccountTransaction bank2Transaction) {
        Transaction.TYPE type;
        if (bank2Transaction.getType() == Bank2AccountTransaction.TRANSACTION_TYPES.CREDIT) { type = Transaction.TYPE.INBOUND; }
        else type = Transaction.TYPE.OUTBOUND;
        return new Transaction(bank2Transaction.getAmount(), type, bank2Transaction.getText());
    }
}
