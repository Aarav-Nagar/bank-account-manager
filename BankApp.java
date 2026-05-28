import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.List;

public class BankApp extends Application {
    private List<Account> accounts = new ArrayList<>();
    private Account selectedAccount;
    
    private ComboBox<Account> accountCombo;
    private Label balanceLabel;
    private TextArea historyArea;
    private TextField amountField;
    private TextField nameField;
    private TextField accountNumField;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Account Manager");
        
        // Main layout
        BorderPane root = new BorderPane();
        
        // Top: Title
        Label titleLabel = new Label("Bank Account Manager");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        VBox topBox = new VBox(titleLabel);
        topBox.setPadding(new Insets(10));
        root.setTop(topBox);
        
        // Center: Account selection and operations
        VBox centerBox = new VBox(15);
        centerBox.setPadding(new Insets(15));
        
        // Account creation section
        VBox createBox = createAccountCreationSection();
        centerBox.getChildren().add(createBox);
        
        // Separator
        Separator sep1 = new Separator();
        centerBox.getChildren().add(sep1);
        
        // Account selection
        HBox selectBox = new HBox(10);
        selectBox.setPadding(new Insets(10));
        selectBox.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5;");
        
        Label selectLabel = new Label("Select Account:");
        accountCombo = new ComboBox<>();
        accountCombo.setOnAction(e -> selectAccountAction());
        selectBox.getChildren().addAll(selectLabel, accountCombo);
        centerBox.getChildren().add(selectBox);
        
        // Balance display
        balanceLabel = new Label("Balance: $0.00");
        balanceLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: green;");
        centerBox.getChildren().add(balanceLabel);
        
        // Operations section
        VBox operationsBox = createOperationsSection();
        centerBox.getChildren().add(operationsBox);
        
        // History section
        VBox historyBox = createHistorySection();
        centerBox.getChildren().add(historyBox);
        
