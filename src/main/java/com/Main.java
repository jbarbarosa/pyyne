package com;

import com.pyyne.challenge.bank.BankController;
import com.pyyne.challenge.bank.service.BalanceService;
import com.pyyne.challenge.bank.service.TransactionService;

/**
 * I interspersed comments around the code, which I normally do not do because,
 * as the great Uncle Bob would say (paraphrasing): "if you need to comment to make
 * your code clear, you need to improve your code". However, due to this being a test
 * primarily about my design choices, I felt like I should make them clear for the reader.
 */
public class Main {
    /**
     * First of all: the main function. Here I place no logic. Again citing Uncle Bob
     * in his Clean Code: the main function is the dirtiest function in your code, it knows
     * all about you program already, so at least hide all that logic somewhere.
     */
    public static void main(String[] args) {
        /**
         * In a framework such as Spring, Dependency Injection would be handled by the runtime.
         * Here, I am doing it manually, but would leave that to the framework were it available.
         * Of course, DI here is necessary to keep the implementation details where they belong - in
         * their respective services - not in the controller.
         */
        BankController bank = new BankController(new BalanceService(), new TransactionService());
        bank.printBalances();
        bank.printTransactions();
    }
}
