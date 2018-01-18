package main.java.ExamplePack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.algorithmsPack.WifiSpotLocation;
import main.java.databasePack.Network;
import main.java.filtersPack.FilterByMAC;
import main.java.filtersPack.RemoveDuplicate;
import main.java.readPack.ReadFinalCsvNoHeader;
import main.java.writePack.WriteCsvEachLine;

/**
 * The Class Test.
 */
public class Test {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		/*ReadCsv gets csv file from WiggleWif only (path to file or folder)*/
		ReadFinalCsvNoHeader file = new ReadFinalCsvNoHeader("Test\\database\\_comb_all_BM2_.csv");
		//file.read();
		/*ReadFinalCsvNoHeader fileTest = new ReadFinalCsvNoHeader("Test\\input\\_comb_no_gps_ts1.csv");
		fileTest.read();*/
		/*WriteCsv gets List<List<Network>> ,which provided by ReadCsv file.*/
		/*WriteCsv write = new WriteCsv(file.getDatabase());
		write.write();*/
		/*Build csvFilter and then use one of the filters, but it's possible to use it directly*/
		
		List<List<Network>> data = new ArrayList<>();
		System.out.println(file.read());
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
		/*The class gets List<String []> ,HashMap<String, Integer> which provided by Filter*/
		/*WriteKml kml = new WriteKml(mac.getFilteredFile());*/
		/*Kml crated (with time stamp)*/
		/*System.out.println(kml.write());*/

	}

}

