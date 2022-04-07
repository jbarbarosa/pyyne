package com.pyyne.challenge.bank.adapter.balance;

import com.bank2.integration.Bank2AccountSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bank2BalanceAdapterTest {
    private static final long ID = 1;
    private Bank2AccountSource source;

    @BeforeEach
    public void setup() {
        this.source = new Bank2AccountSource();
    }

    @Test
    public void ShouldConvertCurrencyIntoBalanceable() {
        var adapter = new Bank2BalanceAdapter(ID, this.source);
        assertEquals(adapter.getBalance().getCurrency(), this.source.getBalance(ID).getCurrency());
    }

    @Test
    public void ShouldConvertBalanceIntoBalanceable() {
        var adapter = new Bank2BalanceAdapter(ID, this.source);
        assertEquals(adapter.getBalance().getAmount(), this.source.getBalance(ID).getBalance());
    }
}
