# Bank Account Manager

A JavaFX-based bank account management system demonstrating inheritance, polymorphism, and abstract classes in Java.

## Features

- Create multiple account types: Checking, Savings, Investment
- Deposit and withdraw funds
- Transfer money between accounts
- Automatic interest calculation based on account type
- Complete transaction history with timestamps
- Real-time balance updates

## Account Types

- **Checking**: Monthly fee of $5, no interest
- **Savings**: 2% annual interest rate, earns monthly
- **Investment**: 5% annual interest rate, requires $1000 minimum balance

## Requirements

- Java 11 or higher
- JavaFX SDK 11+

## Compile and Run

```
javac *.java
java BankApp
```

## How to Use

1. Enter account holder name, account number, and initial balance
2. Select account type (Checking, Savings, or Investment)
3. Click "Create Account"
4. Select an account from the dropdown
5. Use Deposit/Withdraw buttons for transactions
6. Click "Apply Interest" to calculate monthly interest
7. Use "Transfer" to move money between accounts
8. View complete transaction history in the History section

## Java Concepts Demonstrated

- Abstract classes and methods
- Inheritance
- Polymorphism
- Collections (ArrayList)
- Encapsulation
- Date/Time formatting
- JavaFX GUI development

## License

MIT
