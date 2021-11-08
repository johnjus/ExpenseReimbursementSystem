package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnectionFactory 
{
	/*
	 * 
	 * JDBC needs a specific format to the url string so that the drivermanager
	 * can understand which driver you are asking to use
	 * 
	 * 
	 * 
	 * for postgresql, the url format is:
	 * 			jdbc:postgresql://[endpoint/ip]/[databasename]
	 * 
	 */

	public static String URL = "jdbc:postgresql://${systemEnvironment['ERS_DB_ENDPOINT']}";
	public static String username= "#{systemEnvironment['ERS_DB_USERNAME']}"
	public static String password ="#{systemEnvironment['ERS_DB_PASSWORD']}";
	
	
	
	public static Connection getConnection() throws SQLException
	{
		
		return DriverManager.getConnection(URL, username, password);
		
	}
	
	
	

}
