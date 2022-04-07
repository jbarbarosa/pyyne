package com.pyyne.challenge.bank;

import com.pyyne.challenge.bank.service.BalanceService;
import com.pyyne.challenge.bank.service.TransactionService;

/**
 * The controller would, in the real world, be responsible for calling the APIs and
 * sending their input into the services. However, I felt like mocking that operation
 * would result in too much boilerplate. Instead, I simply call the "APIs" from the services.
 */
/**
 * Controller that pulls information form multiple bank integrations and prints them to the console.
 *
 * Created by Par Renyard on 5/12/21.
 */
public class BankController {

    private final BalanceService balanceService;
    private final TransactionService transactionService;
    //The ID was mocked due to it being useless in the "APIs" calls anyway
    private static final long ID = 1;

    public BankController(BalanceService balanceService, TransactionService transactionService) {
        this.balanceService = balanceService;
        this.transactionService = transactionService;
    }

    public void printBalances() {
        this.balanceService
                .getAllBalances(ID)
                .forEach(balance -> System.out.printf("currency: %s, amount: %.2f\n",
                         balance.getCurrency(),
                         balance.getAmount()));
    }

    public void printTransactions() {
        this.transactionService
                .getAllTransactions(ID)
                .forEach(transaction ->
                        System.out.printf("type: %s, source: %s, amount: %.2f\n",
                                transaction.getType(),
                                transaction.getSource(),
                                transaction.getAmount()));
    }
}
