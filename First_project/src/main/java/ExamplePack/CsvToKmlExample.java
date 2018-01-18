package main.java.ExamplePack;

import java.io.IOException;
import main.java.readPack.ReadCsv;
import main.java.writePack.WriteCsv;
import main.java.writePack.WriteKml;

public class CsvToKmlExample {

	public static void main(String[] args) throws IOException {
		/*ReadCsv gets csv file from WiggleWif only (path to file or folder)*/
		ReadCsv file = new ReadCsv("Test\\database\\_comb_all_BM2_.csv");
		file.read();
		
		/*WriteCsv gets List<List<Network>> ,which provided by ReadCsv file.*/
		WriteCsv write = new WriteCsv(file.getDatabase());
		write.write();
		
		/*Kml crated (with time stamp)*/
		WriteKml kml = new WriteKml(file.getDatabase());
		System.out.println(kml.write());
	}

}
