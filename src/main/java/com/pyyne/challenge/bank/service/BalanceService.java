package com.pyyne.challenge.bank.service;

import com.bank1.integration.Bank1AccountSource;
import com.bank2.integration.Bank2AccountSource;
import com.pyyne.challenge.bank.domain.Balance;
import com.pyyne.challenge.bank.adapter.balance.Bank1BalanceAdapter;
import com.pyyne.challenge.bank.adapter.balance.Bank2BalanceAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * The actual business logic starts here, in the services. If the task was more complex,
 * I would probably create a Domain layer and split Domain services - like returning balances -
 * from application services - adapting external APIs to fit the Domain services. Again,
 * I felt like I would risk over engineering the problem at hand, and opted for a simpler solution.
 */
public class BalanceService {
    public List<Balance> getAllBalances(long id) {
        ArrayList<Balance> balances = new ArrayList<>();
        balances.add(new Bank1BalanceAdapter(id, new Bank1AccountSource()).getBalance());
        balances.add(new Bank2BalanceAdapter(id, new Bank2AccountSource()).getBalance());
        return balances;
    }
}
