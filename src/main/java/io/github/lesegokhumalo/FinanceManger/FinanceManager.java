package io.github.lesegokhumalo.FinanceManger;


import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private List<Account> accounts;
    private List<Transaction> transactions;

    public FinanceManager() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        for (Account account : accounts) {
            if (account.getName().equals(transaction.getAccountName())) {
                if (transaction instanceof Expense) {
                    account.withdraw(transaction.getAmount());
                } else if (transaction instanceof Income) {
                    account.deposit(transaction.getAmount());
                }
            }
        }
    }

    public void printSummary() {
        System.out.println("Accounts Summary:");
        for (Account account : accounts) {
            System.out.println(account.getName() + ": $" + account.getBalance());
        }

        System.out.println("\nTransactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}