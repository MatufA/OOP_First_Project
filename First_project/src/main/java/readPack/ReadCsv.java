package main.java.readPack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import main.java.databasePack.Network;

import static java.nio.file.StandardCopyOption.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadCsv.
 * This class gets csv file from WiggleWif only (path to file or folder)
 */
public class ReadCsv {
	/*Csv database*/
	private List<List<Network>> database = new ArrayList<List<Network>>();
	/*Object of type Network*/
	private Network wifiObj;
	private String pathToFileOrFolder;
	private int size;
	/**
	 * Instantiates a new read csv.
	 * No path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
 	public ReadCsv() throws IOException {
 		 this.database = new ArrayList<List<Network>>();
 		 this.wifiObj = new Network();
 		 this.pathToFileOrFolder = null;
 		 this.size = 0;
	}
	/**
	 * Instantiates a new read csv.
	 *
	 * @author adiel, adi and yuda
	 * @param path
	 */
	public ReadCsv(String path) {
		this.pathToFileOrFolder = path;
	}
	/**
	 * Adding more files of folders
	 * @param path
	 * @return 
	 */
	public String add(String path) {
		try {
			pathToFileOrFolder = path;
			read();
			return "Done - Adding successfully";
		} catch (IOException e) {
			e.printStackTrace();
			return "Done - Adding failure";
		}
		
	}
	/**
	 * @author adiel, adi and yuda
	 * {@link} https://www.tutorialspoint.com/java/io/file_listfiles.htm
	 * {@link} http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void read() throws IOException {
		try {
			/**
			 * Read all file from path
			 * Function copy from https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java 
			 **/
			List<File> filesInFolder = Files.walk(Paths.get(pathToFileOrFolder))
					.filter(Files::isRegularFile)
					.map(Path::toFile)
					.collect(Collectors.toList());

			//Folder is empty
			if(filesInFolder.isEmpty()) System.err.println("Folder is empty!");

			//read all file in folder
			else while(!filesInFolder.isEmpty()) {
					System.out.println(filesInFolder.get(0).getName());
					String typeOfFile = fileType(filesInFolder.get(0).getName());
					String pathOfEachFile = filesInFolder.get(0).getPath();

					if (typeOfFile.equals("CSV"))
						csvInput(pathOfEachFile);
					else unauthorizedFile(filesInFolder.get(0).getName());

					filesInFolder.remove(0);
				}
			
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Csv input.
	 * @param csvPath
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void csvInput(String csvPath) throws IOException {
		try {
			File input_csv = new File (csvPath);
			File output_csv = new File ("temp_csv.csv");
			Files.copy(input_csv.toPath(), output_csv.toPath(), REPLACE_EXISTING);

			System.out.println("The CSV copy successfully!");
			getOrder("temp_csv.csv");
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("\nSorry, somethings went wrong! \nPlease check if your file is corrupted");
		}
	}
	/**
	 * Order the csv.
	 *
	 * @param path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void getOrder(String path) throws IOException{
		try {
			/*//Read file
			FileReader readFile = new FileReader(path);
			BufferedReader fileOpen = new BufferedReader(readFile);
			String model = "NaN" , stop = null;
			
			//Temp list
			List<Network> line_of_table = new ArrayList<Network>();
			//Unsorted list
			List<List<Network>> fileTable = new ArrayList<List<Network>>();

			//Title index
			HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();

