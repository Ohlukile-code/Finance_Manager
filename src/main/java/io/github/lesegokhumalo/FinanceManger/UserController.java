package io.github.lesegokhumalo.FinanceManger;

import io.github.lesegokhumalo.FinanceManger.Budget;
import io.github.lesegokhumalo.FinanceManger.User;
import io.javalin.http.Context;

import java.sql.*;

public class UserController {

    private Connection dbConnection;

    public UserController(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        initialize();
    }

    // Method to initialize the database tables if they don't exist
    public void initialize() throws SQLException {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "username VARCHAR(255) UNIQUE NOT NULL, " +
                "email VARCHAR(255) UNIQUE NOT NULL, " +
                "password VARCHAR(255) NOT NULL" +
                ");";

        String createBudgetsTable = "CREATE TABLE IF NOT EXISTS budgets (" +
                "id SERIAL PRIMARY KEY, " +
                "username VARCHAR(255) NOT NULL, " +
                "pay_day VARCHAR(255), " +
                "income DOUBLE PRECISION, " +
                "living_costs DOUBLE PRECISION, " +
                "debt DOUBLE PRECISION, " +
                "savings_investments DOUBLE PRECISION, " +
                "insurance DOUBLE PRECISION, " +
                "FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE" +
                ");";

        try (Statement stmt = dbConnection.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createBudgetsTable);
        }
    }

    // Method to register a user and their budget
    public void registerUser(User user) throws SQLException {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        Budget budget = user.getBudget();

        if (validatePassword(password)) {
            String insertUserQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            String insertBudgetQuery = "INSERT INTO budgets (username, pay_day, income, living_costs, debt, savings_investments, insurance) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement userStmt = dbConnection.prepareStatement(insertUserQuery);
                 PreparedStatement budgetStmt = dbConnection.prepareStatement(insertBudgetQuery)) {

                // Inserting user details
                userStmt.setString(1, username);
                userStmt.setString(2, email);
                userStmt.setString(3, password);
                userStmt.executeUpdate();

                // Inserting budget details
                budgetStmt.setString(1, username);
                budgetStmt.setString(2, budget.getPayDay());
                budgetStmt.setDouble(3, budget.getIncome());
                budgetStmt.setDouble(4, budget.getLivingCosts());
                budgetStmt.setDouble(5, budget.getDebt());
                budgetStmt.setDouble(6, budget.getSavingsInvestments());
                budgetStmt.setDouble(7, budget.getInsurance());
                budgetStmt.executeUpdate();

//                ctx.status(201).result("User and budget registered successfully.");
            }
        } else {
//            ctx.status(400).result("Invalid password.");
        }
    }

    // Method to log in a user
    public boolean loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Invalid credentials.");
                return false;
            }
        }
    }

    private static boolean validatePassword(String password) {
        return password.length() >= 8 && password.length() <= 10;
    }
}
