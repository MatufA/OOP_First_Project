package main.java.GUIPack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.List;

import main.java.databasePack.MainDB;
import main.java.databasePack.Network;

public class Table extends JFrame {
	/**
	 * @author adiel, adi and yuda
	 * Show all database on table
	 */
	private static final long serialVersionUID = 2032689923506912581L;
	JTable table;
	private int count;

	public Table(MainDB database) {
		setBackground(Color.WHITE);                         
		setBounds(100, 100, 770, 440);
		setTitle("CSV Table");
		setResizable(false);
		
		setLayout(new FlowLayout());
		String[] columnNames = {"Time","Id","MAC","SSID","Latitude",
				                "Longitude","Altitude","Frequncy","Signal"};
		
		Object [][] data = new Object[database.get_size()][9];

		if(!database.isEmpty()) {
			count = 0;
			List<List<Network>> temp = new ArrayList<>(database.getdatabase()); 
			for (List<Network> list : temp) {
				for (Network net : list ) {
					data[count][0] = net.getTime();
					data[count][1] = net.getId();
					data[count][2] = net.getMac();
					data[count][3] = net.getSsid();
					data[count][4] = net.getLat();
					data[count][5] = net.getLon();
					data[count][6] = net.getAlt();
					data[count][7] = net.getFrequncy();
					data[count][8] = net.getSignal();
					count++;
				}
			}
		}
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(736, 372));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane);
		
	}
}
