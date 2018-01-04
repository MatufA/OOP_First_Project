package main.java.writePack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.databasePack.Network;

public class WriteCsvEachLine implements Write{

	private List<List<Network>> database;
	private int size;
	public WriteCsvEachLine(List<List<Network>> data) {
		database = new ArrayList<>();
		this.database.addAll(data);
	}

	@Override
	public String write() {
		try {
			Date date = new Date();
			BufferedWriter makeFile = new BufferedWriter(new FileWriter("Test\\output\\final_csv - " + date.getTime() +".csv"));
			
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
			return "Write Csv successfully!";
		} catch (IOException e) {
			e.getStackTrace();
			return "Error While writing!";
		}
	}

	public List<List<Network>> getDatabase() {
		return database;
	}

	public int getSize() {
		return size;
	}

}
