Requirements Document: Cash and Stock Transactions in an Account
1. Introduction
Acme Financial Services is expanding its transactional accounts application! The system will now track and manage cash and stock holdings within an account, ensuring accuracy in asset valuation, categorization, and transaction history.
2. Existing Functionality
In the current iteration of this application, only cash transactions are supported. In addition, only the current balance is tracked, and therefore, no transaction history is available. 

**Implementation:** The `BasicAccount` class provides the original functionality with cash-only transactions and no history tracking. This serves as a baseline implementation that demonstrates the original system capabilities. 
3. Transaction History 
When option "6. Get History" is selected from the Acme Financial Menu, all deposits and withdrawals initiated within this session will be listed in the order that they were entered. 

**Implementation:** The `TransactionalAccount` class maintains an `ArrayList<Transaction>` to store all transaction records. Each transaction is automatically recorded with timestamp, type, amount, and direction (deposit/withdrawal). The `getHistory()` method formats and returns the complete chronological transaction list. 
 
4. Stocks
Stocks are a new type of asset to be implemented. They are tracked using a price and the number of units held. All deposits and withdraw transactions will be done with the number of units specified, and it must be a discrete integer. For this iteration of Acme Financial Services application, all stocks deposited and withdrawn will be defaulted to the stock ACME, where the price is always $5 per share. 

**Implementation:** Stock transactions are handled through the `TransactionType.STOCK` enum value. The `TransactionalAccount` class validates that stock amounts are positive integers using `isValidStockUnits()`. Stock units are tracked separately from cash balance, with ACME stock price fixed at $5.00 per share as a constant. 
 
5. Account Balance
When option "5. Check Balance" is selected from the Acme Financial Menu, the application will calculate the account balance as follows:

Cash Balance = Total Cash Deposits – Total Cash Withdraws

Stock Balance = (Total Stock Deposits – Total Stock Withdraws) * Price

Total Balance = Cash Balance + Stock Balance 

**Implementation:** The `getBalance()` method calculates total balance by combining cash balance with stock value (stock units × $5.00). The `UserInterface` displays detailed breakdown showing cash balance, stock balance with units, and total balance. Separate getter methods `getCashBalance()` and `getStockUnits()` provide granular access to individual components. 
 
7. Out of Scope
•	Implementation of specific stocks other than the default.
•	Implementation of any transaction type other than cash and stock.
•	Saving/persistence of data to a file or database. Information is reset when the application is closed. 

**Implementation:** The `TransactionType` enum includes `MUTUALFUND` and `BOND` types but they are explicitly rejected in `TransactionalAccount` methods. No file I/O or database connectivity is implemented - all data exists only in memory during application execution. The system gracefully handles unsupported transaction types with appropriate error messages. 
