
import controller.MenuController;
import util.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("hola");

        // Test database connection
        Connection testConnection = null;
        try {
            testConnection = ConnectionFactory.openConnection();
            if (testConnection != null) {
                System.out.println("Database connection successful!");
                JOptionPane.showMessageDialog(null, "Database connection successful!");
            } else {
                System.out.println("Database connection failed.");
                JOptionPane.showMessageDialog(null, "Database connection failed.");
            }
        } catch (Exception e) {
            System.out.println("Error during connection test: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error during connection test: " + e.getMessage());
        } finally {
            if (testConnection != null) {
                ConnectionFactory.closeConnection();
            }
        }
        
        // Start the application menu
        MenuController.showMenu();
    }
}

