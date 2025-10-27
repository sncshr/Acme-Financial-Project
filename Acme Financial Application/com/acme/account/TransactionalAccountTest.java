package com.acme.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TransactionalAccount class.
 */
public class TransactionalAccountTest {
    private TransactionalAccount account;

    @BeforeEach
    public void setUp() {
        account = new TransactionalAccount(1000.0);
    }

    @Test
    public void testCashDepositIncreasesBalanceCorrectly() {
        account.deposit(100.0, TransactionType.CASH);
        assertEquals(1100.0, account.getCashBalance(), 0.01);
        assertEquals(1100.0, account.getBalance(), 0.01);
    }

    @Test
    public void testCashDepositRejectsZeroAmount() {
        account.deposit(0.0, TransactionType.CASH);
        assertEquals(1000.0, account.getCashBalance(), 0.01);
    }

    @Test
    public void testCashDepositRejectsNegativeAmount() {
        account.deposit(-50.0, TransactionType.CASH);
        assertEquals(1000.0, account.getCashBalance(), 0.01);
    }

    @Test
    public void testCashWithdrawalDecreasesBalanceCorrectly() {
        account.withdraw(200.0, TransactionType.CASH);
        assertEquals(800.0, account.getCashBalance(), 0.01);
        assertEquals(800.0, account.getBalance(), 0.01);
    }

    @Test
    public void testCashWithdrawalRejectsAmountExceedingBalance() {
        account.withdraw(1500.0, TransactionType.CASH);
        assertEquals(1000.0, account.getCashBalance(), 0.01);
    }

    @Test
    public void testStockDepositIncreasesStockUnitsCorrectly() {
        account.deposit(10.0, TransactionType.STOCK);
        assertEquals(10, account.getStockUnits());
        assertEquals(1050.0, account.getBalance(), 0.01); // 1000 cash + 10*5 stock
    }

    @Test
    public void testStockDepositRejectsNonIntegerAmounts() {
        account.deposit(10.5, TransactionType.STOCK);
        assertEquals(0, account.getStockUnits());
    }

    @Test
    public void testStockWithdrawalDecreasesStockUnitsCorrectly() {
        account.deposit(20.0, TransactionType.STOCK);
        account.withdraw(5.0, TransactionType.STOCK);
        assertEquals(15, account.getStockUnits());
        assertEquals(1075.0, account.getBalance(), 0.01); // 1000 cash + 15*5 stock
    }

    @Test
    public void testStockWithdrawalRejectsAmountExceedingAvailableUnits() {
        account.deposit(10.0, TransactionType.STOCK);
        account.withdraw(15.0, TransactionType.STOCK);
        assertEquals(10, account.getStockUnits());
    }

    @Test
    public void testGetBalanceCalculatesTotalCorrectlyWithCashOnly() {
        account.deposit(500.0, TransactionType.CASH);
        assertEquals(1500.0, account.getBalance(), 0.01);
    }

    @Test
    public void testGetBalanceCalculatesTotalCorrectlyWithStocksOnly() {
        TransactionalAccount newAccount = new TransactionalAccount(0.0);
        newAccount.deposit(20.0, TransactionType.STOCK);
        assertEquals(100.0, newAccount.getBalance(), 0.01); // 20 * 5.0
    }

    @Test
    public void testGetBalanceCalculatesTotalCorrectlyWithBothCashAndStocks() {
        account.deposit(200.0, TransactionType.CASH);
        account.deposit(10.0, TransactionType.STOCK);
        assertEquals(1250.0, account.getBalance(), 0.01); // 1200 cash + 50 stock
    }

    @Test
    public void testGetHistoryReturnsEmptyMessageWhenNoTransactions() {
        TransactionalAccount newAccount = new TransactionalAccount(1000.0);
        String history = newAccount.getHistory();
        assertEquals("No transactions recorded.", history);
    }

    @Test
    public void testGetHistoryShowsTransactionsInChronologicalOrder() {
        account.deposit(100.0, TransactionType.CASH);
        account.withdraw(50.0, TransactionType.CASH);
        account.deposit(10.0, TransactionType.STOCK);
        
        String history = account.getHistory();
        assertTrue(history.contains("CASH Deposit: $100.00"));
        assertTrue(history.contains("CASH Withdrawal: $50.00"));
        assertTrue(history.contains("STOCK Deposit: 10 units"));
        
        // Check order
        int depositIndex = history.indexOf("CASH Deposit");
        int withdrawalIndex = history.indexOf("CASH Withdrawal");
        int stockIndex = history.indexOf("STOCK Deposit");
        assertTrue(depositIndex < withdrawalIndex);
        assertTrue(withdrawalIndex < stockIndex);
    }

    @Test
    public void testUnsupportedTransactionTypesDisplayErrorMessages() {
        account.deposit(100.0, TransactionType.MUTUALFUND);
        account.withdraw(50.0, TransactionType.BOND);
        
        // Balance should remain unchanged
        assertEquals(1000.0, account.getCashBalance(), 0.01);
        assertEquals(0, account.getStockUnits());
    }
}
