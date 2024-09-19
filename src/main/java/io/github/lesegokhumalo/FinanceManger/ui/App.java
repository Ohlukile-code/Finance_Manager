package io.github.lesegokhumalo.FinanceManger.ui;

import io.github.lesegokhumalo.FinanceManger.DatabaseConnection;
import io.github.lesegokhumalo.FinanceManger.UserController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class App {

    private JFrame frame;
    private UserController controller;

    // Keep track of login status
    private boolean isLoggedIn = false;

    public App() throws SQLException {
        frame = new JFrame("Financial Manager");
        controller = new UserController(DatabaseConnection.getConnection());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        showMainMenu();  // Start with the main menu
    }

    public void showMainMenu() {
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        frame.setContentPane(mainMenuScreen.getPanel());
        frame.revalidate();
    }

    public void showRegisterScreen() {
        RegisterScreen registerScreen = new RegisterScreen(this, controller);
        frame.setContentPane(registerScreen.getPanel());
        frame.revalidate();
    }

    public void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen(this, controller);
        frame.setContentPane(loginScreen.getPanel());
        frame.revalidate();
    }

    public void showProfileScreen() {
        if (isLoggedIn) {
            ProfileScreen profileScreen = new ProfileScreen(this, controller);
            frame.setContentPane(profileScreen.getPanel());
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, "Please log in first.");
            showLoginScreen();
        }
    }

    public void setLoginStatus(boolean status) {
        this.isLoggedIn = status;
    }

    public boolean getLoginStatus() {
        return isLoggedIn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new App().frame.setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
