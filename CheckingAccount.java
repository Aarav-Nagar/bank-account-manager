/**
 * Checking Account implementation extending the Account base class.
 * 
 * Characteristics:
 * - Charges a monthly fee of $5.00
 * - Does not earn interest
 * - Ideal for frequent transactions and everyday banking
 */
public class CheckingAccount extends Account {
    private double monthlyFee = 5.0;
    
    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }
    
    /**
     * Applies monthly maintenance fee to the checking account.
     * Checking accounts do not earn interest but are charged a monthly fee.
     * Fee is only deducted if sufficient balance exists.
     */
    @Override
    public void calculateInterest() {
        // Checking accounts don't earn interest, but apply monthly fee if funds available
        if (balance >= monthlyFee) {
            balance -= monthlyFee;
            addToHistory("Monthly fee deducted: -$" + String.format("%.2f", monthlyFee));
        }
    }
    
    @Override
    public String getAccountType() {
        return "Checking";
    }
}
