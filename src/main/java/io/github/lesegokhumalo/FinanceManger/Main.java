package io.github.lesegokhumalo.FinanceManger;


public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();

        // Sample accounts
        financeManager.addAccount(new Account("Checking Account", 5000));
        financeManager.addAccount(new Account("Savings Account", 10000));

        // Sample transactions
        financeManager.addTransaction(new Expense("Groceries", 200, "Checking Account"));
        financeManager.addTransaction(new Income("Salary", 3000, "Checking Account"));


        financeManager.printSummary();
    }
}

