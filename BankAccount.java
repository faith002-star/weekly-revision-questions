/**
 * Question 1: BankAccount Class
 * This class demonstrates encapsulation by using private attributes
 * and providing controlled access through methods.
 */
public class BankAccount {
    // Private attributes as per specifications
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor to initialize all attributes
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // --- Accessor (Getter) and Mutator (Setter) Methods ---
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    // Only Getter for balance to prevent unauthorized manual modification
    public double getBalance() {
        return balance;
    }

    // --- Core Methods ---

    /**
     * Adds the specified amount to the current balance.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount + " into account " + accountNumber);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /**
     * Subtracts the amount if sufficient funds exist.
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount + " from account " + accountNumber);
        } else {
            System.out.println("Withdrawal failed: Insufficient balance or invalid amount.");
        }
    }

    /**
     * Returns the current balance.
     */
    public double checkBalance() {
        return balance;
    }

    /**
     * Displays all account details in a formatted manner.
     */
    public void displayAccountInfo() {
        System.out.println("------------------------------");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: $" + balance);
        System.out.println("------------------------------");
    }
}