
package util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection objConnection = null;

    public static Connection openConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/product_management";
            String user = "root";
            String password = "220319"; // Changed password to 220319

            objConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
            return objConnection;

        } catch (ClassNotFoundException e) {
            objConnection = null; // Ensure connection is null on driver error
            System.err.println("Driver not found: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR: JDBC Driver not found. Please ensure mysql-connector-j.jar is in the classpath. Details: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("JDBC Driver not found.", e);
        } catch (SQLException e) {
            objConnection = null; // Ensure connection is null on SQL error
            System.err.println("Error connecting to database: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR: Could not connect to the database. Please check credentials and database status. Details: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            throw e; // Re-throw the SQLException
        } catch (Exception e) {
            objConnection = null; // Ensure connection is null on general error
            System.err.println("An unexpected error occurred: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR: An unexpected error occurred during database connection. Details: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Unexpected error during connection.", e);
        }
    }

    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR: Could not close database connection. Details: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}