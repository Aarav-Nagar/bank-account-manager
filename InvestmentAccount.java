/**
 * Investment Account implementation extending the Account base class.
 * 
 * Characteristics:
 * - Earns 5% annual interest (calculated monthly as 0.05/12)
 * - Requires minimum balance of $1000 to earn interest
 * - Higher return ideal for investors with substantial capital
 * - Premium account with stricter requirements
 */
public class InvestmentAccount extends Account {
    private double annualInterestRate = 0.05; // 5% annual
    
    public InvestmentAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }
    
    /**
     * Calculates and applies monthly investment interest.
     * Premium account that requires minimum balance of $1000 to earn interest.
     * Highest interest rate (5% annual) among all account types.
     * 
     * Interest calculation:
     * If Balance >= $1000: Monthly Interest = Balance × (0.05 / 12)
     * Otherwise: No interest is credited
     */
    @Override
    public void calculateInterest() {
        // Investment accounts require $1000 minimum balance to qualify for interest
        if (balance >= 1000) {
            double interest = balance * annualInterestRate / 12;
            balance += interest;
            addToHistory("Investment interest added: +$" + String.format("%.2f", interest));
        } else {
            // Notify customer of minimum balance requirement
            addToHistory("Minimum balance of $1000 required for interest");
        }
    }
    
    @Override
    public String getAccountType() {
        return "Investment";
    }
}
