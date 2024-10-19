package DB_Testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TestLogger {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "Destiny@8796";

    public static void logTestResult(String testName, String status, double executionTime) {

        String query = "INSERT INTO test_results (test_name, status, execution_time) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, testName);
            statement.setString(2, status);
            statement.setDouble(3, executionTime);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Method to clear test results table before running new tests
    public static void clearTestResults() {
        String truncateQuery = "TRUNCATE TABLE test_results";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(truncateQuery);
            System.out.println("Test results table cleared successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
