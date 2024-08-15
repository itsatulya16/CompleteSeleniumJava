package DB_Testing;

import java.sql.*;

public class Test_connection_local {

    public static void main(String[] args) {
        // Database URL
        String url = "jdbc:mysql://localhost:3306/mydb"; // Replace with your database name
        String username = "root"; // Replace with your username
        String password = "Destiny@8796"; // Replace with your password

        try {
            // Establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connection established successfully!");
            String query = "SELECT * FROM employees";
            // Optional: Execute a query to verify
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query); // Replace with your table name


            while (resultSet.next()) {
                String name = resultSet.getString("emp_Name");
                System.out.println("Employee names: " + name);
            }


            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
