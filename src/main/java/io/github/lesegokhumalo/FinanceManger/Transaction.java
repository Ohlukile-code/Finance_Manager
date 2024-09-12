package io.github.lesegokhumalo.FinanceManger;

public abstract class Transaction {
    protected String description;
    protected double amount;
    protected String accountName;

    public Transaction(String description, double amount, String accountName) {
        this.description = description;
        this.amount = amount;
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return description + ": $" + amount + " (" + accountName + ")";
    }
}
