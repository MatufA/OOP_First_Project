package main.java.GUIPack;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import main.java.readPack.ReadCsv;
import main.java.writePack.WriteCsv;
import main.java.writePack.WriteKml;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.prism.impl.shape.OpenPiscesRasterizer;

import main.java.databasePack.MainDB;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;
import main.java.databasePack.Network;
import main.java.filtersPack.FilterByDate;
import main.java.filtersPack.FilterByID;
import main.java.filtersPack.FilterByLocation;
import main.java.filtersPack.FilterByMAC;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JFormattedTextField;


public class filterPage extends JFrame {

	/**
	 * 
	 */
	private static boolean flag = false;

	private static final long serialVersionUID = 2L;
	private JPanel contentPane;

	private JLabel addIcon, filterIcon;
	private JLabel mapIcon, tableIcon;
	private JTable table;
	private static HashMap<File, FileTime> files = new HashMap<>();

	private MainDB database = new MainDB();
	private static MainDB filterDB = new MainDB();

	private FilterByID id = new FilterByID(database.getdatabase(), "");
	private FilterByLocation loc = new FilterByLocation(database.getdatabase(), 0.0, 0.0, 0.0);
	private FilterByDate datime = new FilterByDate(database.getdatabase(), null, null);


	private JLabel logoff;
	private JLabel userIcon;
	private JLabel modemIcon;
	private JLabel infoIcon;
	private static File[] selectedFile;
	private JPanel upload;
	private JTextField txtfilter;
	private JTextField choosemac;
	private JComboBox filterBox1;
	private JComboBox operatorBox1;

	private JButton ok1;
	private JButton ok2;
	private JComboBox operatorBox2;
	private JComboBox filterBox2;
	private JTextField input1;
	private JTextField input2;
	private JTextField input3;
	private JTextField input4;

	private static String selectedfilter1=null;
	private static String selectedfilter2=null;
	private JTextField latIn1;
	private JTextField latIn2;
	private JTextField lonIn1;
	private JTextField lonIn2;
	private JTextField altIn1;
	private JTextField altIn2;
	private JButton btnCancelFilter;
	private JTextField latIn3;
	private JTextField latIn4;
	private JTextField lonIn3;
	private JTextField lonIn4;
	private JTextField altIn3;
	private JTextField altIn4;
	private JTextField databaseSize;
	private JLabel dbSize;
	private JButton dbSizeBtn;
	private JButton dbFilterSizeBtn;
	private JLabel dbFilterSize;
	private JLabel Background;

