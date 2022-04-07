package com.pyyne.challenge.bank.adapter.balance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bank1.integration.Bank1AccountSource;

public class Bank1BalanceAdapterTest {
    private static final long ID = 1;
    private Bank1AccountSource source;

    @BeforeEach
    public void setup() {
        this.source = new Bank1AccountSource();
    }

    @Test
    public void ShouldConvertCurrencyIntoBalanceable() {
        var adapter = new Bank1BalanceAdapter(ID, this.source);
        assertEquals(adapter.getBalance().getCurrency(), this.source.getAccountCurrency(ID));
    }

    @Test
    public void ShouldConvertBalanceIntoBalanceable() {
        var adapter = new Bank1BalanceAdapter(ID, this.source);
        assertEquals(adapter.getBalance().getAmount(), this.source.getAccountBalance(ID));
    }
}
