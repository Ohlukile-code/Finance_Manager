package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateBudgetScreen extends JFrame {

    private JTextField payDayField, incomeField, livingCostsField, debtField, savingsField, insuranceField;
    private UserController controller;
    public UpdateBudgetScreen(UserController controller) {
        setTitle("Update Budget");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Existing budget data (this can later be retrieved from a database)
        String payDay = "25th";
        double income = 20000;
        double livingCosts = 5000;
        double debt = 2000;
        double savings = 3000;
        double insurance = 1000;

        // Form fields pre-filled with existing data
        JLabel payDayLabel = new JLabel("Pay Day:");
        payDayField = new JTextField(payDay, 10);

        JLabel incomeLabel = new JLabel("Income:");
        incomeField = new JTextField(String.valueOf(income), 10);

        JLabel livingCostsLabel = new JLabel("Living Costs:");
        livingCostsField = new JTextField(String.valueOf(livingCosts), 10);

        JLabel debtLabel = new JLabel("Debt:");
        debtField = new JTextField(String.valueOf(debt), 10);

        JLabel savingsLabel = new JLabel("Savings & Investments:");
        savingsField = new JTextField(String.valueOf(savings), 10);

        JLabel insuranceLabel = new JLabel("Insurance:");
        insuranceField = new JTextField(String.valueOf(insurance), 10);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBudget();
            }
        });

        // Layout setup
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(payDayLabel);
        panel.add(payDayField);
        panel.add(incomeLabel);
        panel.add(incomeField);
        panel.add(livingCostsLabel);
        panel.add(livingCostsField);
        panel.add(debtLabel);
        panel.add(debtField);
        panel.add(savingsLabel);
        panel.add(savingsField);
        panel.add(insuranceLabel);
        panel.add(insuranceField);
        panel.add(updateButton);

        add(panel);
    }

    private void updateBudget() {
        // Save the updated budget details
        JOptionPane.showMessageDialog(this, "Budget Updated Successfully!");
    }
}
