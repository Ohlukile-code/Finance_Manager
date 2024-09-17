package io.github.lesegokhumalo.FinanceManger;

public class BudgetController {
    private Budget budget;

    public BudgetController(Budget budget) {
        this.budget = budget;
    }

    public void editBudget(double newTotal, double newSavings, double newExpenses) {
        budget.setTotalBudget(newTotal);
        budget.setSavings(newSavings);
        budget.setExpenses(newExpenses);
        System.out.println("Budget updated successfully: " + budget);
    }

    public void viewBudget() {
        System.out.println("Current Budget: " + budget);
    }
}
