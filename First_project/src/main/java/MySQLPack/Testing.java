package MySQLPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import databasePack.*;

public class Testing {
	
	public static void main(String[] args) {
		Vector dataHolder = new Vector();
		try {
			
			saveToDatabase(dataHolder);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
    public static void saveToDatabase(Vector dataHolder) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    	String SSID = "";
    	String MAC = "";
    	String Frequncy = "";
    	String Signla = "";
		String Time = "";
		String Id = "";
		String Lat = "";
		String Lon = ""; 
		String Alt = "";
		
        System.out.println(dataHolder);

        for(Iterator iterator = dataHolder.iterator();iterator.hasNext();) {
            List list = (List) iterator.next();
            SSID = list.get(0).toString();
            MAC = list.get(1).toString();
            Frequncy = list.get(2).toString();
            Signla = list.get(3).toString();
            Time = list.get(4).toString();
            Id = list.get(5).toString();
    		Lat = list.get(6).toString();
    		Lon = list.get(7).toString(); 
    		Alt = list.get(8).toString();
            
            
            String read = "SELECT * FROM csv_table";
            try (Connection con = DBConnection.getConnection(); //connect to database
    				PreparedStatement pr = con.prepareStatement(read)) {
                
                System.out.println("connection made...");
                PreparedStatement stmt = con.prepareStatement("INSERT INTO csv_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, SSID);
                stmt.setString(2, MAC);
                stmt.setString(3, Frequncy);
                stmt.setString(4, Signla);
                stmt.setString(5, Time);
                stmt.setString(6, Id);
                stmt.setString(7, Lat);
                stmt.setString(8, Lon);
                stmt.setString(9, Alt);
                
                stmt.executeUpdate();

                System.out.println("Data is inserted");
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        }

}
