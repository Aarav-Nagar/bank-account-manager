# Bank Account Manager

A comprehensive JavaFX-based bank account management system demonstrating core Object-Oriented Programming principles including inheritance, polymorphism, and abstract classes.

## Project Overview

This application provides a complete banking solution with support for multiple account types, each with unique characteristics and interest calculation methods. Built with a JavaFX graphical user interface for ease of use and real-time balance tracking.

## Features

- **Multi-Account Management**: Create and manage multiple account types simultaneously
- **Deposit & Withdrawal**: Safe, validated transactions with balance verification
- **Inter-Account Transfers**: Move funds between accounts with audit trail logging
- **Interest Calculation**: Automatic interest application based on account type
- **Transaction History**: Complete timestamped history of all operations
- **Real-Time Updates**: Immediate balance and status reflection in the UI
- **Input Validation**: Comprehensive validation for all user inputs

## Account Types

### Checking Account
- **Monthly Fee**: $5.00 per month
- **Interest Rate**: None (0%)
- **Best For**: Frequent transactions and everyday banking
- **Minimum Balance**: None required
- **Fee Application**: Deducted if sufficient balance exists

### Savings Account
- **Interest Rate**: 2% annual (calculated monthly)
- **Monthly Fee**: None
- **Best For**: Regular savings with modest returns
- **Compound Interest**: Monthly compounding for consistent growth
- **Formula**: Monthly Interest = Balance × (0.02 ÷ 12)

### Investment Account
- **Interest Rate**: 5% annual (calculated monthly)
- **Monthly Fee**: None
- **Minimum Balance**: $1,000 required to earn interest
- **Best For**: Investors with substantial capital
- **Premium Account**: Highest returns among account types
- **Formula**: Monthly Interest = Balance × (0.05 ÷ 12) [if balance ≥ $1,000]

## Requirements

- Java 11 or higher
- JavaFX SDK 11+ (runtime library)
- Modern operating system (Windows, macOS, Linux)

## Compile and Run

```bash
# Compile all Java files
javac *.java

# Run the application
java BankApp
```

## How to Use

### Creating an Account
1. Fill in the "Create New Account" section with:
   - Account Holder Name (required)
   - Account Number (unique identifier)
   - Initial Balance (opening deposit)
2. Select the desired account type from the dropdown (Checking, Savings, or Investment)
3. Click "Create Account" to initialize the account

### Managing Accounts
1. Select an account from the "Select Account" dropdown
2. View current balance in the Balance display
3. Enter transaction amount in the amount field

### Transaction Operations
- **Deposit**: Add funds to the account
- **Withdraw**: Remove funds (validates sufficient balance)
- **Apply Interest**: Calculate and apply monthly interest
- **Transfer**: Move funds between accounts with full logging

### Viewing History
- Transaction history displays all account operations
- Each transaction is timestamped for audit purposes
- History shows transaction type, amount, and timestamp

## Java Concepts Demonstrated

- **Abstract Classes**: Account base class defines contract for subclasses
- **Inheritance**: Checking, Savings, and Investment extend Account
- **Polymorphism**: Each account type implements calculateInterest() differently
- **Encapsulation**: Private fields with public getters
- **Collections**: ArrayList for managing multiple accounts and history
- **Exception Handling**: Input validation and error checking
- **Date/Time**: Timestamp generation with SimpleDateFormat
- **JavaFX GUI**: 
  - Scene and Stage management
  - Layout panes (BorderPane, VBox, HBox)
  - Event handling for buttons
  - Real-time UI updates

## Architecture

### Class Hierarchy
```
Account (abstract)
├── CheckingAccount
├── SavingsAccount
└── InvestmentAccount
```

### Key Methods
- `deposit(double amount)`: Add funds to account
- `withdraw(double amount)`: Remove funds from account
- `transfer(Account recipient, double amount)`: Transfer between accounts
- `calculateInterest()`: Apply interest (overridden per account type)
- `getHistory()`: Retrieve all transactions

## Testing the Application

### Test Scenarios
1. **Multiple Accounts**: Create accounts of each type
2. **Transactions**: Test deposits, withdrawals, and transfers
3. **Interest**: Apply interest to different account types
4. **History**: Verify transaction history is logged correctly
5. **Edge Cases**: Attempt overdrafts, transfers with insufficient funds

## Future Enhancements

- Database integration for persistent storage
- User authentication and login
- Account statements and reporting
- Scheduled interest calculations
- Bill payment integration
- Transaction filtering and search

## License

MIT License - Free to use, modify, and distribute

## Author

Aarav Nagar

## Version

1.0 - Initial Release (May 2026)
