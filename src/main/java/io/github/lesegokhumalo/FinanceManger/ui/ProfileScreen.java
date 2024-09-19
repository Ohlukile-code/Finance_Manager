package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileScreen {

    private JPanel panel;
    private JTextField incomeField;
    private JTextField budgetField;
    private JButton saveButton;
    private JButton logoutButton;
    private UserController controller;

    public ProfileScreen(App app, UserController controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(3, 2));
        incomeField = new JTextField();
        budgetField = new JTextField();
        saveButton = new JButton("Save Profile");
        logoutButton = new JButton("Logout");

        panel.add(new JLabel("Income:"));
        panel.add(incomeField);
        panel.add(new JLabel("Budget:"));
        panel.add(budgetField);
        panel.add(saveButton);
        panel.add(logoutButton);

        // Handle profile saving
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String income = incomeField.getText();
                String budget = budgetField.getText();
                // Save logic here
                JOptionPane.showMessageDialog(null, "Profile saved successfully!");
            }
        });

        // Logout and return to main menu
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setLoginStatus(false);  // Mark user as logged out
                JOptionPane.showMessageDialog(null, "Logged out!");
                app.showMainMenu();  // Navigate back to main menu on logout
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