        ScrollPane scrollPane = new ScrollPane(centerBox);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);
        
        Scene scene = new Scene(root, 700, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createAccountCreationSection() {
        VBox box = new VBox(10);
        box.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10;");
        
        Label title = new Label("Create New Account");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        
        HBox nameBox = new HBox(10);
        nameBox.getChildren().addAll(new Label("Name:"), nameField = new TextField());
        nameField.setPrefWidth(200);
        
        HBox accountNumBox = new HBox(10);
        accountNumBox.getChildren().addAll(new Label("Account #:"), accountNumField = new TextField());
        accountNumField.setPrefWidth(200);
        
        HBox typeBox = new HBox(10);
        typeBox.getChildren().add(new Label("Type:"));
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Checking", "Savings", "Investment");
        typeCombo.setValue("Checking");
        typeBox.getChildren().add(typeCombo);
        
        HBox initialBox = new HBox(10);
        initialBox.getChildren().addAll(new Label("Initial Balance:"), new TextField());
        TextField initialField = (TextField) initialBox.getChildren().get(1);
        initialField.setPrefWidth(150);
        initialField.setText("1000");
        
        Button createBtn = new Button("Create Account");
        createBtn.setStyle("-fx-padding: 8; -fx-font-size: 12;");
        createBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String accNum = accountNumField.getText();
                String type = typeCombo.getValue();
                double balance = Double.parseDouble(initialField.getText());
                
                if (name.isEmpty() || accNum.isEmpty()) {
                    showAlert("Please fill all fields");
                    return;
                }
                
                Account account;
                if (type.equals("Checking")) {
                    account = new CheckingAccount(accNum, name, balance);
                } else if (type.equals("Savings")) {
                    account = new SavingsAccount(accNum, name, balance);
                } else {
                    account = new InvestmentAccount(accNum, name, balance);
                }
                
                accounts.add(account);
                accountCombo.getItems().add(account);
                showAlert("Account created successfully!");
                nameField.clear();
                accountNumField.clear();
                initialField.setText("1000");
            } catch (NumberFormatException ex) {
                showAlert("Invalid amount");
            }
        });
        
        box.getChildren().addAll(title, nameBox, accountNumBox, typeBox, initialBox, createBtn);
        return box;
    }
    
    private VBox createOperationsSection() {
        VBox box = new VBox(10);
        box.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10;");
        
        Label title = new Label("Operations");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        
        HBox amountBox = new HBox(10);
        amountBox.getChildren().addAll(new Label("Amount:"), amountField = new TextField());
        amountField.setPrefWidth(150);
        
        HBox buttonBox = new HBox(10);
        
        Button depositBtn = new Button("Deposit");
        depositBtn.setPrefWidth(100);
        depositBtn.setOnAction(e -> performDeposit());
        
        Button withdrawBtn = new Button("Withdraw");
        withdrawBtn.setPrefWidth(100);
        withdrawBtn.setOnAction(e -> performWithdraw());
        
        Button interestBtn = new Button("Apply Interest");
        interestBtn.setPrefWidth(100);
        interestBtn.setOnAction(e -> applyInterest());
        
        Button transferBtn = new Button("Transfer");
        transferBtn.setPrefWidth(100);
        transferBtn.setOnAction(e -> showTransferDialog());
        
        buttonBox.getChildren().addAll(depositBtn, withdrawBtn, interestBtn, transferBtn);
        
        box.getChildren().addAll(title, amountBox, buttonBox);
        return box;
    }
    
    private VBox createHistorySection() {
        VBox box = new VBox(10);
        box.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10;");
        
        Label title = new Label("Transaction History");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        
        historyArea = new TextArea();
        historyArea.setPrefRowCount(12);
        historyArea.setWrapText(true);
        historyArea.setEditable(false);
        
        box.getChildren().addAll(title, historyArea);
        return box;
    }
    
    private void selectAccountAction() {
        selectedAccount = accountCombo.getValue();
        if (selectedAccount != null) {
            updateDisplay();
        }
    }
    
    private void performDeposit() {
        if (selectedAccount == null) {
            showAlert("Select an account first");
            return;
        }
        try {
            double amount = Double.parseDouble(amountField.getText());
            selectedAccount.deposit(amount);
            updateDisplay();
            amountField.clear();
            showAlert("Deposited $" + String.format("%.2f", amount));
        } catch (NumberFormatException ex) {
            showAlert("Invalid amount");
        }
    }
    
    private void performWithdraw() {
        if (selectedAccount == null) {
            showAlert("Select an account first");
            return;
        }
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (selectedAccount.withdraw(amount)) {
                updateDisplay();
                amountField.clear();
                showAlert("Withdrew $" + String.format("%.2f", amount));
            } else {
                showAlert("Insufficient funds or invalid amount");
            }
        } catch (NumberFormatException ex) {
            showAlert("Invalid amount");
        }
    }
    
    private void applyInterest() {
        if (selectedAccount == null) {
            showAlert("Select an account first");
            return;
        }
        selectedAccount.calculateInterest();
        updateDisplay();
        showAlert("Interest calculated");
    }
    
    private void showTransferDialog() {
        if (selectedAccount == null) {
            showAlert("Select source account first");
            return;
        }
        if (accounts.size() < 2) {
            showAlert("Need at least 2 accounts to transfer");
            return;
        }
        
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Transfer Funds");
        
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        ComboBox<Account> recipientCombo = new ComboBox<>();
        for (Account acc : accounts) {
            if (acc != selectedAccount) {
                recipientCombo.getItems().add(acc);
            }
        }
        
        TextField transferAmount = new TextField();
        transferAmount.setPromptText("Amount to transfer");
        
        content.getChildren().addAll(
            new Label("To Account:"),
            recipientCombo,
            new Label("Amount:"),
            transferAmount
        );
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        if (dialog.showAndWait().isPresent()) {
            try {
                double amount = Double.parseDouble(transferAmount.getText());
                Account recipient = recipientCombo.getValue();
                if (selectedAccount.transfer(recipient, amount)) {
                    updateDisplay();
                    showAlert("Transfer successful!");
                } else {
                    showAlert("Transfer failed");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid amount");
            }
        }
    }
    
    private void updateDisplay() {
        balanceLabel.setText(String.format("Balance: $%.2f", selectedAccount.getBalance()));
        historyArea.setText(selectedAccount.getHistory());
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bank Manager");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
