package main.java.writePack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import main.java.databasePack.Network;

/**
 * The Class WriteCsv.
 * The class gets List<List<Network>> ,which provided by ReadCsv file.
 */
public class WriteCsv implements Write{
	/** The file table. */
	private List<List<Network>> _fileTable;
	String path;
	/**
	 * Instantiates a new write csv.
	 *
	 * @author adiel ,adi and yuda
	 */
	public WriteCsv(){
		this._fileTable = null;
		
	}
	
	/**
	 * Instantiates a new write csv.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList the csv list
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public WriteCsv(List<List<Network>> csvList) throws IndexOutOfBoundsException  {
		this._fileTable = csvList;
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		path = "final_csv - " + df.format(date.getTime()) +".csv";
	}
	
	/**
	 * Instantiates a new write csv.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList the csv list
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public WriteCsv(List<List<Network>> csvList, String path) throws IndexOutOfBoundsException  {
		this._fileTable = csvList;
		this.path = path;
	}
	
	@Override
	public boolean write() {
		try {
			
			BufferedWriter makeFile = new BufferedWriter(new FileWriter(path));
			String [] firstHeader = {"Time", "ID", "Lat", "Lon", "Alt", "WiFi networks"},
					secondHeader = {"SSID", "MAC", "Frequncy", "Signal"};
			//First required titles
			for (String newHeader : firstHeader) {
				makeFile.write(newHeader + " , ");
			}
			//Second required titles
			for (int i = 1; i < 11; i++) {
				for (String newHeader : secondHeader) {
					makeFile.write(newHeader +" " + i + " , ");
				}
			}
			makeFile.newLine();
			//Write to file
			if(this._fileTable.size() != 0) {
				for (List<Network> runList: this._fileTable) {
					if(runList.size() >= 1) {
						makeFile.write(runList.get(0).getTime() + " , " + runList.get(0).getId() + " , "
									+runList.get(0).getLat() + " , " + runList.get(0).getLon() + " , "
									+ runList.get(0).getAlt() + " , " + runList.size());
						for (Network network : runList) {
								makeFile.write( " , " + network.getSsid() + " , " + network.getMac() + " , " 
										+ network.getFrequncy() + " , " + network.getSignal());
						}
						makeFile.newLine();
					}
				}
				//close session
				makeFile.close();
				return true;
				}else {
					//close session
					makeFile.close();
					return false;
				}
			
		}catch(IndexOutOfBoundsException | IOException | NullPointerException e) {
			e.printStackTrace();
			return false;
		}
	}
	/*Get list of csv file*/
	public List<List<Network>> getFileTable() {
		return _fileTable;
	}
}
