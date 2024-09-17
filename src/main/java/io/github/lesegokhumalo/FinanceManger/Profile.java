package io.github.lesegokhumalo.FinanceManger;

public class Profile {
    private double income;
    private double budget;

    public Profile(double income, double budget) {
        this.income = income;
        this.budget = budget;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
