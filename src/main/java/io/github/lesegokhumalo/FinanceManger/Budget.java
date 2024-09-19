package io.github.lesegokhumalo.FinanceManger;

public class Budget {

    private String payDay;
    private double income;
    private double livingCosts;
    private double debt;
    private double savingsInvestments;
    private double insurance;

    public Budget(String payDay, double income, double livingCosts, double debt, double savingsInvestments, double insurance) {
        this.payDay = payDay;
        this.income = income;
        this.livingCosts = livingCosts;
        this.debt = debt;
        this.savingsInvestments = savingsInvestments;
        this.insurance = insurance;
    }

    public String getPayDay() {
        return payDay;
    }

    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getLivingCosts() {
        return livingCosts;
    }

    public void setLivingCosts(double livingCosts) {
        this.livingCosts = livingCosts;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public double getSavingsInvestments() {
        return savingsInvestments;
    }

    public void setSavingsInvestments(double savingsInvestments) {
        this.savingsInvestments = savingsInvestments;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }
}
