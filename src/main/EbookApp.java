package main;

import db.DatabaseConnection;
import views.LoginFrame;

import java.sql.Connection;
import java.sql.SQLException;

public class EbookApp {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                // If connection is successful, launch the login window
                new LoginFrame();
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
