package io.github.lesegokhumalo.FinanceManger;

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

        String createProfilesTable = "CREATE TABLE IF NOT EXISTS profiles (" +
                "id SERIAL PRIMARY KEY, " +
                "username VARCHAR(255) NOT NULL, " +
                "income DOUBLE PRECISION, " +
                "budget DOUBLE PRECISION, " +
                "FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE" +
                ");";

        try (Statement stmt = dbConnection.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createProfilesTable);
        }
    }

    public void registerUser(User user) throws SQLException {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        double income = user.getProfile().getIncome();
        double budget = user.getProfile().getBudget();

        if (validatePassword(password)) {
            String insertUserQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            String insertProfileQuery = "INSERT INTO profiles (username, income, budget) VALUES (?, ?, ?)";

            try (PreparedStatement userStmt = dbConnection.prepareStatement(insertUserQuery);
                 PreparedStatement profileStmt = dbConnection.prepareStatement(insertProfileQuery)) {

                // Inserting user details
                userStmt.setString(1, username);
                userStmt.setString(2, email);
                userStmt.setString(3, password);
                userStmt.executeUpdate();

                // Inserting profile details
                profileStmt.setString(1, username);
                profileStmt.setDouble(2, income);
                profileStmt.setDouble(3, budget);
                profileStmt.executeUpdate();

//                ctx.status(201).result("User and profile registered successfully.");
            }
        } else {
//            ctx.status(400).result("Invalid password.");
        }
    }

    public void loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid credentials.");
            }
        }
    }

    private static boolean validatePassword(String password) {
        return password.length() >= 8 && password.length() <= 10;
    }
}
