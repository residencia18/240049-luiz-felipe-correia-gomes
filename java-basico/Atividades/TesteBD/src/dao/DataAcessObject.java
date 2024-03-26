package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAcessObject {

	private static final String URL = "jdbc:mysql://unjbjp8ppsov0mbu:06bXy9nKjsuZvtmhyG9k@boubw0iyprsul4wpoe0d-mysql.services.clever-cloud.com:3306/boubw0iyprsul4wpoe0d";
	private static final String USER = "unjbjp8ppsov0mbu";
	private static final String PASSWD = "06bXy9nKjsuZvtmhyG9k";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWD);
			System.out.println("Connected!");
		} catch (SQLException e) {
			System.out.println("Error while connecting!");
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
			System.out.println("Connection closed!");
		} catch (SQLException e) {
			
		}
	}	
}
