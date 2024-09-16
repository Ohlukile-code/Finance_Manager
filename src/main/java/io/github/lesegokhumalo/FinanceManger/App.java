package io.github.lesegokhumalo.FinanceManger;

import javax.swing.*;
import java.awt.*;

public class App {

    // Method to launch the UI
    public static void launchApp() {
        // Ensure the UI is launched on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame frame = new JFrame("Financial Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Create buttons
            JButton registerButton = new JButton("Register");
            JButton loginButton = new JButton("Login");

            // Create the panel to hold the form fields
            JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
            JTextField usernameField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JLabel statusLabel = new JLabel("");

            // Initially hide the form panel
            formPanel.setVisible(false);

            // Add labels and fields to the form panel
            formPanel.add(new JLabel("Username:"));
            formPanel.add(usernameField);
            formPanel.add(new JLabel("Email:"));
            formPanel.add(emailField);
            formPanel.add(new JLabel("Password:"));
            formPanel.add(passwordField);

            // Create panel for Register/Login buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(registerButton);
            buttonPanel.add(loginButton);

            // Create panel for the form actions (Submit/Cancel)
            JPanel actionPanel = new JPanel();
            JButton submitButton = new JButton("Submit");
            JButton cancelButton = new JButton("Cancel");
            actionPanel.add(submitButton);
            actionPanel.add(cancelButton);

            // Add components to the main panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(buttonPanel);
            mainPanel.add(formPanel);
            mainPanel.add(actionPanel);
            mainPanel.add(statusLabel);

            // Initially hide action buttons
            actionPanel.setVisible(false);

            // Add action listeners for Register and Login buttons
            registerButton.addActionListener(e -> {
                usernameField.setVisible(true);
                emailField.setVisible(true);
                passwordField.setVisible(true);
                statusLabel.setText("Enter Registration Details");
                formPanel.setVisible(true);
                actionPanel.setVisible(true);
            });

            loginButton.addActionListener(e -> {
                usernameField.setVisible(true);
                emailField.setVisible(false); // Hide email field for login
                passwordField.setVisible(true);
                statusLabel.setText("Enter Login Details");
                formPanel.setVisible(true);
                actionPanel.setVisible(true);
            });

            // Add action listener for Submit button
            submitButton.addActionListener(e -> {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Handle registration
                if (statusLabel.getText().contains("Registration")) {
                    if (validatePassword(password)) {
                        statusLabel.setText("Registration Successful!");
                        formPanel.setVisible(false);
                        actionPanel.setVisible(false);
                    } else {
                        statusLabel.setText("Invalid password! Password must be between 8-10 characters.");
                    }
                }

                // Handle login
                if (statusLabel.getText().contains("Login")) {
                    if (username.isEmpty() || password.isEmpty()) {
                        statusLabel.setText("Please enter both username and password.");
                    } else {
                        statusLabel.setText("Login Successful!");
                        formPanel.setVisible(false);
                        actionPanel.setVisible(false);
                    }
                }
            });

            // Add action listener for Cancel button
            cancelButton.addActionListener(e -> {
                formPanel.setVisible(false);
                actionPanel.setVisible(false);
                statusLabel.setText("");
            });

            // Add main panel to frame and display it
            frame.getContentPane().add(mainPanel);
            frame.setVisible(true);
        });
    }

    // Simplified password validation method (just length check)
    private static boolean validatePassword(String password) {
        // Check if the password length is between 8 and 10 characters
        return password.length() >= 8 && password.length() <= 10;
    }
}
