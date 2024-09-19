package io.github.lesegokhumalo.FinanceManger.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen {

    private JPanel panel;
    private JButton registerButton;
    private JButton loginButton;

    public MainMenuScreen(App app) {
        panel = new JPanel();
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        panel.add(registerButton);
        panel.add(loginButton);

        // Navigate to Register screen
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showRegisterScreen();
            }
        });

        // Navigate to Login screen
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showLoginScreen();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
