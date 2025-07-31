package management.system;

import java.sql.*;  

public class conn{
    Connection c;
    Statement s;
    public conn(){  
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the database
            c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database_name", // DB URL
                "username", // Username
                "your password" // Password
            );
            // Create statement object
            s = c.createStatement();
            System.out.println("Connection and Statement created successfully.");
        } catch (Exception e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        }
    }

    // Test connection
    public static void main(String[] args) {
        conn connection = new conn();
        if (connection.c != null && connection.s != null) {
            System.out.println("Database connection and Statement established successfully.");
        } else {
            System.out.println("Failed to establish database connection or Statement.");
        }
}  

}