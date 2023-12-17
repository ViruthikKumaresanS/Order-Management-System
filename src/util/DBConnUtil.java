package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

	public class DBConnUtil {
	    private static Connection connection;

	    static {
	        try {
	         
	            Class.forName("com.mysql.cj.jdbc.Driver");

	           
	            Properties properties = new Properties();

	try(FileInputStream fis = new FileInputStream("db.properties")) {
	    properties.load(fis);
	}


	String url = properties.getProperty("url");
	String username = properties.getProperty("username");
	String password = properties.getProperty("password");

	connection = DriverManager.getConnection(url, username, password);
	} 
	        catch (ClassNotFoundException | SQLException | IOException e) {
	e.printStackTrace();
	}
	}

		public static Connection getConnection() {
			// TODO Auto-generated method stub
			return null;
		}



	}

