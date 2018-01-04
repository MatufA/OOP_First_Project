package MySQLPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class emptyTableData {
	
	public static void reset(String chooseTable) throws SQLException{
		 String table = "SELECT * FROM '"+chooseTable+"'";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pr = con.prepareStatement(table)) {
			
			Statement stTruncate = con.createStatement();
			stTruncate.executeUpdate("truncate table "+chooseTable+"");
			
			}
	}

}
