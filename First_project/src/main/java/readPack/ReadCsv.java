package main.java.readPack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	private String pathToFileOrFolder;
	private int size;
	/**
	 * Instantiates a new read csv.
	 * No path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
 	public ReadCsv() throws IOException {
 		 this.database = new ArrayList<List<Network>>();
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
		this.database = new ArrayList<List<Network>>();
 		this.size = 0;
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
			///Read file
			FileReader readFile = new FileReader(path);
			BufferedReader fileOpen = new BufferedReader(readFile);

			//Temp String[] 
			String [] orFile = fileOpen.readLine().split(",");
			//Close session
			readFile.close();
			if(orFile[0].contains("Wiggle") || orFile[0].contains("Wigle") ){
				ReadCsvWiggle rcw = new ReadCsvWiggle(path);
				System.out.println("You are a Wigle File");
				rcw.read();
				database.addAll(rcw.getDatabase());
				size+=rcw.getSize();
			}else{
				System.out.println("You are not a Wigle File");
				ReadFinalCsvNoHeader rfcnh = new ReadFinalCsvNoHeader(path);
				rfcnh.read();
				database.addAll(rfcnh.getDatabase());
				size+=rfcnh.getSize();
			}

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

	public int getSize() {
		return size;
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
