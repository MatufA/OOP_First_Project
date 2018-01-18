package test.java.writePack;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class WriteCsvTest.
 */
public class WriteCsvTest {

	
	/**
	 * Test write csv list of list of network.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	@Test
	public void testWriteCsvListOfListOfNetwork() throws FileNotFoundException {
		String [] firstHeader = {"Time", "ID", "Lat", "Lon", "Alt", "WiFi networks","SSID" ,"MAC"  ,"Frequncy"  ,"Signal"};
		FileReader fr;
		try {
			fr = new FileReader ("Test\\junitTest\\WigleWifi_20171031013535.csv");
			BufferedReader br = new BufferedReader(fr);
			String str;
			str = br.readLine();
			String[] st = str.split(",");
			for (int i = 0; i < firstHeader.length; i++) {
				if(!st[i].trim().contains(firstHeader[i])){
					fail("Invalid writeCsv");
				}
			}
			br.close();

		} catch (IOException e) {
			fail("Invalid writeCsv");
		}
	}

}
