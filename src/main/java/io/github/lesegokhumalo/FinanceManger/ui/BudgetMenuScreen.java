package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BudgetMenuScreen extends JFrame {
    JPanel panel = new JPanel();
    UserController controller;
    public BudgetMenuScreen(UserController controller) {
        this.controller = controller;
        setTitle("Budget Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton createBudgetBtn = new JButton("Create Budget");
        JButton viewBudgetBtn = new JButton("View Budget");
        JButton updateBudgetBtn = new JButton("Update Budget");
        JButton deleteBudgetBtn = new JButton("Delete Budget");

        // Add action listeners to buttons
        createBudgetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCreateBudgetScreen();
            }
        });

        viewBudgetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openViewBudgetScreen();
            }
        });

        updateBudgetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openUpdateBudgetScreen();
            }
        });

        deleteBudgetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDeleteBudgetScreen();
            }
        });

        // Layout for the buttons
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createBudgetBtn);
        panel.add(viewBudgetBtn);
        panel.add(updateBudgetBtn);
        panel.add(deleteBudgetBtn);

        // Add the panel to the frame
        add(panel);
    }

    private void openCreateBudgetScreen() {
        new CreateBudgetScreen(controller).setVisible(true);
    }

    private void openViewBudgetScreen() {
        new ViewBudgetScreen(controller).setVisible(true);
    }

    private void openUpdateBudgetScreen() {
        new UpdateBudgetScreen(controller).setVisible(true);
    }

    private void openDeleteBudgetScreen() {
        new DeleteBudgetScreen(controller).setVisible(true);
    }

    public Container getPanel() {
        return panel;
    }
}



