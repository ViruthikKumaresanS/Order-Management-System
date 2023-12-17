package util;


import java.sql.Connection;
import java.sql.SQLException;


public class DBUtil {

    // Static method to get a database connection
    public static Connection getDBConn() {
        // Specify the name of the property file
		String propertyFileName = "database.properties";

		// Get the connection string using DBPropertyUtil
		String connectionString = DBPropertyUtil.getConnectionString(propertyFileName);

		// Establish a database connection using DBConnUtil
		return DBConnUtil.getConnection();
    }

    public static void main(String[] args) {
        try (Connection connection = DBUtil.getDBConn()) {
            // Now you have a valid database connection (Connection object)
            System.out.println("Connected to the database.");

            // Perform database operations using the 'connection' object

        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}