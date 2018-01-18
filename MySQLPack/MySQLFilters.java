package MySQLPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import filtersPack.*;
public class MySQLFilters {
	private String val1, val2, typeFilter, chooseTable;

	public void MySQLFilters(String val1, String val2, String typeFilter, String chooseTable  ){
		this.val1 = val1;
		this.val2 = val2;
		this.typeFilter = typeFilter;
		this.chooseTable = chooseTable;
	}

	public void MySQLFilters(MySQLFilters mf){
		this.val1 = mf.getVal1();
		this.val2 = mf.getVal2();
		this.typeFilter = mf.getTypeFilter();
	}
	
	public void GeneralFilter(String val1, String val2, String typeFilter){  
		this.val1 = val1;
		this.val2 = val2;
		this.typeFilter = typeFilter;
		
		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table where '"+this.typeFilter+"' between '"+this.val1+"' and '"+this.val2+"'");
			if(rs.next()){
				while (rs.next())
				{

					String Lat_table = "INSERT INTO lat_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Lat_table);
				}
				System.out.println("Records are in Lat table now");
				//showTableData();
			} 			
			else System.out.println("You didnt insert nothing");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	public void NotAndNotFilter(String val1, String val2,String typeFilter1, String val3, String val4, String typeFilter2){  
		this.val1 = val1;
		this.val2 = val2;
		this.typeFilter = typeFilter;
		
		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){
			//("SELECT * FROM csv_table where Time between '"+time1+"' and '"+time2+"'");
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table WHERE NOT "+typeFilter1+" BETWEEN '"+val1+"' AND '"+val2+"' AND NOT "
							+typeFilter2+" between '"+val3+"' and '"+val4+"'");
			
			if(rs.next()){
				while (rs.next())
				{

					String Lat_table = "INSERT INTO lat_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Lat_table);
				}
				System.out.println("Records are in Lat table now");
				//showTableData();
			} 			
			else System.out.println("You didnt insert nothing");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AndFilter(String val1, String val2,String typeFilter1, String val3, String val4, String typeFilter2){  
		this.val1 = val1;
		this.val2 = val2;
		this.typeFilter = typeFilter;
		
		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){
			//("SELECT * FROM csv_table where Time between '"+time1+"' and '"+time2+"'");
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table WHERE "+typeFilter1+" BETWEEN '"+val1+"' AND '"+val2+"' AND "
							+typeFilter2+" between '"+val3+"' and '"+val4+"'");
			
			if(rs.next()){
				while (rs.next())
				{

					String Lat_table = "INSERT INTO lat_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Lat_table);
				}
				System.out.println("Records are in Lat table now");
				//showTableData();
			} 			
			else System.out.println("You didnt insert nothing");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void OrFilter(String val1, String val2,String typeFilter1, String val3, String val4, String typeFilter2){  
		this.val1 = val1;
		this.val2 = val2;
		this.typeFilter = typeFilter;
		
		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){
			//("SELECT * FROM csv_table where Time between '"+time1+"' and '"+time2+"'");
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table WHERE "+typeFilter1+" BETWEEN '"+val1+"' AND '"+val2+"' OR "
							+typeFilter2+" between '"+val3+"' and '"+val4+"'");
			
			if(rs.next()){
				while (rs.next())
				{

					String Lat_table = "INSERT INTO lat_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Lat_table);
				}
				System.out.println("Records are in Lat table now");
				//showTableData();
			} 			
			else System.out.println("You didnt insert nothing");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void LatFilter(String lat1, String lat2){

		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table where Lat between '"+lat1+"' and '"+lat2+"'");
			if(rs.next()){
				while (rs.next())
				{

					String Lat_table = "INSERT INTO lat_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Lat_table);
				}
				System.out.println("Records are in Lat table now");
				//showTableData();
			} 			
			else System.out.println("You didnt insert nothing");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void LonFilter(String lon1, String lon2){

		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table where Lon between '"+lon1+"' and '"+lon2+"'");
			if(rs.next()){
				while (rs.next())
				{

					String Lat_table = "INSERT INTO Lon_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Lat_table);
				}
				System.out.println("Records are in Lon table now");
			} 			
			else System.out.println("You didnt insert nothing");
			//showTableData();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void AltFilter(String alt1, String alt2){

		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table where Alt between '"+alt1+"' and '"+alt2+"'");
			if(rs.next()){
				while (rs.next())
				{

					String Alt_table = "INSERT INTO Alt_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Alt_table);
				}
				System.out.println("Records are in Alt table now");
			} 			
			else System.out.println("You didnt insert nothing");
			//showTableData();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void DevFilter(String dev){

		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table where Id= '"+dev+"'");
			if(rs.next()){
				while (rs.next())
				{

					String Device_table = "INSERT INTO Device_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Device_table);
				}
				System.out.println("Records are in Device table now");			
			}
			else System.out.println("You didnt insert nothing");
			//showTableData();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void DateFilter(String time1, String time2){

		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table where Time between '"+time1+"' and '"+time2+"'");
			if(rs.next()){
				while (rs.next())
				{

					String Date_table = "INSERT INTO Date_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Date_table);
				}
				System.out.println("Records are in Date table now");
				//showTableData();
			} 			
			else System.out.println("You didnt insert nothing");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}


	
	public String getVal1() {
		return val1;
	}

	public String getVal2() {
		return val2;
	}

	public String getTypeFilter() {
		return typeFilter;
	}

	public String getChooseTable() {
		return chooseTable;
	}

	

	//typefilter- lat\lon\alt\date - name by column
	//chooseTabe - table to insert data after filter
	public void testFilter(String val1, String val2, String typeFilter){  

		String read = "SELECT * FROM csv_table"; //Enter to csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement(read)
				){

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM csv_table WHERE "+typeFilter+" BETWEEN '"+val1+"' AND '"+val2+"' ORDER BY "+typeFilter+"");
			if(rs.next()){
				while (rs.next())
				{

					String Lat_table = "INSERT INTO lat_table(SSID,MAC,Frequncy,Signla,Time,Id,Lat,Lon,Alt) VALUES(?,?,?,?,?,?,?,?,?)";
					addRow a = new addRow();
					a.add(rs.getString("SSID"), rs.getString("MAC"), rs.getString("Frequncy"), rs.getString("Signla"),
							rs.getString("Time"), rs.getString("Id"), rs.getString("Lat"), rs.getString("Lon"),
							rs.getString("Alt"), Lat_table);
				}
				System.out.println("Records are in Lat table now");
				//showTableData();
			} 			
			else System.out.println("You didnt insert nothing");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void showTableData(){
		PreparedStatement preparedStatment = null;
		ResultSet rs = null;
		Statement statment= null;
		String read = "SELECT * FROM lat_table";

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
			System.out.println("aldsaldsaldsad");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
