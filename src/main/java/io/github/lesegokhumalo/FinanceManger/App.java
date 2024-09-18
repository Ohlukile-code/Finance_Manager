package io.github.lesegokhumalo.FinanceManger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class App {

    private JFrame frame;
    private JPanel panel;
    private JButton registerButton;
    private JButton loginButton;

    public App() {
        frame = new JFrame("Financial Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegistrationScreen();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen();
            }
        });

        panel.add(registerButton);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to launch the UI
    public static void launchApp() throws SQLException {

        UserController controller = new UserController(DatabaseConnection.getConnection());
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

            // Panel to show commands after login/registration
            JPanel commandsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
            JButton viewProfileButton = new JButton("View Profile");
            JButton manageBudgetButton = new JButton("Manage Budget");
            JButton logoutButton = new JButton("Logout");

            commandsPanel.add(viewProfileButton);
            commandsPanel.add(manageBudgetButton);
            commandsPanel.add(logoutButton);
            commandsPanel.setVisible(false);

            // Add components to the main panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(buttonPanel);
            mainPanel.add(formPanel);
            mainPanel.add(actionPanel);
            mainPanel.add(commandsPanel);
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
                        try {
                            controller.registerUser(new User(username,email,password, new Profile(0,0)));
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        statusLabel.setText("Registration Successful! Please log in.");
                        formPanel.setVisible(false);
                        actionPanel.setVisible(false);
                        commandsPanel.setVisible(false);
                    } else {
                        statusLabel.setText("Invalid password! Password must be between 8-10 characters.");
                    }
                }

                // Handle login
                if (statusLabel.getText().contains("Login")) {
                    if (username.isEmpty() || password.isEmpty()) {
                        statusLabel.setText("Please enter both username and password.");
                    } else {
                        try {
                            controller.loginUser(username,password);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        statusLabel.setText("Login Successful!");
                        formPanel.setVisible(false);
                        actionPanel.setVisible(false);
                        commandsPanel.setVisible(true); // Show additional commands after login
                    }
                }
            });

            // Add action listener for Cancel button
            cancelButton.addActionListener(e -> {
                formPanel.setVisible(false);
                actionPanel.setVisible(false);
                statusLabel.setText("");
            });

            // Add action listeners for post-login commands
            viewProfileButton.addActionListener(e ->{ statusLabel.setText("Viewing Profile...");
                System.out.println("online");
            });
            manageBudgetButton.addActionListener(e -> statusLabel.setText("Managing Budget..."));
            logoutButton.addActionListener(e -> {
                commandsPanel.setVisible(false);
                buttonPanel.setVisible(true);
                statusLabel.setText("Logged out. Please log in again.");
            });

            // Add main panel to frame and display it
            frame.getContentPane().add(mainPanel);
            frame.setVisible(true);
        });
    }

    private void showRegistrationScreen() {
        // Registration form (Email, Username, Password)
        JPanel registrationPanel = new JPanel(new GridLayout(4, 2));
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submitButton = new JButton("Submit");

        registrationPanel.add(new JLabel("Email:"));
        registrationPanel.add(emailField);
        registrationPanel.add(new JLabel("Username:"));
        registrationPanel.add(usernameField);
        registrationPanel.add(new JLabel("Password:"));
        registrationPanel.add(passwordField);
        registrationPanel.add(submitButton);

        frame.setContentPane(registrationPanel);
        frame.revalidate();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate and save user details (For now, just go back to login)
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (password.length() >= 8 && password.length() <= 10) {
                    JOptionPane.showMessageDialog(frame, "Registration Successful! Please log in.");
                    showLoginScreen();
                } else {
                    JOptionPane.showMessageDialog(frame, "Password must be 8-10 characters long.");
                }
            }
        });
    }

    private void showLoginScreen() {
        // Login form (Username/Email, Password)
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JTextField loginField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginSubmitButton = new JButton("Login");

        loginPanel.add(new JLabel("Username/Email:"));
        loginPanel.add(loginField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginSubmitButton);

        frame.setContentPane(loginPanel);
        frame.revalidate();

        loginSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginInput = loginField.getText();
                String password = new String(passwordField.getPassword());

                // For simplicity, assuming login is successful
                if (!loginInput.isEmpty() && !password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    showProfileScreen();  // Direct to profile setup after successful login
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials!");
                }
            }
        });
    }

    private void showProfileScreen() {
        // Profile setup screen (Income, Budget)
        JPanel profilePanel = new JPanel(new GridLayout(3, 2));
        JTextField incomeField = new JTextField();
        JTextField budgetField = new JTextField();
        JButton saveButton = new JButton("Save Profile");

        profilePanel.add(new JLabel("Income:"));
        profilePanel.add(incomeField);
        profilePanel.add(new JLabel("Budget:"));
        profilePanel.add(budgetField);
        profilePanel.add(saveButton);

        frame.setContentPane(profilePanel);
        frame.revalidate();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the profile details
                String income = incomeField.getText();
                String budget = budgetField.getText();

                if (!income.isEmpty() && !budget.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Profile saved successfully!");
                    // Proceed to other functionalities like budget editing
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill in all details.");
                }
            }
        });
    }
    // Simplified password validation method (just length check)
    private static boolean validatePassword(String password) {
        // Check if the password length is between 8 and 10 characters
        return password.length() >= 8 && password.length() <= 10;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
