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
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addToHistory("Deposit: +$" + String.format("%.2f", amount));
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addToHistory("Withdrawal: -$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }
    
    public boolean transfer(Account recipient, double amount) {
        if (this.withdraw(amount)) {
            recipient.deposit(amount);
            addToHistory("Transfer to " + recipient.accountHolder + ": -$" + String.format("%.2f", amount));
            recipient.addToHistory("Transfer from " + this.accountHolder + ": +$" + String.format("%.2f", amount));
            return true;
        }
        return false;
    }
    
    protected void addToHistory(String transaction) {
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
