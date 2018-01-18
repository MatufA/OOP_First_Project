package main.java.ExamplePack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.algorithmsPack.WifiSpotLocation;
import main.java.databasePack.Network;
import main.java.filtersPack.FilterByMAC;
import main.java.filtersPack.RemoveDuplicate;
import main.java.readPack.ReadCsv;
import main.java.writePack.WriteCsvEachLine;

public class UserLocationExample {

	public static void main(String[] args) throws IOException {
		ReadCsv file = new ReadCsv("Test\\database\\_comb_all_BM2_.csv");
		List<List<Network>> data = new ArrayList<>();
		file.read();
		RemoveDuplicate duplicate = new RemoveDuplicate(file.getDatabase());
		System.out.println(duplicate.filter());
		System.out.println(duplicate.getSize());
		List<List<Network>> database = duplicate.getFilteredFile();
		List<Network>tempLine;
		
		for (List<Network> list : database) {
			tempLine = new ArrayList<>();
			for (Network network : list) {
				FilterByMAC mac = new FilterByMAC(file.getDatabase(),network.getMac());
				System.out.println(mac.filter());
				WifiSpotLocation point = new WifiSpotLocation(mac.getFilteredFile());
				tempLine.add(point.WeightedAverage());
			}
			data.add(tempLine);
		}
		WriteCsvEachLine check = new WriteCsvEachLine(data);
		System.out.println(check.write());
	}

}
