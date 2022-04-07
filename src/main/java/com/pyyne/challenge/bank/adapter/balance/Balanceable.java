package com.pyyne.challenge.bank.adapter.balance;

import com.pyyne.challenge.bank.domain.Balance;

/**
 * About the name: "Balanceable" is not a word, but conveys the meaning of something
 * which is able to return a Balance - and it's still intelligible.
 */
public interface Balanceable {
    Balance getBalance();
}
