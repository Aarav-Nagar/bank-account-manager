/**
 * Savings Account implementation extending the Account base class.
 * 
 * Characteristics:
 * - Earns 2% annual interest (calculated monthly as 0.02/12)
 * - No monthly fees
 * - Ideal for long-term savings with regular compound interest
 */
public class SavingsAccount extends Account {
    private double annualInterestRate = 0.02; // 2% annual
    
    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }
    
    /**
     * Calculates and applies monthly interest to the savings account.
     * Uses simple monthly interest calculation: (balance * annual rate / 12 months)
     * 
     * Interest calculation:
     * Monthly Interest = Balance × (0.02 / 12)
     */
    @Override
    public void calculateInterest() {
        // Calculate monthly interest as 1/12 of annual rate
        double interest = balance * annualInterestRate / 12;
        balance += interest;
        addToHistory("Interest added: +$" + String.format("%.2f", interest));
    }
    
    @Override
    public String getAccountType() {
        return "Savings";
    }
}
