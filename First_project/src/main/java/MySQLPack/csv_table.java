package MySQLPack;

import java.sql.ResultSet;
import java.sql.SQLException;

public class csv_table {

	public static void getcsv_table(ResultSet rs) throws SQLException{
		while(rs.next()){
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("SSID " +rs.getString("SSID") + " ");
			buffer.append("MAC " + rs.getString("MAC") + " ");
			buffer.append("Frequncy " + rs.getInt("Frequncy") + " ");
			buffer.append("Signal " + rs.getInt("Signla") + " ");
			buffer.append("Time "+ rs.getString("Time") + " ");
			buffer.append("Id " + rs.getString("Id") + " ");
			buffer.append("Lat " + rs.getDouble("Lat") + " ");
			buffer.append("Lon " + rs.getDouble("Lon") + " ");
			buffer.append("Alt " + rs.getDouble("Alt") + " ");
			
			System.out.println(buffer.toString());
		}
		
	}
	
}
