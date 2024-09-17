package io.github.lesegokhumalo.FinanceManger;

import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    private Connection dbConnection;

    public UserController(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void registerUser(Context ctx) throws SQLException {
        String username = ctx.formParam("username");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        double income = Double.parseDouble(ctx.formParam("income"));
        double budget = Double.parseDouble(ctx.formParam("budget"));

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

                ctx.status(201).result("User and profile registered successfully.");
            }
        } else {
            ctx.status(400).result("Invalid password.");
        }
    }

    public void loginUser(Context ctx) throws SQLException {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ctx.status(200).result("Login successful.");
            } else {
                ctx.status(401).result("Invalid credentials.");
            }
        }
    }

    private static boolean validatePassword(String password) {
        return password.length() >= 8 && password.length() <= 10;
    }
}
