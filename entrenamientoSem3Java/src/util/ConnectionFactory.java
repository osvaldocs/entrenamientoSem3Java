package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection objConnection = null;

    public static Connection openConnection(){

        try{
            // Le damos las credenciales para nuestra bd
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/product_management";
            String user = "root";
            String password = "220319";
            // Establecemos la conexion
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully");

        } catch (ClassNotFoundException error) {
            System.out.println("Driver not installed: " + error.getMessage());
        } catch (SQLException error){
            System.out.println("Error connecting to database: " + error.getMessage());
        }
        return  objConnection;


    }

    public static void closeConnection(){
        try{
            if (objConnection != null){
                objConnection.close();
                System.out.println("Connection closed successfully");
            }
        }catch (SQLException error){
            System.out.println("Error closing connection: " + error.getMessage());
        }
    }
}
