package com.acme.account;

import java.util.ArrayList;
import java.util.List;

/**
 * TransactionalAccount implementation that supports both cash and stock transactions
 * with complete transaction history tracking.
 */
public class TransactionalAccount implements Account {
	private double cashBalance;
	private int stockUnits;
	private static final double STOCK_PRICE = 5.0;
	private List<Transaction> transactionHistory;

	/**
	 * Constructor for creating a new TransactionalAccount.
	 * 
	 * @param initialCashBalance The initial cash balance for the account
	 */
	public TransactionalAccount(double initialCashBalance) {
		this.cashBalance = initialCashBalance;
		this.stockUnits = 0;
		this.transactionHistory = new ArrayList<>();
	}

	@Override
	public void deposit(double amount, TransactionType type) {
		if (type == TransactionType.CASH) {
			if (amount <= 0) {
				System.out.println("Deposit amount must be positive.");
				return;
			}
			cashBalance += amount;
			recordTransaction(TransactionType.CASH, amount, true);
			System.out.println("Deposited: $" + String.format("%.2f", amount));
		} else if (type == TransactionType.STOCK) {
			if (!isValidStockUnits(amount)) {
				System.out.println("Stock units must be positive integers.");
				return;
			}
			stockUnits += (int)amount;
			recordTransaction(TransactionType.STOCK, amount, true);
			System.out.println("Deposited: " + (int)amount + " units of ACME stock");
		} else {
			System.out.println("Only CASH and STOCK transactions are supported.");
		}
	}

	@Override
	public void withdraw(double amount, TransactionType type) {
		if (type == TransactionType.CASH) {
			if (amount <= 0) {
				System.out.println("Withdrawal amount must be positive.");
				return;
			}
			if (amount > cashBalance) {
				System.out.println("Insufficient balance.");
				return;
			}
			cashBalance -= amount;
			recordTransaction(TransactionType.CASH, amount, false);
			System.out.println("Withdrawn: $" + String.format("%.2f", amount));
		} else if (type == TransactionType.STOCK) {
			if (!isValidStockUnits(amount)) {
				System.out.println("Stock units must be positive integers.");
				return;
			}
			if ((int)amount > stockUnits) {
				System.out.println("Insufficient stock units.");
				return;
			}
			stockUnits -= (int)amount;
			recordTransaction(TransactionType.STOCK, amount, false);
			System.out.println("Withdrawn: " + (int)amount + " units of ACME stock");
		} else {
			System.out.println("Only CASH and STOCK transactions are supported.");
		}
	}

	@Override
	public double getBalance() {
		double stockBalance = stockUnits * STOCK_PRICE;
		double totalBalance = cashBalance + stockBalance;
		return totalBalance;
	}

	/**
	 * Get the current cash balance.
	 * 
	 * @return The cash balance
	 */
	public double getCashBalance() {
		return cashBalance;
	}

	/**
	 * Get the current stock units.
	 * 
	 * @return The number of stock units
	 */
	public int getStockUnits() {
		return stockUnits;
	}

	@Override
	public String getHistory() {
		if (transactionHistory.isEmpty()) {
			return "No transactions recorded.";
		}
		
		StringBuilder history = new StringBuilder();
		for (Transaction transaction : transactionHistory) {
			history.append(transaction.toString()).append("\n");
		}
		return history.toString();
	}

	/**
	 * Helper method to record a transaction in the transaction history.
	 * 
	 * @param type The transaction type (CASH or STOCK)
	 * @param amount The amount or units involved
	 * @param isDeposit True for deposits, false for withdrawals
	 */
	private void recordTransaction(TransactionType type, double amount, boolean isDeposit) {
		Transaction transaction = new Transaction(type, amount, isDeposit);
		transactionHistory.add(transaction);
	}

	/**
	 * Helper method to validate stock units.
	 * 
	 * @param amount The amount to validate
	 * @return True if amount is a positive integer, false otherwise
	 */
	private boolean isValidStockUnits(double amount) {
		if (amount <= 0) {
			return false;
		}
		if (amount != (int)amount) {
			return false;
		}
		return true;
	}

}
