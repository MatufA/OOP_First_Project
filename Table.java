package main.java.GUIPack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.java.MySQLPack.*;

public class Table extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2032689923506912581L;
	JTable table;
	

	public Table(String chooseTable) {
		
		setBackground(Color.WHITE);                          //filters page
		setBounds(100, 100, 770, 440);
		setTitle("CSV Table");
		getContentPane().setLayout(null);
		
		
		setLayout(new FlowLayout());
		String[] columnNames = {"Time","Id","MAC","SSID","Latitude",
				                "Longitude","Altitude","Frequncy","Signal"};
		int numOfRows = 0; //csv_table
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				PreparedStatement pr = con.prepareStatement("SELECT * FROM "+chooseTable+"")
				){
			//csv_table.getcsv_table(rs);  //getting networks from csv table in database (MySQL)
			ResultSet rs = pr.executeQuery("SELECT * FROM "+chooseTable+"");
			rs.last();//run for last row
			
			System.out.println(rs.getRow());
			numOfRows = rs.getRow();
		} catch(SQLException e){
			System.err.print(e);
		}
		Object [][] data = new Object[numOfRows][9];
		
		try(
				Connection con = DBConnection.getConnection(); //connect to database
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				){
			
			int i = 0;
			ResultSet SSID = stmt.executeQuery("SELECT SSID FROM "+chooseTable+"");
			while(SSID.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][0] = buffer.append(SSID.getString("SSID"));
				i++;
			}
			
			i = 0;
			ResultSet MAC = stmt.executeQuery("SELECT MAC FROM "+chooseTable+"");
			while(MAC.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][1] = buffer.append(MAC.getString("MAC"));
				i++;
			}
			
			i = 0;
			ResultSet Frequncy = stmt.executeQuery("SELECT Frequncy FROM "+chooseTable+"");
			while(Frequncy.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][2] = buffer.append(Frequncy.getString("Frequncy"));
				i++;
			}
			
			i = 0;
			ResultSet Signla = stmt.executeQuery("SELECT Signla FROM "+chooseTable+"");
			while(Signla.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][3] = buffer.append(Signla.getString("Signla"));
				i++;
			}
			
			i = 0;
			ResultSet Time = stmt.executeQuery("SELECT Time FROM "+chooseTable+"");
			while(Time.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][4] = buffer.append(Time.getString("Time"));
				i++;
			}
			
			i = 0;
			ResultSet Id = stmt.executeQuery("SELECT Id FROM "+chooseTable+"");
			while(Id.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][5] = buffer.append(Id.getString("Id"));
				i++;
			}
			
			i = 0;
			ResultSet Lat = stmt.executeQuery("SELECT Lat FROM "+chooseTable+"");
			while(Lat.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][6] = buffer.append(Lat.getString("Lat"));
				i++;
			}
			
			i = 0;
			ResultSet Lon = stmt.executeQuery("SELECT Lon FROM "+chooseTable+"");
			while(Lon.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][7] = buffer.append(Lon.getString("Lon"));
				i++;
			}
			
			i = 0;
			ResultSet Alt = stmt.executeQuery("SELECT Alt FROM "+chooseTable+"");
			while(Alt.next()){
				StringBuffer buffer = new StringBuffer();
				data[i][8] = buffer.append(Alt.getString("Alt"));
				i++;
			}
			
		} catch(SQLException e){
			System.err.print(e);
		}
		
		
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(736, 372));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane);
		
	}


	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String table = "csv_table";
					Table window = new Table(table);
					window.setVisible(true);
					window.setSize(770,440);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}



}
