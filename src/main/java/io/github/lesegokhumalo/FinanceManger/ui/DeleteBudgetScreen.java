package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBudgetScreen extends JFrame {
    UserController controller;
    public DeleteBudgetScreen(UserController controller) {
        setTitle("Delete Budget");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel warningLabel = new JLabel("Are you sure you want to delete the budget?");
        JButton confirmButton = new JButton("Yes, Delete");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBudget();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(warningLabel);
        panel.add(confirmButton);
        panel.add(cancelButton);

        add(panel);
    }

    private void deleteBudget() {
        // Code to delete the budget
        JOptionPane.showMessageDialog(this, "Budget Deleted Successfully!");
        dispose();
    }
}
