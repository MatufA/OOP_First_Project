package main.java.writePack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.databasePack.Network;

public class WriteCsvEachLine implements Write{

	private List<List<Network>> database;
	private int size;
	String path;
	
	public WriteCsvEachLine(List<List<Network>> data) {
		database = new ArrayList<>();
		this.database.addAll(data);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		path = "Test\\output\\final_csv - " + df.format(date.getTime()) +".csv";
	}
	
	public WriteCsvEachLine(List<List<Network>> data, String path) {
		database = new ArrayList<>();
		this.database.addAll(data);
		this.path = path;
	}

	@Override
	public boolean write() {
		try {
			
			BufferedWriter makeFile = new BufferedWriter(new FileWriter(this.path));
			
			for (List<Network> list : database) {
				for (Network network : list) {
					makeFile.write(size++ +"," 
							+ network.getMac() + ","
							+ network.getSsid() + ","
							+ network.getLat() + ","
							+ network.getLon() + ","
							+ network.getAlt() + ","
							+ network.getTime() + ",");
					makeFile.newLine();
				}
			}
			makeFile.close();
			return true;
		} catch (IOException e) {
			e.getStackTrace();
			return false;
		}
	}

	public List<List<Network>> getDatabase() {
		return database;
	}

	public int getSize() {
		return size;
	}

}