	/**
	 * Launch the application.
	 */
	public static void main(File[] selectedFile) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage(selectedFile);

					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @author adiel, adi and yuda
	 * Filter frame, the main page
	 * @return 
	 * @throws IOException 
	 */
	public filterPage(File[] selectedFile) throws IOException {
		setResizable(false);
		filterPage.selectedFile = selectedFile;
		checkModifiedTime(filterPage.selectedFile);
		setTitle("GEO-App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(filterPage.class.getResource("/main/java/GUIPack/images/app-earth-icon.png")));

		//setIconImage(Toolkit.getDefaultToolkit().getImage(filterPage.class.getResource("/main/java/GUIPack/images/app-earth-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);

		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(null);

		setContentPane(contentPane);

		new JLayeredPane();
		String[] columnNames = {"Time","ID","MAC","SSID","Latitude",
				"Longitude","Altitude","Frequncy","Signal"};		
		Object [][] data = new Object[database.get_size()][9];

		upload = new JPanel();
		upload.setBounds(100, 100, 630, 421);
		upload.setVisible(false);

		Vector<String> filternames = new Vector<>();

		choosemac = new JTextField();
		choosemac.setVisible(false);
		choosemac.setBounds(93, 100, 121, 23);

		String newstr = "";
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		/*-------------------------------------------->user icon<---------------------------------------------------*/
		userIcon = new JLabel("");
		userIcon.setBounds(9, 266, 50, 50);
		contentPane.add(userIcon);
		userIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Location_50px.png")));
		/*----------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->info icon<---------------------------------------------------*/

		infoIcon = new JLabel("");
		infoIcon.setBounds(19, 391, 32, 32);
		contentPane.add(infoIcon);
		infoIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Info_32px.png")));
		/*-----------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->filter icon<---------------------------------------------------*/
		filterIcon = DefaultComponentFactory.getInstance().createLabel("");
		filterIcon.setBounds(17, 53, 32, 32);
		contentPane.add(filterIcon);
		filterIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/filter_32px.png")));
		filterIcon.setForeground(new Color(248, 248, 255));
		/*-----------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->map icon<---------------------------------------------------*/
		mapIcon = DefaultComponentFactory.getInstance().createLabel("");
		mapIcon.setBounds(17, 96, 32, 32);
		contentPane.add(mapIcon);
		mapIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Globe_32px.png")));
		/*-----------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->table icon<---------------------------------------------------*/
		tableIcon = DefaultComponentFactory.getInstance().createLabel("");
		contentPane.add(tableIcon);

		tableIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Table t = new Table(database);
				t.setVisible(true);
			}
		});
		tableIcon.setBounds(17, 222, 32, 32);
		contentPane.add(tableIcon);

		tableIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Bulleted List_32px.png")));
		tableIcon.setForeground(new Color(248, 248, 255));
		/*-------------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->add icon<---------------------------------------------------*/
		addIcon = DefaultComponentFactory.getInstance().createLabel("");
		addIcon.setBounds(17, 10, 32, 32);
		contentPane.add(addIcon);
		addIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					System.out.println(database.get_size());
					uploadfile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Plus Math_32px.png")));
		addIcon.setForeground(new Color(248, 248, 255));
		/*---------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->KML icon<---------------------------------------------------*/
		JLabel kmlIcon = new JLabel("");
		kmlIcon.setBounds(17, 182, 32, 32);
		contentPane.add(kmlIcon);
		kmlIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Custom button text
				Object[] options = {"Main Database",
						"Filtered database",
				"Exit"};
				int choose = JOptionPane.showOptionDialog(contentPane,
						"Would you like export Main or Filtered database?",
						"Export",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[2]);

				switch (choose) {
				case 0:
					// parent component of the dialog
					JFrame parentFrame = new JFrame();

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");   
					int userSelection = fileChooser.showSaveDialog(parentFrame);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();
						String path = fileToSave.getAbsolutePath();
						if(!path.substring(path.length()-4, path.length()).equalsIgnoreCase(".kml")) path += ".kml";
						System.out.println("Save as file: " + path);
						WriteKml write = new WriteKml(database.getdatabase() ,path);
						if(!database.isEmpty()) write.write();
					}
					break;
				case 1:
					// parent component of the dialog
					parentFrame = new JFrame();

					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Specify a file to save");   

					userSelection = fc.showSaveDialog(parentFrame);

					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fc.getSelectedFile();
						String path = fileToSave.getAbsolutePath();
						if(path.substring(path.length()-4).equalsIgnoreCase(".kml")) path += ".kml";
						System.out.println("Save as file: " + path);
						WriteKml write = new WriteKml(filterPage.filterDB.getdatabase() ,path);
						if(!filterPage.filterDB.isEmpty()) write.write();
						System.out.println("----------------"+filterPage.filterDB.isEmpty()+"------------");
					}
					break;
				default:
					break;
				}
			}
		});
		kmlIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/KML_32px.png")));
		/*---------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->csv icon<---------------------------------------------------*/
		JLabel csvButton = new JLabel("");
		csvButton.setBounds(17, 139, 32, 32);
		contentPane.add(csvButton);
		csvButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Custom button text
				Object[] options = {"Main Database",
						"Filtered database",
				"Exit"};
				int choose = JOptionPane.showOptionDialog(contentPane,
						"Would you like export Main or Filtered database?",
						"Export",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[2]);

				switch (choose) {
				case 0:
					// parent component of the dialog
					JFrame parentFrame = new JFrame();
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");   
					int userSelection = fileChooser.showSaveDialog(parentFrame);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();
						String path = fileToSave.getAbsolutePath();
						if(!path.substring(path.length()-4, path.length()).equalsIgnoreCase(".csv")) path += ".csv";
						System.out.println("Save as file: " + path);
						WriteCsv write = new WriteCsv(database.getdatabase() ,path);
						if(!database.isEmpty()) write.write();
					}
					break;
				case 1:
					System.out.println("-------------->"+"filter"+"<---------");
					// parent component of the dialog
					JFrame parent = new JFrame();
					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Specify a file to save");   
					userSelection = fc.showSaveDialog(parent);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fc.getSelectedFile();
						String path = fileToSave.getAbsolutePath();
						//System.out.println("Save as file: " + path);
						if(!path.substring(path.length()-4).equalsIgnoreCase(".csv")) path += ".csv";
						System.out.println("Save as file: " + path);
						System.out.println("FILTERDB: "+filterPage.filterDB.isEmpty());
						WriteCsv write = new WriteCsv(filterPage.filterDB.getdatabase() ,path);
						if(!filterPage.filterDB.isEmpty()) write.write();
						System.out.println("FILTERDB:222222222 "+filterPage.filterDB.isEmpty());
					}
					break;
				default:
					break;
				}
			}
		});
		csvButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/CSV_32px.png")));
		/*----------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->modem icon<---------------------------------------------------*/
		modemIcon = new JLabel("");
		modemIcon.setBounds(9, 330, 50, 50);
		contentPane.add(modemIcon);
		modemIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Wi-Fi Router_50px.png")));
		/*----------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->logoff icon<---------------------------------------------------*/
		logoff = new JLabel("");
		logoff.setBounds(752, 1, 32, 32);
		contentPane.add(logoff);
		logoff.setForeground(SystemColor.inactiveCaption);
		logoff.setBackground(SystemColor.window);
		logoff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int yesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to close the application?", "Exit" ,JOptionPane.YES_NO_OPTION);
				switch (yesOrNo) {
				case 0:
					System.exit(0);
					break;

				default:
					break;
				}
			}
		});
		logoff.setHorizontalAlignment(SwingConstants.LEFT);
		logoff.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Logout Rounded Up_32px.png")));
		/*----------------------------------------------------------------------------------------------------------*/
		/*----------------------------------------------->filter<---------------------------------------------------*/
		txtfilter = new JTextField();
		txtfilter.setBounds(99, 386, 575, 32);
		contentPane.add(txtfilter);
		txtfilter.setFont(new Font("Calibri Light", Font.BOLD, 16));
		txtfilter.setColumns(10);


		table = new JTable(data, columnNames);
		table.setBounds(0, 0, -2, 0);
		contentPane.add(table);
		table.setPreferredScrollableViewportSize(new Dimension(736, 372));
		table.setFillsViewportHeight(true);

		input1 = new JTextField();
		input1.setBounds(186, 151, 119, 20);
		input1.setVisible(false);
		contentPane.add(input1);
		input1.setColumns(10);

		input2 = new JTextField();
		input2.setBounds(186, 182, 119, 20);
		input2.setVisible(false);
		input2.setColumns(10);
		contentPane.add(input2);

		ok1 = new JButton("OK");
		ok1.setBounds(254, 249, 50, 32);
		ok1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		/*-------------------------------------------->first filter<---------------------------------------------------*/
				if(selectedfilter1 != null){
					if(selectedfilter1 == "Time"){
						System.out.println(input1.getText() + "   " + input2.getText());
						datime = new FilterByDate(database.getdatabase(), input1.getText() ,input2.getText() );

						if(operatorBox1.getSelectedItem().equals("NOT")){
							System.out.println("YOU ARE IN NOT");
							datime.filterNOT();
							filterDB.removeAll();
							filterDB.add(datime.getFilteredFile());
							//filterDB.addList();
						}else{
							System.out.println("YOU ARE IN ");
							datime.filter();
							filterDB.removeAll();
							filterDB.add(datime.getFilteredFile());

						}

					}else if(selectedfilter1 == "Device"){
						System.out.println(input1.getText());
						id = new FilterByID(database.getdatabase(), input1.getText());

						System.out.println(filterDB.get_size()+ "     filterDB sizeA");
						filterDB.removeAll();
						System.out.println(filterDB.get_size()+ "     filterDB sizeB");

						if(operatorBox1.getSelectedItem().equals("NOT")){

							System.out.println("YOU ARE IN NOT");
							id.filterNOT();
							filterDB.removeAll();
							filterDB.add(id.getFilteredFile());

						}else{
							System.out.println("YOU ARE IN ");
							id.filter();
							filterDB.add(id.getFilteredFile());
						}


					}else if(selectedfilter1 == "Location"){
						double lat1 = Double.parseDouble(latIn1.getText());
						double lat2 = Double.parseDouble(latIn2.getText());
						double lon1 = Double.parseDouble(lonIn1.getText());
						double lon2 = Double.parseDouble(lonIn2.getText());
						double radius = (double)Math.sqrt((double)Math.pow((lat1 - lat2), 2.0) + (double)Math.pow((lon1 - lon2), 2.0));
						double lon = (lon1+lon2)/2;
						double lat = (lat1+lat2)/2;
						loc = new FilterByLocation(database.getdatabase(), radius ,lat ,lon);

						if(operatorBox1.getSelectedItem().equals("NOT")){
							System.out.println("YOU ARE IN NOT");
							Thread calc = new Thread(new Runnable() {
								
								@Override
								public void run() {
									loc.filterNOT();
									filterDB.add(loc.getFilteredFile());
								}
							});
							calc.start();
						}else{
							System.out.println("YOU ARE IN ");
							Thread calc = new Thread(new Runnable() {
								
								@Override
								public void run() {
									loc.filter();
									filterDB.add(loc.getFilteredFile());
								}
							});
							calc.start();	
						}

					}

				}
		/*--------------------------------------------------------------------------------------------------------------*/
		/*-------------------------------------------->second filter<---------------------------------------------------*/
				if(selectedfilter2 != "Choose filter.."){
					if(selectedfilter2 == "Time"){
						System.out.println(input3.getText() + "   " + input4.getText());
						datime = new FilterByDate(database.getdatabase(), input3.getText() ,input4.getText() );
						if(operatorBox2.getSelectedItem().toString().contains("AND")){
							
							if(operatorBox2.getSelectedItem().toString().equals("AND")){


							}
							else{//AND NOT

							}
						}else if(operatorBox2.getSelectedItem().toString().contains("OR")){

							if(operatorBox2.getSelectedItem().toString().equals("OR")){


							}
							else{//OR NOT

							}
						}


					}

					else if(selectedfilter2 == "Device"){
						System.out.println(input3.getText());
						id = new FilterByID(database.getdatabase(), input3.getText());
						
						if(operatorBox2.getSelectedItem().toString().contains("AND")){

							if(operatorBox2.getSelectedItem().toString().equals("AND")){


							}
							else{//AND NOT

							}
						}else if(operatorBox2.getSelectedItem().toString().contains("OR")){

							if(operatorBox2.getSelectedItem().toString().equals("OR")){
								System.out.println("YOU ARE IN OR ");
								System.out.println(id.getSize()+ "  id size ------ before filter OR");
								id.filter();
								System.out.println(id.getSize()+ "  id size ---------- after filterNot");
								filterDB.add(id.getFilteredFile());

							}
							else if(operatorBox2.getSelectedItem().toString().equals("OR NOT")){ //OR NOT
								System.out.println("YOU ARE IN NOT OR");
								System.out.println(id.getSize()+ "  id size ------ before filter OR NOT");
								FilterByID otherID = new FilterByID(database.getdatabase(), input3.getText());
								otherID.filterNOT();
								filterDB.add(otherID.getFilteredFile());
								System.out.println(otherID.getSize()+ "  id size ---------- after filterNot");
								
							}
						}
						/*else{
							System.out.println("YOU ARE IN ");
							id.filter();
							System.out.println(id.getSize()+ "  ---------size");
							filterPage.filterDB.removeAll();
							filterPage.filterDB.add(id.getFilteredFile());
							System.out.println(filterPage.filterDB.isEmpty());
						}*/


					}

					else if(selectedfilter2 == "Location"){

						double lat = Double.parseDouble(latIn3.getText());
						double lon = Double.parseDouble(lonIn4.getText());
						loc = null;// new FilterByLocation(database.getdatabase(), 4.0 ,lat ,lon ); //new FilterByLocation(database.getdatabase(), input1.getText());

						System.out.println("Lat is: "+ latIn3.getText() + " " +latIn4.getText() );
						System.out.println("Lon is: "+ lonIn3.getText() + " " +lonIn4.getText() );
						System.out.println("Alt is: "+ altIn3.getText() + " " +altIn4.getText() );

						if(operatorBox2.getSelectedItem().equals("AND")){
							/*System.out.println("YOU ARE IN NOT");
							location2.filterNOT();
							filterDB = new MainDB();//    (time.getFilteredFile());
							filterDB.removeAll();
							filterDB.add(location2.getFilteredFile());*/

						}
						else if(operatorBox2.getSelectedItem().equals("AND NOT")){

						}
						else if(operatorBox2.getSelectedItem().equals("OR")){

						}
						else if(operatorBox2.getSelectedItem().equals("OR NOT")){

						}
						else{
							System.out.println("YOU ARE IN ");
							loc.filter();
							filterDB = new MainDB();//    (time.getFilteredFile());
							filterDB.removeAll();
							filterDB.add(loc.getFilteredFile());
						}

					}

				}

			}
		});
		ok1.setFont(new Font("Tahoma", Font.BOLD, 10));
		ok1.setVisible(false);
		contentPane.add(ok1);
		/*----------------------------------------------------------------------------------------------------------------*/
		operatorBox2 = new JComboBox();
		operatorBox2.setBounds(405, 112, 77, 20);
		operatorBox2.setVisible(false);
		contentPane.add(operatorBox2);
		operatorBox2.addItem("Operator..");
		operatorBox2.addItem("AND");
		operatorBox2.addItem("AND NOT");
		operatorBox2.addItem("OR");
		operatorBox2.addItem("OR NOT");

		input3 = new JTextField();
		input3.setBounds(503, 151, 119, 20);
		input3.setVisible(false);
		input3.setColumns(10);
		contentPane.add(input3);

		input4 = new JTextField();
		input4.setBounds(503, 182, 119, 20);
		input4.setVisible(false);
		input4.setColumns(10);
		contentPane.add(input4);

		ok2 = new JButton("OK");
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(selectedfilter2 != "Choose filter.."){

					if(selectedfilter2 == "Time"){
						System.out.println(input3.getText() + "   " + input4.getText());
						datime = new FilterByDate(database.getdatabase(), input3.getText() ,input4.getText() );

						if(operatorBox2.getSelectedItem().toString().contains("AND")){

							if(operatorBox2.getSelectedItem().toString().equals("AND")){


							}
							else{//AND NOT

							}
						}else if(operatorBox2.getSelectedItem().toString().contains("OR")){

							if(operatorBox2.getSelectedItem().toString().equals("OR")){


							}
							else{//OR NOT

							}
						}


					}

					else if(selectedfilter2 == "Device"){
						System.out.println(input3.getText());
						id = new FilterByID(database.getdatabase(), input3.getText());

						if(operatorBox2.getSelectedItem().toString().contains("AND")){

							if(operatorBox2.getSelectedItem().toString().equals("AND")){


							}
							else{//AND NOT

							}
						}else if(operatorBox2.getSelectedItem().toString().contains("OR")){

							if(operatorBox2.getSelectedItem().toString().equals("OR")){
								System.out.println("YOU ARE IN OR ");
								id.filter();
								System.out.println(id.getSize()+ "  ---------size");
								filterPage.filterDB.add(id.getFilteredFile());
								System.out.println(filterPage.filterDB.isEmpty());

							}
							else{//OR NOT

							}
						}
						else{
							System.out.println("YOU ARE IN ");
							id.filter();
							System.out.println(id.getSize()+ "  ---------size");
							filterPage.filterDB.removeAll();
							filterPage.filterDB.add(id.getFilteredFile());
							System.out.println(filterPage.filterDB.isEmpty());
						}


					}

					else if(selectedfilter2 == "Location"){

						double lat = Double.parseDouble(latIn3.getText());
						double lon = Double.parseDouble(lonIn4.getText());
						//FilterByLocation location2 = new FilterByLocation(database.getdatabase(), 4.0 ,lat ,lon ); //new FilterByLocation(database.getdatabase(), input1.getText());

						System.out.println("Lat is: "+ latIn3.getText() + " " +latIn4.getText() );
						System.out.println("Lon is: "+ lonIn3.getText() + " " +lonIn4.getText() );
						System.out.println("Alt is: "+ altIn3.getText() + " " +altIn4.getText() );

						if(operatorBox2.getSelectedItem().equals("AND")){
							/*System.out.println("YOU ARE IN NOT");
							location2.filterNOT();
							filterDB = new MainDB();//    (time.getFilteredFile());
							filterDB.removeAll();
							filterDB.add(location2.getFilteredFile());*/

						}
						else if(operatorBox2.getSelectedItem().equals("AND NOT")){

						}
						else if(operatorBox2.getSelectedItem().equals("OR")){

						}
						else if(operatorBox2.getSelectedItem().equals("OR NOT")){

						}
						else{
							System.out.println("YOU ARE IN ");
							//	location2.filter();
							filterDB = new MainDB();//    (time.getFilteredFile());
							filterDB.removeAll();
							//filterDB.add(location2.getFilteredFile());
						}

					}

				}

			}
		});
		ok2.setBounds(572, 249, 50, 32);
		ok2.setFont(new Font("Tahoma", Font.BOLD, 10));
		ok2.setVisible(false);
		contentPane.add(ok2);

		/*		input1.setVisible(false);
		input2.setVisible(false);
		ok1.setVisible(false);
		 */
		JLabel Lat1 = new JLabel("Choose Lat:");
		Lat1.setBounds(85, 153, 73, 15);
		Lat1.setForeground(Color.WHITE);
		Lat1.setVisible(false);
		Lat1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(Lat1);

		JLabel Lon1 = new JLabel("Choose Lon:");
		Lon1.setBounds(85, 184, 76, 15);
		Lon1.setForeground(Color.WHITE);
		Lon1.setVisible(false);
		Lon1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(Lon1);

		JLabel Alt1 = new JLabel("Choose Alt:");
		Alt1.setBounds(85, 216, 73, 15);
		Alt1.setForeground(Color.WHITE);
		Alt1.setVisible(false);
		Alt1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(Alt1);



		filterBox1 = new JComboBox();
		filterBox1.setBounds(186, 112, 119, 20);
		filterBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedfilter1 = (String) filterBox1.getSelectedItem();
				System.out.println("You seleted : " + selectedfilter1);
				if(selectedfilter1.equals("Device")){
					btnCancelFilter.setVisible(true);
					input1.setText("");
					input2.setText("");
					latIn1.setText("");
					latIn2.setText("");
					lonIn1.setText("");
					lonIn2.setText("");
					altIn1.setText("");
					altIn2.setText("");
					latIn1.setVisible(false);
					latIn2.setVisible(false);
					lonIn1.setVisible(false);
					lonIn2.setVisible(false);
					altIn1.setVisible(false);
					altIn2.setVisible(false);
					Lat1.setVisible(false);
					Lon1.setVisible(false);
					Alt1.setVisible(false);
					input2.setVisible(false);

					btnCancelFilter.setVisible(true);
					input1.setVisible(true);
					String value1 = (String)input1.getText();
					ok1.setVisible(true);

				}
				else if(selectedfilter1 == "Location"){
					btnCancelFilter.setVisible(true);
					input1.setText("");
					input2.setText("");
					latIn1.setText("");
					latIn2.setText("");
					lonIn1.setText("");
					lonIn2.setText("");
					altIn1.setText("");
					altIn2.setText("");
					input1.setVisible(false);
					input2.setVisible(false);

					latIn1.setVisible(true);
					latIn2.setVisible(true);
					lonIn1.setVisible(true);
					lonIn2.setVisible(true);
					altIn1.setVisible(true);
					altIn2.setVisible(true);
					String lat1 = (String)latIn1.getText();
					String lat2 = (String)latIn2.getText();
					String lon1 = (String)lonIn1.getText();
					String lon2 = (String)lonIn2.getText();
					String alt1 = (String)altIn1.getText();
					String alt2 = (String)altIn2.getText();


					Lat1.setVisible(true);
					Lon1.setVisible(true);
					Alt1.setVisible(true);

					ok1.setVisible(true);

				}else if(selectedfilter1 == "Time"){
					btnCancelFilter.setVisible(true);
					input1.setText("");
					input2.setText("");
					latIn1.setText("");
					latIn2.setText("");
					lonIn1.setText("");
					lonIn2.setText("");
					altIn1.setText("");
					altIn2.setText("");
					latIn1.setVisible(false);
					latIn2.setVisible(false);
					lonIn1.setVisible(false);
					lonIn2.setVisible(false);
					altIn1.setVisible(false);
					altIn2.setVisible(false);
					Lat1.setVisible(false);
					Lon1.setVisible(false);
					Alt1.setVisible(false);

					input1.setVisible(true);
					input2.setVisible(true);

					String value1 = (String)input1.getText();
					String value2 = (String)input2.getText();
					ok1.setVisible(true);

				}
			}
		});
		contentPane.add(filterBox1);
		filterBox1.addItem("Choose filter..");
		filterBox1.addItem("Location");
		filterBox1.addItem("Device");
		filterBox1.addItem("Time");

		operatorBox1 = new JComboBox();
		operatorBox1.setBounds(88, 112, 77, 20);
		contentPane.add(operatorBox1);
		operatorBox1.addItem("Operator..");
		operatorBox1.addItem("NOT");




		latIn1 = new JTextField();
		latIn1.setBounds(165, 151, 86, 20);
		latIn1.setVisible(false);
		contentPane.add(latIn1);
		latIn1.setColumns(10);

		latIn2 = new JTextField();
		latIn2.setBounds(275, 151, 86, 20);
		latIn2.setVisible(false);
		contentPane.add(latIn2);
		latIn2.setColumns(10);

		lonIn1 = new JTextField();
		lonIn1.setBounds(165, 182, 86, 20);
		lonIn1.setVisible(false);
		contentPane.add(lonIn1);
		lonIn1.setColumns(10);

		lonIn2 = new JTextField();
		lonIn2.setBounds(275, 182, 86, 20);
		lonIn2.setVisible(false);
		contentPane.add(lonIn2);
		lonIn2.setColumns(10);

		altIn1 = new JTextField();
		altIn1.setBounds(165, 213, 86, 20);
		altIn1.setVisible(false);
		contentPane.add(altIn1);
		altIn1.setColumns(10);

		altIn2 = new JTextField();
		altIn2.setBounds(275, 213, 86, 20);
		altIn2.setVisible(false);
		contentPane.add(altIn2);
		altIn2.setColumns(10);

		btnCancelFilter = new JButton("Cancel filters");
		btnCancelFilter.setVisible(false);
		btnCancelFilter.setBounds(213, 292, 106, 39);
		btnCancelFilter.setVisible(false);
		btnCancelFilter.setFont(new Font("Tahoma", Font.BOLD, 9));
		contentPane.add(btnCancelFilter);

		latIn3 = new JTextField();
		latIn3.setVisible(false);
		latIn3.setBounds(482, 151, 86, 20);
		contentPane.add(latIn3);
		latIn3.setColumns(10);

		latIn4 = new JTextField();
		latIn4.setVisible(false);
		latIn4.setColumns(10);
		latIn4.setBounds(588, 151, 86, 20);
		contentPane.add(latIn4);

		lonIn3 = new JTextField();
		lonIn3.setVisible(false);
		lonIn3.setColumns(10);
		lonIn3.setBounds(482, 182, 86, 20);
		contentPane.add(lonIn3);

		lonIn4 = new JTextField();
		lonIn4.setVisible(false);
		lonIn4.setColumns(10);
		lonIn4.setBounds(588, 182, 86, 20);
		contentPane.add(lonIn4);

		altIn3 = new JTextField();
		altIn3.setVisible(false);
		altIn3.setColumns(10);
		altIn3.setBounds(482, 214, 86, 20);
		contentPane.add(altIn3);

		altIn4 = new JTextField();
		altIn4.setVisible(false);
		altIn4.setColumns(10);
		altIn4.setBounds(588, 213, 86, 20);
		contentPane.add(altIn4);

		JLabel Lat2 = new JLabel("Choose Lat:");
		Lat2.setVisible(false);
		Lat2.setForeground(Color.WHITE);
		Lat2.setFont(new Font("Tahoma", Font.BOLD, 12));
		Lat2.setBounds(399, 153, 73, 15);
		contentPane.add(Lat2);

		JLabel Lon2 = new JLabel("Choose Lon:");
		Lon2.setVisible(false);
		Lon2.setForeground(Color.WHITE);
		Lon2.setFont(new Font("Tahoma", Font.BOLD, 12));
		Lon2.setBounds(399, 182, 76, 15);
		contentPane.add(Lon2);

		JLabel Alt2 = new JLabel("Choose Alt:");
		Alt2.setVisible(false);
		Alt2.setForeground(Color.WHITE);
		Alt2.setFont(new Font("Tahoma", Font.BOLD, 12));
		Alt2.setBounds(399, 216, 73, 15);
		contentPane.add(Alt2);

		JButton removeFilter = new JButton("REMOVE FILTER");
		removeFilter.setBounds(405, 293, 124, 39);
		removeFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operatorBox2.setVisible(false);
				filterBox2.setVisible(false);
				input3.setVisible(false);
				input4.setVisible(false);
				ok2.setVisible(false);
				Lat2.setVisible(false);
				Lon2.setVisible(false);
				Alt2.setVisible(false);
				latIn3.setVisible(false);
				latIn4.setVisible(false);
				lonIn3.setVisible(false);
				lonIn4.setVisible(false);
				altIn3.setVisible(false);
				altIn4.setVisible(false);
				removeFilter.setVisible(false);
				ok1.setBounds(254, 249, 50, 32);
				flag = false;
				input1.setText("");
				input2.setText("");
				latIn1.setText("");
				latIn2.setText("");
				lonIn1.setText("");
				lonIn2.setText("");
				altIn1.setText("");
				altIn2.setText("");

				input3.setText("");
				input4.setText("");
				latIn3.setText("");
				latIn4.setText("");
				lonIn3.setText("");
				lonIn4.setText("");
				altIn3.setText("");
				altIn4.setText("");
				operatorBox2.setSelectedItem("Operator..");

			}
		});
		removeFilter.setVisible(false);

		dbSize = new JLabel("");
		dbSize.setForeground(Color.WHITE);
		dbSize.setBounds(242, 10, 50, 20);
		contentPane.add(dbSize);

		dbSizeBtn = new JButton("Database size:");
		dbSizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String size = String.valueOf(database.get_size());
				System.out.println(size);
				dbSize.setText(size);
			}
		});
		dbSizeBtn.setBounds(89, 10, 143, 23);
		contentPane.add(dbSizeBtn);



		removeFilter.setFont(new Font("Tahoma", Font.BOLD, 9));
		contentPane.add(removeFilter);


		filterBox2 = new JComboBox();
		filterBox2.setBounds(503, 112, 119, 20);
		filterBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedfilter2 = (String) filterBox2.getSelectedItem();
				System.out.println("You seleted : " + selectedfilter2);

				if(selectedfilter2 == "Device"){
					latIn3.setVisible(false);
					latIn4.setVisible(false);
					lonIn3.setVisible(false);
					lonIn4.setVisible(false);
					altIn3.setVisible(false);
					altIn4.setVisible(false);
					Lat2.setVisible(false);
					Lon2.setVisible(false);
					Alt2.setVisible(false);

					btnCancelFilter.setVisible(true);
					input4.setVisible(false);

					input3.setVisible(true);
					String value1 = (String)input3.getText();
					ok2.setVisible(true);

				}
				else if(selectedfilter2 == "Location"){
					input3.setVisible(false);
					input4.setVisible(false);

					latIn3.setVisible(true);
					latIn4.setVisible(true);
					lonIn3.setVisible(true);
					lonIn4.setVisible(true);
					altIn3.setVisible(true);
					altIn4.setVisible(true);

					String lat3 = (String)latIn3.getText();
					String lat4 = (String)latIn4.getText();
					String lon3 = (String)lonIn3.getText();
					String lon4 = (String)lonIn4.getText();
					String alt3 = (String)altIn3.getText();
					String alt4 = (String)altIn4.getText();


					Lat2.setVisible(true);
					Lon2.setVisible(true);
					Alt2.setVisible(true);

					ok2.setVisible(true);

				}else if(selectedfilter2 == "Time"){
					latIn3.setVisible(false);
					latIn4.setVisible(false);
					lonIn3.setVisible(false);
					lonIn4.setVisible(false);
					altIn3.setVisible(false);
					altIn4.setVisible(false);
					Lat2.setVisible(false);
					Lon2.setVisible(false);
					Alt2.setVisible(false);

					input3.setVisible(true);
					input4.setVisible(true);
					String value1 = (String)input3.getText();
					String value2 = (String)input4.getText();
					ok2.setVisible(true);

				}


				/*

				if(selectedfilter2 == "Device"){
					input3.setVisible(true);
					input4.setVisible(false);
					String value3 = (String)input3.getText();
					ok2.setVisible(true);
				}
				else if(selectedfilter2 != "Choose filter.." && selectedfilter2 != "Device"){
					input3.setVisible(true);
					String value3 = (String)input3.getText();
					input4.setVisible(true);
					String value4 = (String)input4.getText();
					ok2.setVisible(true);
				}
				 */
			}
		});
		filterBox2.setVisible(false);
		contentPane.add(filterBox2);
		filterBox2.addItem("Choose filter..");
		filterBox2.addItem("Location");
		filterBox2.addItem("Device");
		filterBox2.addItem("Time");


		JButton addFilter = new JButton("ADD FILTER");
		addFilter.setBounds(88, 293, 106, 39);
		addFilter.setFont(new Font("Tahoma", Font.BOLD, 9));
		addFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ok1.setVisible(false);
				ok1.setBounds(572, 249, 50, 32);
				//ok2.setVisible(true);
				operatorBox2.setVisible(true);
				filterBox2.setVisible(true);
				removeFilter.setVisible(true);
				flag = true;
			}
		});
		contentPane.add(addFilter);

		dbFilterSizeBtn = new JButton("DB filter size:");
		dbFilterSizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String size = String.valueOf(filterDB.get_size());
				System.out.println(size);
				dbFilterSize.setText(size);
			}
		});
		dbFilterSizeBtn.setBounds(339, 10, 143, 23);
		contentPane.add(dbFilterSizeBtn);

		dbFilterSize = new JLabel("");
		dbFilterSize.setForeground(Color.WHITE);
		dbFilterSize.setBounds(492, 10, 50, 20);
		contentPane.add(dbFilterSize);

		JButton refreshBttn = new JButton("Refresh");
		refreshBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!flag){

					if(operatorBox1.getSelectedItem() != "NOT"){
						if(filterBox1.getSelectedItem().equals("Location")){

						}
						else if(filterBox1.getSelectedItem().equals("Time")){
							txtfilter.setText("filter(" +filterBox1.getSelectedItem()+"("+ input1.getText()+ "< data <"+input2.getText()+ ") )");
						}
						else if(filterBox1.getSelectedItem().equals("Device")){
							txtfilter.setText("filter(" +filterBox1.getSelectedItem()+"("+ "Device: "+ input1.getText()+") )");
						}

					}
					else{

					}
				}
				else{ //flag == true
					if(operatorBox2.getSelectedItem().toString().contains("AND")){

						if(operatorBox2.getSelectedItem().toString().equals("AND")){


						}
						else{//AND NOT

						}
					}else if(operatorBox2.getSelectedItem().toString().contains("OR")){

						if(operatorBox2.getSelectedItem().toString().equals("OR")){
							if(filterBox2.getSelectedItem().equals("Location")){

							}
							else if(filterBox2.getSelectedItem().equals("Time")){
								txtfilter.setText("filter(" +filterBox2.getSelectedItem()+"("+ input3.getText()+ "< data <"+input4.getText()+ ") )");
							}
							else if(filterBox2.getSelectedItem().equals("Device")){

								if(filterBox1.getSelectedItem().equals("Device")){
									txtfilter.setText("filter(" +filterBox1.getSelectedItem()+"(Device: "+ input1.getText()+") || "
											+"("+filterBox2.getSelectedItem()+"(Device: "+ input3.getText()+")))");
								}
								if(filterBox1.getSelectedItem().equals("Location")){

								}
								if(filterBox1.getSelectedItem().equals("Time")){
									txtfilter.setText("filter(" +filterBox1.getSelectedItem()+"("+ input1.getText()+ "< data <"+input2.getText()+ ") || "
											+"("+filterBox2.getSelectedItem()+"(Device: "+ input3.getText()+")))");
								}
							}

						}
						else{//OR NOT

						}
					}

				}
			}
		});
		refreshBttn.setBounds(684, 386, 89, 32);
		contentPane.add(refreshBttn);

		Background = new JLabel("");
		Background.setBounds(-139, -38, 943, 523);
		contentPane.add(Background);
		Background.setIcon(new ImageIcon(Home.class.getResource("/main/java/GUIPack/images/filter_Background.jpg")));


	}

	private void checkModifiedTime(final File[] selectedFile) {
		TimerTask timer = new TimerTask() {

			@Override
			public void run() {
				for (File file : selectedFile) {
					try {
						if(file.exists()){
							FileTime filetime = Files.getLastModifiedTime(file.toPath(),LinkOption.NOFOLLOW_LINKS);
							if(files.isEmpty()||!files.containsKey(file) || (files.containsKey(file) && !files.get(file).equals(filetime))) {
								files.put(file, filetime);
								System.out.println("Selected file: " + file.getAbsolutePath());
								ReadCsv readFile= new ReadCsv(file.getAbsolutePath());
								readFile.read();
								database.add(readFile.getDatabase());
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		};
		Timer task = new Timer();
		task.schedule(timer, 1000,60*1000);
	}
	private void uploadfile() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("csv file only (.csv)","csv"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		getContentPane().add(fileChooser, BorderLayout.CENTER);
		int result = fileChooser.showOpenDialog(upload);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFiles();
			for (File file : selectedFile) {
				try {
					if(file.exists()){
						FileTime filetime = Files.getLastModifiedTime(file.toPath(),LinkOption.NOFOLLOW_LINKS);
						if(files.isEmpty()||!files.containsKey(file) || (files.containsKey(file) && !files.get(file).equals(filetime))) {
							files.put(file, filetime);
							System.out.println("Selected file: " + file.getAbsolutePath());
							ReadCsv readFile= new ReadCsv(file.getAbsolutePath());
							readFile.read();
							database.add(readFile.getDatabase());
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void undo() throws IOException{
		this.setVisible(false);
		filterDB = new MainDB();
		filterPage fp = new filterPage(selectedFile);
		fp.setVisible(true);
	}


	//https://stackoverflow.com/questions/15372705/calculating-a-radius-with-longitude-and-latitude
	double CoordDistance(double latitude1, double longitude1, double latitude2, double longitude2){
		return 6371 * Math.acos(
				Math.sin(latitude1) * Math.sin(latitude2)
				+ Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude2 - longitude1));
	}



}
