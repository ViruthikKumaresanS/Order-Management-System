package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    // Static function to get the connection string from the property file
    public static String getConnectionString(String propertyFileName) {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(propertyFileName)) {
            // Load properties from the file
            properties.load(fileInputStream);

            // Retrieve the connection string property
            String connectionString = properties.getProperty("db.connectionString=jdbc:mysql://localhost:3306/order_management_system");

            if (connectionString != null && !connectionString.isEmpty()) {
                return connectionString;
            } else {
                throw new RuntimeException("Connection string not found in the property file.");
            }

        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyFileName; 
    }

    public static void main(String[] args) {
        // Example usage
        String propertyFileName = "db.connectionString=jdbc:mysql://localhost:3306/order_management_system";
        String connectionString = getConnectionString(propertyFileName);
        System.out.println("Connection String: " + connectionString);
    }
}
