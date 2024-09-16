package io.github.lesegokhumalo.FinanceManger;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class BudgetController {

    private Map<String, Budget> budgets = new HashMap<>();

    public void createBudget(Context ctx) {
        Budget budget = ctx.bodyAsClass(Budget.class);
        budgets.put(budget.getId(), budget);
        ctx.status(201).result("Budget created successfully.");
    }

    public void editBudget(Context ctx) {
        String budgetId = ctx.pathParam("id");
        Budget updatedBudget = ctx.bodyAsClass(Budget.class);
        budgets.put(budgetId, updatedBudget);
        ctx.status(200).result("Budget updated successfully.");
    }

    public void getBudget(Context ctx) {
        String budgetId = ctx.pathParam("id");
        Budget budget = budgets.get(budgetId);
        if (budget != null) {
            ctx.status(200).json(budget);
        } else {
            ctx.status(404).result("Budget not found.");
        }
    }

    public void addCategory(Context ctx) {
        String budgetId = ctx.formParam("budgetId");
        String category = ctx.formParam("category");
        Budget budget = budgets.get(budgetId);
        if (budget != null) {
            budget.addCategory(category);
            ctx.status(200).result("Category added.");
        } else {
            ctx.status(404).result("Budget not found.");
        }
    }
}

