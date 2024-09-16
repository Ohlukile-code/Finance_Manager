package io.github.lesegokhumalo.FinanceManger;

import java.util.ArrayList;
import java.util.List;

public class Budget {
    private String id;
    private double income;
    private double expenses;
    private List<String> categories = new ArrayList<>();

    public Budget(String id, double income, double expenses) {
        this.id = id;
        this.income = income;
        this.expenses = expenses;
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    public String getId() {
        return id;
    }
}
