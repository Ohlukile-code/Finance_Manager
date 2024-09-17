package io.github.lesegokhumalo.FinanceManger;

public class Budget {
    private double totalBudget;
    private double savings;
    private double expenses;

    public Budget(double totalBudget, double savings, double expenses) {
        this.totalBudget = totalBudget;
        this.savings = savings;
        this.expenses = expenses;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Budget [Total=" + totalBudget + ", Savings=" + savings + ", Expenses=" + expenses + "]";
    }
}
