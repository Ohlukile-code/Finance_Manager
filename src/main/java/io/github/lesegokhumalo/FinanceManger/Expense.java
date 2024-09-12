package io.github.lesegokhumalo.FinanceManger;


public class Expense extends Transaction {
    public Expense(String description, double amount, String accountName) {
        super(description, amount, accountName);
    }
}
