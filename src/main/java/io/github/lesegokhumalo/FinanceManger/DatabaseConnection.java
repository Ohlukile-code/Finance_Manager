package io.github.lesegokhumalo.FinanceManger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                // Connect to SQLite (or use MySQL/PostgreSQL connection string)
                String url = "jdbc:sqlite:/home/wethinkcode/PersonalProject/Personal5/src/main/resources/finance_manager.db";
                connection = DriverManager.getConnection(url);
                System.out.println("Connected to database.");
            } catch (SQLException e) {
                System.out.println("Database connection failed.");
                throw new SQLException(e.getMessage());
            }
        }
        return connection;
    }
}
