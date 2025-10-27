package com.acme.application;

import java.util.Scanner;
import com.acme.account.TransactionalAccount;
import com.acme.account.TransactionType;
import com.acme.account.Account;

public class UserInterface {
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double depositAmount;
        double withdrawAmount;

        // Create a new account with an initial balance of $1000
        TransactionalAccount account = new TransactionalAccount(1000.00);
        // Uncomment this to use the BasicAccount, the default implementation without transaction history or stock transactions
        // BasicAccount basicAccount = new BasicAccount(1000.00);

        while (true) {
            System.out.println("\n-- Acme Financial Menu --");
            System.out.println("1. Cash - Deposit");
            System.out.println("2. Cash - Withdraw");
            System.out.println("3. Stock - Deposit");
            System.out.println("4. Stock - Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Get History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Cash - Enter amount to deposit: ");
                    depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount, TransactionType.CASH);
                    break;

                case 2:
                    System.out.print("Cash - Enter amount to withdraw: ");
                    withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount, TransactionType.CASH);
                    break;
                    
                case 3:
                    System.out.print("Stock - Enter amount to deposit: ");
                    depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount, TransactionType.STOCK);
                    break;

                case 4:
                    System.out.print("Stock - Enter amount to withdraw: ");
                    withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount, TransactionType.STOCK);
                    break;

                case 5:
                    System.out.println("\n--- Account Balance ---");
                    System.out.println("Cash Balance: $" + String.format("%.2f", account.getCashBalance()));
                    double stockValue = account.getStockUnits() * 5.0;
                    System.out.println("Stock Balance: $" + String.format("%.2f", stockValue) + 
                                     " (" + account.getStockUnits() + " units @ $5.00)");
                    System.out.println("Total Balance: $" + String.format("%.2f", account.getBalance()));
                    break;
                    
                case 6:
                    System.out.print("Transaction History: \n: " + account.getHistory());
                    break;

                case 7:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please choose a valid option.");
            }
        }
    }
}