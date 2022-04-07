package com.pyyne.challenge.bank.service;

import com.bank1.integration.Bank1AccountSource;
import com.bank2.integration.Bank2AccountSource;
import com.pyyne.challenge.bank.adapter.transaction.Bank1TransactionAdapter;
import com.pyyne.challenge.bank.adapter.transaction.Bank2TransactionAdapter;
import com.pyyne.challenge.bank.domain.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The actual business logic starts here, in the services. If the task was more complex,
 * I would probably create a Domain layer and split Domain services - like returning balances -
 * from application services - adapting external APIs to fit the Domain services. Again,
 * I felt like I would risk over engineering the problem at hand, and opted for a simpler solution.
 */
public class TransactionService {

    private static final Date START = new Date(1649282433689L);
    private static final Date END = new Date(1649285544790L);

    public List<Transaction> getAllTransactions(long id) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.addAll(new Bank1TransactionAdapter(new Bank1AccountSource(), id, START, END).getTransactions());
        transactions.addAll(new Bank2TransactionAdapter(new Bank2AccountSource(), id, START, END).getTransactions());
        return transactions;
    }
}