			//Temp String[] 
			String [] orFile = fileOpen.readLine().split(",");*/
			if(orFile[0].contains("Wiggle") || orFile[0].contains("Wigle") ){
				ReadCsvWiggle rcw = new ReadCsvWiggle(path);
				System.out.println("You are a Wigle File");
				rcw.read();
			}else{
				System.out.println("You are not a Wigle File");
				ReadFinalCsvNoHeader rfcnh = new ReadFinalCsvNoHeader(path);
				rfcnh.read();
			}
			
/*			//Array of required title
			String[] title = {"MAC","SSID","AuthMode","FirstSeen","Channel","RSSI","CurrentLatitude",
					"CurrentLongitude","AltitudeMeters"};

			//Get ID
			for (String temp_cell : orFile) {
				if(temp_cell != null && temp_cell.contains("model")) {	
					model = temp_cell.split("=")[1].trim();
					break;
				}
			}
			orFile = fileOpen.readLine().split(",");

			//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
			for (int i = 0 ; i < title.length; i++) {
				keyIndex.put(orFile[i].trim(), i);
			}

			stop = fileOpen.readLine();
			if(stop != null) orFile = stop.split(",");
			
			//Create table
			while(stop != null) {
				//Add to line of list
				wifiObj = new Network(orFile[keyIndex.get("SSID")] , orFile[keyIndex.get("MAC")] , 
						Integer.parseInt(orFile[keyIndex.get("Channel")]) , 
						Integer.parseInt(orFile[keyIndex.get("RSSI")]), 
						orFile[keyIndex.get("FirstSeen")], model, 
						Double.parseDouble(orFile[keyIndex.get("CurrentLatitude")]) ,
						Double.parseDouble(orFile[keyIndex.get("CurrentLongitude")]) ,
						Double.parseDouble(orFile[keyIndex.get("AltitudeMeters")]));
				
				line_of_table.add(wifiObj);
				stop = fileOpen.readLine();
				if(stop != null) orFile = stop.split(",");
			}

			//Move to main table, _fileTable, by date
			int fromIndex = 0;
			for (int i = 0 ; i < line_of_table.size()-1 ; i++) {
				if(!line_of_table.get(i).getTime().trim().equals(line_of_table.get(i+1).getTime().trim())) {
					fileTable.add(line_of_table.subList(fromIndex, i));
					fromIndex = i+1;
				}
			}

			if(fromIndex == 0 || fromIndex < line_of_table.size())
				fileTable.add(line_of_table.subList(fromIndex, line_of_table.size()));
			//Sort by signal
			for (List<Network> obj : fileTable) {
				Collections.sort(obj,new Comparator<Network>() {
					public int compare(Network wifi1, Network wifi2) {
						if(Math.abs(wifi1.getSignal()) < Math.abs(wifi2.getSignal()))
							return -1;
						if(Math.abs(wifi1.getSignal()) == Math.abs(wifi2.getSignal()))
							return 0;
						return 1;
					}
				});
			}
			
			//Sort by Date
			for (List<Network> obj : fileTable) {
				Collections.sort(obj,new Comparator<Network>() {
					public int compare(Network wifiLine1, Network wifiLine2) {
						try {
							DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							Date wifi1Time =  df1.parse(wifiLine1.getTime().trim());
							Date wifi2Time =  df1.parse(wifiLine2.getTime().trim());
							if(wifi2Time.before(wifi1Time))
								return -1;
							if(wifi1Time.equals(wifi2Time))
								return 0;
							return 1;
						}catch (ParseException e) {
							e.getCause();
							return 0;
						}
					}
				});
				}
			//Max of 10 element in each sublist
			for (List<Network> net : fileTable) {
				if(net.size() > 10) {
					this.database.add(net.stream()
							.limit(10)
							.collect(Collectors.toList()));
				}else {
					this.database.add(net);
					
				}
			}*/

			//Close session
			readFile.close();
			System.out.println("The CSV creat successfully!");
			//Delete temp file
			File delete = new File("test_csv.csv");
			Files.deleteIfExists(delete.toPath());

		}catch(NullPointerException|IOException e) {
			System.err.println(e.getMessage());
			System.err.println("Sorry, somethings went wrong! \nPlease check if your file is corrupted");
		}
	}
	
	/**
	 * Gets the file table.
	 * @return database
	 */
	public List<List<Network>> getDatabase() {
		return database;
	}

	/**
	 * File type.
	 *
	 * @param filePath the file path
	 * @return type of file
	 */
	public String fileType (String filePath) {
		//Cut the type from file (exemple: txt)
		int length = filePath.length()-3;
		String type = filePath.substring(length, filePath.length());
		return type.toUpperCase();
	}

	/**
	 * Unauthorized file.
	 * @param unauthorizedFile
	 */
	//Check if file type is unauthorized 
	public void unauthorizedFile(String unauthorizedFile) {
		System.err.println(unauthorizedFile+" - " +"Unauthorized File, please change the file to authorized file (csv,txt or kml)!"); 

	}

}
