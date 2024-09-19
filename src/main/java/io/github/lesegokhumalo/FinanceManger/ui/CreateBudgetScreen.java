package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateBudgetScreen extends JFrame {

    private JTextField payDayField, incomeField, livingCostsField, debtField, savingsField, insuranceField;
    private UserController controller;
    public CreateBudgetScreen(UserController controller) {
        setTitle("Create Budget");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Form fields
        JLabel payDayLabel = new JLabel("Pay Day:");
        payDayField = new JTextField(10);

        JLabel incomeLabel = new JLabel("Income:");
        incomeField = new JTextField(10);

        JLabel livingCostsLabel = new JLabel("Living Costs:");
        livingCostsField = new JTextField(10);

        JLabel debtLabel = new JLabel("Debt:");
        debtField = new JTextField(10);

        JLabel savingsLabel = new JLabel("Savings & Investments:");
        savingsField = new JTextField(10);

        JLabel insuranceLabel = new JLabel("Insurance:");
        insuranceField = new JTextField(10);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitBudget();
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
        panel.add(submitButton);

        add(panel);
    }

    private void submitBudget() {
        String payDay = payDayField.getText();
        double income = Double.parseDouble(incomeField.getText());
        double livingCosts = Double.parseDouble(livingCostsField.getText());
        double debt = Double.parseDouble(debtField.getText());
        double savings = Double.parseDouble(savingsField.getText());
        double insurance = Double.parseDouble(insuranceField.getText());

        // Store this budget data as needed
        JOptionPane.showMessageDialog(this, "Budget Created Successfully!");
    }
}

