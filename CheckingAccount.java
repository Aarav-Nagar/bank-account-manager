public class CheckingAccount extends Account {
    private double monthlyFee = 5.0;
    
    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }
    
    @Override
    public void calculateInterest() {
        // Checking accounts don't earn interest, but apply monthly fee
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
