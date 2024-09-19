package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginScreen {

    private JPanel panel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private UserController controller;


    public LoginScreen(App app, UserController controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(3, 2));
        loginField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        panel.add(new JLabel("Username/Email:"));
        panel.add(loginField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);

        // Handle login action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginInput = loginField.getText();
                String password = new String(passwordField.getPassword());
                // Assume valid credentials for now
                try {
                    if (!loginInput.isEmpty() && !password.isEmpty() && controller.loginUser(loginInput, password)) {
                        app.setLoginStatus(true);  // Mark user as logged in
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        app.showProfileScreen();  // Navigate to Profile screen on successful login
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
