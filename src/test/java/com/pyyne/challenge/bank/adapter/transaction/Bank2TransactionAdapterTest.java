package com.pyyne.challenge.bank.adapter.transaction;

import com.bank2.integration.Bank2AccountSource;
import com.bank2.integration.Bank2AccountTransaction;
import com.pyyne.challenge.bank.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bank2TransactionAdapterTest {
    private static final long ID = 1;
    private static final Date START = new Date(1649282433689L);
    private static final Date END = new Date(1649285544790L);
    private Bank2AccountSource source;
    private Bank2TransactionAdapter adapter;

    @BeforeEach
    public void setup() {
        this.source = new Bank2AccountSource();
        this.adapter = new Bank2TransactionAdapter(this.source, ID, START, END);
    }

    @Test
    public void shouldHaveSameAmountOfTransactions() {
        assertEquals(adapter.getTransactions().size(), this.source.getTransactions(ID, START, END).size());
    }

    @Test
    public void shouldConvertCreditTransactionTypes() {
        var b2CreditTransaction = this.source.getTransactions(ID, START, END)
                .stream()
                .filter(bank2Transaction -> bank2Transaction.getType() == Bank2AccountTransaction.TRANSACTION_TYPES.CREDIT)
                .toList()
                .get(0);
        var adaptedCreditTransaction = this.adapter.getTransactions()
                .stream()
                .filter(transaction -> transaction.getType() == Transaction.TYPE.INBOUND)
                .toList()
                .get(0);
        assertEquals(b2CreditTransaction.getText(), adaptedCreditTransaction.getSource());
    }

    @Test
    public void shouldConvertDebitTransactionTypes() {
        var b2CreditTransaction = this.source.getTransactions(ID, START, END)
                .stream()
                .filter(bank2Transaction -> bank2Transaction.getType() == Bank2AccountTransaction.TRANSACTION_TYPES.DEBIT)
                .toList()
                .get(0);
        var adaptedCreditTransaction = this.adapter.getTransactions()
                .stream()
                .filter(transaction -> transaction.getType() == Transaction.TYPE.OUTBOUND)
                .toList()
                .get(0);
        assertEquals(b2CreditTransaction.getText(), adaptedCreditTransaction.getSource());
    }
}
