/**
 * Abstract base class representing a bank account with core functionality.
 * This class demonstrates inheritance and polymorphism principles in Java.
 * 
 * Provides core banking operations including deposit, withdrawal, transfer, 
 * and transaction history tracking with timestamps.
 * 
 * Subclasses must implement:
 * - calculateInterest(): Account-specific interest calculation logic
 * - getAccountType(): Returns the type of account
 */
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String accountHolder;
    protected java.util.List<String> history;
    
    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.history = new java.util.ArrayList<>();
        addToHistory("Account created with balance: $" + String.format("%.2f", initialBalance));
    }
    
    /**
     * Deposits money into the account.
     * Only processes positive amounts.
     * 
     * @param amount The amount to deposit (must be positive)
     */
    public void deposit(double amount) {
        // Validate deposit amount is positive
        if (amount > 0) {
            balance += amount;
            addToHistory("Deposit: +$" + String.format("%.2f", amount));
        }
    }
    
    /**
     * Withdraws money from the account.
     * Only allows withdrawal if amount is positive and sufficient funds exist.
     * 
     * @param amount The amount to withdraw
     * @return true if withdrawal was successful, false otherwise
     */
    public boolean withdraw(double amount) {
        // Verify amount is positive and account has sufficient funds
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addToHistory("Withdrawal: -$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }
    
    /**
     * Transfers money from this account to another account.
     * Ensures atomic operation: either both accounts are updated or neither.
     * 
     * @param recipient The account receiving the transfer
     * @param amount The amount to transfer
     * @return true if transfer was successful, false otherwise
     */
    public boolean transfer(Account recipient, double amount) {
        // Attempt withdrawal first; if successful, deposit to recipient
        if (this.withdraw(amount)) {
            recipient.deposit(amount);
            addToHistory("Transfer to " + recipient.accountHolder + ": -$" + String.format("%.2f", amount));
            recipient.addToHistory("Transfer from " + this.accountHolder + ": +$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }
    
    /**
     * Records a transaction with timestamp to account history.
     * All operations (deposit, withdrawal, transfer) are logged for audit trail.
     * 
     * @param transaction Description of the transaction
     */
    protected void addToHistory(String transaction) {
        // Format current timestamp and add to transaction history
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        history.add("[" + timestamp + "] " + transaction);
    }
    
    public abstract void calculateInterest();
    public abstract String getAccountType();
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public String getHistory() {
        StringBuilder sb = new StringBuilder();
        for (String entry : history) {
            sb.append(entry).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - Balance: $%.2f", accountHolder, getAccountType(), balance);
    }
}
