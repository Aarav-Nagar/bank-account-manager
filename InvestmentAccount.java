public class InvestmentAccount extends Account {
    private double annualInterestRate = 0.05; // 5% annual
    
    public InvestmentAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }
    
    @Override
    public void calculateInterest() {
        if (balance >= 1000) { // Minimum balance requirement
            double interest = balance * annualInterestRate / 12;
            balance += interest;
            addToHistory("Investment interest added: +$" + String.format("%.2f", interest));
        } else {
            addToHistory("Minimum balance of $1000 required for interest");
        }
    }
    
    @Override
    public String getAccountType() {
        return "Investment";
    }
}
