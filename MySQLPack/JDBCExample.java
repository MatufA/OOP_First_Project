package MySQLPack;


import java.sql.*;

public class JDBCExample {  // example how to connect sql data
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/oop_project";

	   //  Database credentials
	   static final String USER = "dbuser";
	   static final String PASS = "password";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rs = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");

	      //STEP 4: Execute a query
	      System.out.println("Deleting database...");
	      stmt = conn.createStatement();
	    //  rs = stmt.executeQuery("SELECT * FROM csv_table");
	      
	      String sql = "DROP DATABASE oop_project";
	      stmt.executeUpdate(sql);
	      System.out.println("Database deleted successfully...");
	      
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
	}//end JDBCExample