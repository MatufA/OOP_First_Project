package MySQLPack;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Connecter {

	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "password";
	private static final String CONN /*JDBC_CONNECTION_UR*/ = "jdbc:mysql://localhost/oop_project";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub




		try(
				Connection con = DBConnection.getConnection(); //connect to database
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("SELECT * FROM csv_table"); // from csv_table - csv table in database in MySQL
				){
			System.out.println("Connected");
			csv_table.getcsv_table(rs);  //getting networks from csv table in database (MySQL)

			rs.last();//run for last row
			System.out.println(rs.isLast());
			System.out.println(rs.getString("Id"));  // gives the last data of which header you choose after using last

			System.out.println(rs.getRow());
			
		} catch(SQLException e){
			System.err.print(e);
		}
		finally{
			System.out.println("Disconnected");
		}

	}
	

}
