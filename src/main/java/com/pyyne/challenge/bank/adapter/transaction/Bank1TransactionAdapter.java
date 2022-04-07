package com.pyyne.challenge.bank.adapter.transaction;

import com.bank1.integration.Bank1AccountSource;
import com.bank1.integration.Bank1Transaction;
import com.pyyne.challenge.bank.domain.Transaction;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The core of the challenge here. I could implement the Adapter pattern either through
 * inheritance or through composition. I opted for composition, using an interface in this case,
 * as I do most of the time for a few reasons. First because I am coding a lot in Go these last
 * few months, and Go does not have inheritance. Second, inheritance couples too tightly a class
 * to it's parent, making changes difficult in the long run.
 */
public class Bank1TransactionAdapter implements Transactionable {

    private final List<Bank1Transaction> transactions;

    public Bank1TransactionAdapter(Bank1AccountSource source, long id, Date start, Date end) {
        this.transactions = source.getTransactions(id, start, end);
    }

    public List<Transaction> getTransactions() {
        return this.transactions.stream().map(this::convert).collect(Collectors.toList());
    }

    private Transaction convert(Bank1Transaction bank1Transaction) {
        Transaction.TYPE type;
        if (bank1Transaction.getType() == 1) { type = Transaction.TYPE.INBOUND; }
        else type = Transaction.TYPE.OUTBOUND;
        return new Transaction(bank1Transaction.getAmount(), type, bank1Transaction.getText());
    }
}
