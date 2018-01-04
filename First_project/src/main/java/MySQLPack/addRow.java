package MySQLPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


import databasePack.Network;
import GUIPack.*;
import readPack.*;

public class addRow {

	public static void main(String[] args) {
		String fileName="G:\\book.xlsx";
		//Vector dataHolder=read(fileName);
		// saveToDatabase(dataHolder);
	}

	public addRow(){
	}

	public void add(String SSID, String MAC, String Frequncy, String Signla,
			String Time, String Id, String Lat, String Lon, String Alt, String chooseTable){

		//table  = "INSERT INTO csv_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";


		try(Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement(chooseTable)){

			//String sqlTemplate = "SELECT * FROM Users WHERE Id IN ({0})";


			st.setString(1, SSID);
			st.setString(2, MAC);
			st.setString(3, Frequncy);
			st.setString(4, Signla);
			st.setString(5, Time);
			st.setString(6, Id);
			st.setString(7, Lat);
			st.setString(8, Lon);
			st.setString(9, Alt);

			st.executeUpdate();
			System.out.println("Record is inserted into table!");


			/*			PreparedStatement pr = con.prepareStatement("SELECT * FROM csv_table"); 
			rs = pr.executeQuery();
			while(rs.next()){ //shows the table after adding all parameters - not important

				System.out.println(rs.getString("SSID")+ " " + rs.getString("MAC")+ " " + rs.getString("Frequncy")+ " "
						 			+ rs.getString("Signla")+ " "  + rs.getString("Time")+ " " + rs.getString("Id")+ " "
						 			 + rs.getString("Lat")+ " " + rs.getString("Lon")+ " " + rs.getString("Alt")+ " ");
			}*/

		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public void add(String SSID, String MAC, int Frequncy, int Signla, String Time, String Id, double Lat,
			double Lon, double Alt, String chooseTable) {
		try(Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement(chooseTable)){

			st.setString(1, SSID);
			st.setString(2, MAC);
			st.setInt(3, Frequncy);
			st.setInt(4, Signla);
			st.setString(5, Time);
			st.setString(6, Id);
			st.setDouble(7, Lat);
			st.setDouble(8, Lon);
			st.setDouble(9, Alt);

			st.executeUpdate();
			System.out.println("Record is inserted into table!");


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*public void add(String Id){
		String insertTableSQL  = "INSERT INTO csv_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
		Statement stmt = null;

		System.out.println("trying......Part1");
		try(Connection conn = DBConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(insertTableSQL)){
			st.setString(6, Id);
			st.executeUpdate();

		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}*/




	/*
	 public static Network read(String fileName)    {
	        Network Holder = new Network();
	        try{

	            Home h = new Home();
	            fileName = h.getPat();
	            FileInputStream myInput = new FileInputStream(fileName);
	            //POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
	            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
	            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
	            Iterator rowIter = mySheet.rowIterator();
	            while(rowIter.hasNext()){
	                XSSFRow myRow = (XSSFRow) rowIter.next();
	                Iterator cellIter = myRow.cellIterator();
	                //Vector cellStoreVector=new Vector();
	                List list = new ArrayList();
	                while(cellIter.hasNext()){
	                    XSSFCell myCell = (XSSFCell) cellIter.next();
	                    list.add(myCell);
	                }
	                cellVectorHolder.addElement(list);
	            }
	        }catch (Exception e){e.printStackTrace(); }
	        return cellVectorHolder;
	    }
	 */

}

