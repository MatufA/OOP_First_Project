package MySQLPack;
import java.sql.*;
import java.util.Scanner;

import databasePack.Network;
import readPack.*;
public class main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		String read = "SELECT * FROM csv_table";

		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			//printData(); //a function that prints all data in table
			
			MySQLFilters f = new MySQLFilters();
			//f.MySQLFilters("32.09026265", "32.09029801", "Lat", "Lat_table");//("32.09026265", "32.09029801");
			//f.MySQLFilters("34.80787979", "34.80806405");
			//f.LatFilter("31.9492352", "32.10309237");
			//f.AndFilter("32.09098162", "32.09098162", "Lat", "56", "56", "Alt");
		//	f.NotAndNotFilter("32.09098162", "32.09098162", "Lat", "56", "56", "Alt");
			//f.OrFilter("32.09098162", "32.09098162", "Lat", "52", "54", "Alt");
			//f.testFilter("31.9492352", "32.10309237", "Lat");
			//f.testFilter("28/10/2017 20:10", "28/10/2017 20:11", "Time");
			//f.AltFilter("0", "32");
			//f.DevFilter("LG-D855");
			//f.DateFilter("28/10/2017 20:13", "28/10/2017 20:14");
			//f.generalFilter(val1, val2, typeFilter, chooseTable);
			emptyTableData em = new emptyTableData();
			String lt = "Lat_table";
			String lon = "Lon_table";
			String alt = "Alt_table";
			String dev = "Device_table";
			String date = "Date_table";
/*			em.reset(alt);
			em.reset(lon);*/
		   // em.reset(lt);
			//em.reset(dev);
			//em.reset(date);
			//em.reset(chooseTable);
			
			
			addRow create = new addRow();  //to add a row with data of Network - SSID :: Alt
			//create.add("1234", "5453", "3213", "432432", "3243543", "321fdsfds", "4324.6", "213.55", "356.2");
			
			emptyTableData resetTable = new emptyTableData(); // empty all data in table - like new table
			resetTable.reset("csv_table");
			
			//System.out.println("after reset the data table is empty");
			//printData(); 

			/*
			PreparedStatement st = con.prepareStatement("DELETE FROM csv_table WHERE SSID = ?"); //statement that you choose 
			st.setString(1,"2");                                                             // value and it's deleting all rows with
			st.executeUpdate();                                                             // the specific value
			System.out.println("You just deleted a row!!!");
			printData();*/


			

		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			System.out.println("Disconnected");
		}

	}


	public static void printData(){
		String read = "SELECT * FROM csv_table";
		PreparedStatement preparedStatment = null;
		ResultSet rs = null;
		Statement statment= null;

		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			rs = pr.executeQuery();
			while(rs.next()){

				System.out.println(rs.getString("SSID")+ " " + rs.getString("MAC")+ " " + rs.getString("Frequncy")+ " "
						+ rs.getString("Signla")+ " "  + rs.getString("Time")+ " " + rs.getString("Id")+ " "
						+ rs.getString("Lat")+ " " + rs.getString("Lon")+ " " + rs.getString("Alt")+ " ");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		

	}
}

