package com.acme.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Transaction class.
 */
public class TransactionTest {

    @Test
    public void testConstructorSetsAllFieldsCorrectly() {
        long beforeTimestamp = System.currentTimeMillis();
        Transaction transaction = new Transaction(TransactionType.CASH, 100.0, true);
        long afterTimestamp = System.currentTimeMillis();
        
        assertEquals(TransactionType.CASH, transaction.getType());
        assertEquals(100.0, transaction.getAmount());
        assertTrue(transaction.isDeposit());
        assertTrue(transaction.getTimestamp() >= beforeTimestamp && 
                   transaction.getTimestamp() <= afterTimestamp);
    }

    @Test
    public void testToStringFormatsCashDepositsCorrectly() {
        Transaction transaction = new Transaction(TransactionType.CASH, 100.50, true);
        String result = transaction.toString();
        
        assertEquals("CASH Deposit: $100.50", result);
    }

    @Test
    public void testToStringFormatsCashWithdrawalsCorrectly() {
        Transaction transaction = new Transaction(TransactionType.CASH, 50.25, false);
        String result = transaction.toString();
        
        assertEquals("CASH Withdrawal: $50.25", result);
    }

    @Test
    public void testToStringFormatsStockDepositsCorrectly() {
        Transaction transaction = new Transaction(TransactionType.STOCK, 10.0, true);
        String result = transaction.toString();
        
        assertEquals("STOCK Deposit: 10 units", result);
    }

    @Test
    public void testToStringFormatsStockWithdrawalsCorrectly() {
        Transaction transaction = new Transaction(TransactionType.STOCK, 5.0, false);
        String result = transaction.toString();
        
        assertEquals("STOCK Withdrawal: 5 units", result);
    }

    @Test
    public void testTimestampIsAutomaticallySet() {
        long beforeTimestamp = System.currentTimeMillis();
        Transaction transaction = new Transaction(TransactionType.CASH, 100.0, true);
        long afterTimestamp = System.currentTimeMillis();
        
        assertTrue(transaction.getTimestamp() > 0);
        assertTrue(transaction.getTimestamp() >= beforeTimestamp);
        assertTrue(transaction.getTimestamp() <= afterTimestamp);
    }

    @Test
    public void testTimestampIsReasonableValue() {
        Transaction transaction = new Transaction(TransactionType.STOCK, 20.0, true);
        long currentTime = System.currentTimeMillis();
        
        // Timestamp should be within 1 second of current time
        assertTrue(Math.abs(transaction.getTimestamp() - currentTime) < 1000);
    }
}
