package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;

public class ViewBudgetScreen extends JFrame {
    private UserController controller;
    public ViewBudgetScreen(UserController controller) {
        setTitle("View Budget");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Placeholder budget data
        String budgetData = "<html><h3>Budget Details</h3>"
                + "Pay Day: 25th<br>"
                + "Income: R20000<br>"
                + "Living Costs: R5000<br>"
                + "Debt: R2000<br>"
                + "Savings: R3000<br>"
                + "Insurance: R1000</html>";

        JLabel budgetLabel = new JLabel(budgetData);
        add(budgetLabel);
    }
}

