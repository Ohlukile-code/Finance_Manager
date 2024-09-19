package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterScreen {

    private JPanel panel;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JButton backButton;
    private UserController controller;

    public RegisterScreen(App app, UserController controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(4, 2));
        emailField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(submitButton);
        panel.add(backButton);

        // Handle registration logic
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Assume registration logic here
                JOptionPane.showMessageDialog(null, "Registration Successful! Please log in.");
                app.showLoginScreen();  // Navigate to login screen after registration
            }
        });

        // Back to main menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showMainMenu();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
