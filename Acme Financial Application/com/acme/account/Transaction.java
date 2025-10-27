package com.acme.account;

/**
 * Immutable class representing a single transaction record.
 * Stores transaction type, amount, direction (deposit/withdrawal), and timestamp.
 */
public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final boolean isDeposit;
    private final long timestamp;

    /**
     * Constructor for creating a new transaction.
     * 
     * @param type The type of transaction (CASH or STOCK)
     * @param amount The amount for cash or number of units for stock
     * @param isDeposit True for deposits, false for withdrawals
     */
    public Transaction(TransactionType type, double amount, boolean isDeposit) {
        this.type = type;
        this.amount = amount;
        this.isDeposit = isDeposit;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * @return The transaction type
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * @return The transaction amount or units
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return True if deposit, false if withdrawal
     */
    public boolean isDeposit() {
        return isDeposit;
    }

    /**
     * @return The timestamp when transaction was created
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a formatted string representation of the transaction.
     * Format: "CASH/STOCK Deposit/Withdrawal: $amount or X units"
     * 
     * @return Formatted transaction string
     */
    @Override
    public String toString() {
        String direction = isDeposit ? "Deposit" : "Withdrawal";
        
        if (type == TransactionType.CASH) {
            return type + " " + direction + ": $" + String.format("%.2f", amount);
        } else if (type == TransactionType.STOCK) {
            return type + " " + direction + ": " + (int)amount + " units";
        }
        
        return type + " " + direction + ": " + amount;
    }
}
