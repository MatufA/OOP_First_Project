package main.java.readPack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.databasePack.Network;

public class ReadFinalCsvNoHeader {
	private String path;
	private List<List<Network>> database;
	
	public ReadFinalCsvNoHeader(String pathToFile) {
		this.database = new ArrayList<>();
		this.path=pathToFile;
	}

	public String read() throws IOException{
		try {
			//Read file
			FileReader readFile = new FileReader(path);
			BufferedReader fileOpen = new BufferedReader(readFile);
			
			//Title index
			HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();
			keyIndex.put("Time",0);
			keyIndex.put("ID",1);
			keyIndex.put("Lat",2);
			keyIndex.put("Lon",3);
			keyIndex.put("Alt",4);
			keyIndex.put("WiFi networks",5);
			keyIndex.put("SSID",6);
			keyIndex.put("MAC",7);
			keyIndex.put("Frequncy",8);
			keyIndex.put("Signal",9);
			
			List<Network> data;
			String[] readLine;
			do {
				data = new ArrayList<>();
				readLine = fileOpen.readLine().split(",");
				int numberOfPoint = Integer.parseInt(readLine[5].trim());
				Network point;
				for (int i = 0; i < numberOfPoint; i++) {
				point = new Network(readLine[keyIndex.get("SSID")+i*4],
						readLine[keyIndex.get("MAC")+i*4], 
						Integer.parseInt(readLine[keyIndex.get("Frequncy")+i*4]), 
						Integer.parseInt(readLine[keyIndex.get("Signal")+i*4]), 
						readLine[keyIndex.get("Time")], 
						readLine[keyIndex.get("ID")],
						Double.parseDouble(readLine[keyIndex.get("Lat")]),
						Double.parseDouble(readLine[keyIndex.get("Lon")]),
						Double.parseDouble(readLine[keyIndex.get("Alt")]));
				data.add(point);
				}
				this.database.add(data);
				
			}while((readLine != null)&&(fileOpen.ready()));
			fileOpen.close();
			return "File has been readed successfully!";
		}catch(IOException|NullPointerException e) {
			e.printStackTrace();
			return path + "Error while reading";
			
		}
	}
	public List<List<Network>> getDatabase() {
		return database;
	}
}
