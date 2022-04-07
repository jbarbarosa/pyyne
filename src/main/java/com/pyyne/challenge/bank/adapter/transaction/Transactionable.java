package com.pyyne.challenge.bank.adapter.transaction;

import com.pyyne.challenge.bank.domain.Transaction;

import java.util.List;

/**
 * About the name: "Transactionable" is not a word, but conveys the meaning of something
 * which is able to return a Balance - and it's still intelligible.
 */
public interface Transactionable {
    List<Transaction> getTransactions();
}
