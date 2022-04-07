package com.pyyne.challenge.bank.adapter.transaction;

import com.bank1.integration.Bank1AccountSource;
import com.pyyne.challenge.bank.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;

public class Bank1TransactionAdapterTest {
    private static final long ID = 1;
    private static final Date START = new Date(1649282433689L);
    private static final Date END = new Date(1649285544790L);
    private Bank1AccountSource source;
    private Bank1TransactionAdapter adapter;

    @BeforeEach
    public void setup() {
        this.source = new Bank1AccountSource();
        this.adapter = new Bank1TransactionAdapter(this.source, ID, START, END);
    }

    @Test
    public void shouldHaveSameAmountOfTransactions() {
        assertEquals(adapter.getTransactions().size(), this.source.getTransactions(ID, START, END).size());
    }

    @Test
    public void shouldConvertCreditTransactionTypes() {
        var b1CreditTransaction = this.source.getTransactions(ID, START, END)
                .stream()
                .filter(bank1Transaction -> bank1Transaction.getType() == 1)
                .toList()
                .get(0);
        var adaptedCreditTransaction = this.adapter.getTransactions()
                .stream()
                .filter(transaction -> transaction.getType() == Transaction.TYPE.INBOUND)
                .toList()
                .get(0);
        assertEquals(b1CreditTransaction.getText(), adaptedCreditTransaction.getSource());
    }

    @Test
    public void shouldConvertDebitTransactionTypes() {
        var b1CreditTransaction = this.source.getTransactions(ID, START, END)
                .stream()
                .filter(bank1Transaction -> bank1Transaction.getType() == 2)
                .toList()
                .get(0);
        var adaptedCreditTransaction = this.adapter.getTransactions()
                .stream()
                .filter(transaction -> transaction.getType() == Transaction.TYPE.OUTBOUND)
                .toList()
                .get(0);
        assertEquals(b1CreditTransaction.getText(), adaptedCreditTransaction.getSource());
    }
}
