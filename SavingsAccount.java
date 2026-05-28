public class SavingsAccount extends Account {
    private double annualInterestRate = 0.02; // 2% annual
    
    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }
    
    @Override
    public void calculateInterest() {
        double interest = balance * annualInterestRate / 12;
        balance += interest;
        addToHistory("Interest added: +$" + String.format("%.2f", interest));
    }
    
    @Override
    public String getAccountType() {
        return "Savings";
    }
}
