package MySQLPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "password";
	private static final String CONN /*JDBC_CONNECTION_UR*/ = "jdbc:mysql://localhost/oop_project";
	
	public static Connection getConnection() throws SQLException{
		
		
		return DriverManager.getConnection(CONN,USERNAME,PASSWORD);  // open connection;
		
	}
	
}
