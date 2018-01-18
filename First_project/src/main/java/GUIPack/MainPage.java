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
import main.java.filtersPack.FilterByMAC;
import javax.swing.JButton;


public class MainPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JPanel contentPane, display;

	private JLabel addIcon, filterIcon;
	private JLabel mapIcon, tableIcon;
	private JTable table;
	private final JFXPanel jfxPanel = new JFXPanel();
	private static HashMap<File, FileTime> files = new HashMap<>();
	
	
	private MainDB database = new MainDB(), filterDB = new MainDB();
	private JScrollPane scrollmap;
	private JScrollPane scrolltable;
	private JTextField txtDatabase;
	private JTextField textFilter;
	private JLabel logoff;
	private JLayeredPane layeredPane;
	private JLayeredPane tableDisplay;
	private JLayeredPane mapDisplay;
	private JLayeredPane filterDisplay, userLocationDisplay, modemLocationDisplay;
	private JLabel userIcon;
	private JLabel modemIcon;
	private JLabel infoIcon;
	private static File[] selectedFile;
	private JPanel upload;
	private JLayeredPane infoDisplay;
	private JTextField txtfilter;
	private JToggleButton tglbtnFilterByMac;
	private JToggleButton tglbtnFilterByTime;
	private JToggleButton tglbtnFilterByLocation;
	private JToggleButton tglbtnFilterById;
	private JTextField choosemac;
	private JEditorPane view;
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
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainPage(File[] selectedFile) throws IOException {
		
		setResizable(false);
		MainPage.selectedFile = selectedFile;
		checkModifiedTime(MainPage.selectedFile);
		setTitle("GEO-App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/main/java/GUIPack/images/app-earth-icon.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		controlPanel.setBackground(new Color(47, 79, 79));
		controlPanel.setForeground(new Color(248, 248, 255));
		controlPanel.setBounds(-12, 0, 70, 500);
		contentPane.add(controlPanel);

		addIcon = DefaultComponentFactory.getInstance().createLabel("");
		addIcon.setBounds(19, 6, 32, 32);
		addIcon.setLabelFor(controlPanel);
		addIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					uploadfile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		addIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Plus Math_32px.png")));
		addIcon.setForeground(new Color(248, 248, 255));

		filterIcon = DefaultComponentFactory.getInstance().createLabel("");
		filterIcon.setBounds(19, 49, 32, 32);
		filterIcon.setLabelFor(controlPanel);
		filterIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();
				
				display.add(filterDisplay,BorderLayout.SOUTH);
				display.repaint();
				display.add(filterDisplay, BorderLayout.CENTER);
				
				contentPane.remove(display);
				contentPane.repaint();
				contentPane.revalidate();
				contentPane.add(filterDisplay,BorderLayout.SOUTH);
				filterDisplay.setVisible(true);
			}
		});
		filterIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/filter_32px.png")));
		filterIcon.setForeground(new Color(248, 248, 255));

		mapIcon = DefaultComponentFactory.getInstance().createLabel("");
		mapIcon.setBounds(20, 166, 32, 32);
		mapIcon.setLabelFor(controlPanel);
		mapIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				filterDisplay.setVisible(false);
				googleMaps();
				display.removeAll();
				display.repaint();
				display.revalidate();
				
				display.add(mapDisplay);
				display.repaint();
				display.add(mapDisplay);
				
				contentPane.remove(display);
				contentPane.repaint();
				contentPane.revalidate();
				
				contentPane.add(mapDisplay);
				mapDisplay.setVisible(true);

			}
		});
		mapIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Globe_32px.png")));

		tableIcon = DefaultComponentFactory.getInstance().createLabel("");
		tableIcon.setBounds(20, 209, 32, 32);
		tableIcon.setLabelFor(controlPanel);
		tableDisplay = new JLayeredPane();
		String[] columnNames = {"Time","ID","MAC","SSID","Latitude",
				"Longitude","Altitude","Frequncy","Signal"};		
		Object [][] data = new Object[database.get_size()][9];
		
		tableIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();
				
				display.add(scrollmap);
				display.repaint();
				display.add(scrollmap);
				
				contentPane.remove(display);
				contentPane.repaint();
				contentPane.revalidate();
				contentPane.add(scrollmap);
				filterDisplay.setVisible(true);
				
				if(!database.isEmpty()) {
					Object [][] data = new Object[database.get_size()][9];
					int count = 0;
					List<List<Network>> temp = new ArrayList<>(database.getdatabase()); 
					for (List<Network> list : temp) {
						for (Network net : list ) {
							data[count][0] = net.getTime();
							data[count][1] = net.getId();
							data[count][2] = net.getMac();
							data[count][3] = net.getSsid();
							data[count][4] = net.getLat();
							data[count][5] = net.getLon();
							data[count][6] = net.getAlt();
							data[count][7] = net.getFrequncy();
							data[count][8] = net.getSignal();
							count++;
						}
						
					}
					tableDisplay.setBounds(0, 0, 752, 431);
					table = new JTable(null, columnNames);
					table.setPreferredScrollableViewportSize(new Dimension(736, 372));
					table.setFillsViewportHeight(true);

					scrolltable = new JScrollPane(table);
					getContentPane().add(scrolltable);
					tableDisplay.add(table);
					tableDisplay.setBounds(0, 0, 752, 431);
					
					display.add(tableDisplay);
					
					display.setVisible(true);
				}

				/*display.add(tableDisplay);
				display.repaint();
				display.revalidate();

				contentPane.add(tableDisplay);
				tableDisplay.setVisible(true);*/
			}
		});
		tableIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Bulleted List_32px.png")));
		tableIcon.setForeground(new Color(248, 248, 255));

		JLabel csvButton = new JLabel("");
		csvButton.setBounds(19, 84, 32, 32);
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
						// parent component of the dialog
						parentFrame = new JFrame();
						JFileChooser fc = new JFileChooser();
						fc.setDialogTitle("Specify a file to save");   
						userSelection = fc.showSaveDialog(parentFrame);
						if (userSelection == JFileChooser.APPROVE_OPTION) {
							File fileToSave = fc.getSelectedFile();
							String path = fileToSave.getAbsolutePath();
							System.out.println("Save as file: " + path);
							if(path.substring(path.length()-4).equalsIgnoreCase(".csv")) path += ".csv";
							WriteCsv write = new WriteCsv(filterDB.getdatabase() ,path);
							if(!filterDB.isEmpty()) write.write();
						}
						break;
					default:
						break;
					}
				}
		});
		csvButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/CSV_32px.png")));

		JLabel kmlIcon = new JLabel("");
		kmlIcon.setBounds(19, 123, 32, 32);
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
							WriteKml write = new WriteKml(filterDB.getdatabase() ,path);
							if(!filterDB.isEmpty()) write.write();
						}
						break;
					default:
						break;
					}
				}
		});
		kmlIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/KML_32px.png")));

		userIcon = new JLabel("");
		userIcon.setBounds(10, 307, 50, 50);
		userIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();

				display.add(userLocationDisplay);
				display.repaint();
				display.revalidate();

				contentPane.add(userLocationDisplay);
				userLocationDisplay.setVisible(true);
			}
		});
		userIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Location_50px.png")));

		modemIcon = new JLabel("");
		modemIcon.setBounds(10, 246, 50, 50);
		modemIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();

				display.add(modemLocationDisplay);
				display.repaint();
				display.revalidate();

				contentPane.add(modemLocationDisplay);
				modemLocationDisplay.setVisible(true);
			}
		});
		modemIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Wi-Fi Router_50px.png")));

		infoIcon = new JLabel("");
		infoIcon.setBounds(19, 368, 32, 32);
		infoIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Info_32px.png")));
		controlPanel.setLayout(null);
		controlPanel.add(userIcon);
		controlPanel.add(modemIcon);
		controlPanel.add(infoIcon);
		controlPanel.add(filterIcon);
		controlPanel.add(mapIcon);
		controlPanel.add(tableIcon);
		controlPanel.add(addIcon);
		controlPanel.add(kmlIcon);
		controlPanel.add(csvButton);

		JPanel upperbar = new JPanel();
		upperbar.setBorder(new LineBorder(new Color(0, 0, 0)));
		upperbar.setBackground(UIManager.getColor("activeCaption"));
		upperbar.setBounds(52, 0, 754, 71);
		contentPane.add(upperbar);

		upload = new JPanel();
		upload.setBounds(100, 100, 630, 421);
		upload.setVisible(false);

		layeredPane = new JLayeredPane();
		layeredPane.setBackground(SystemColor.activeCaption);

		txtDatabase = new JTextField();
		txtDatabase.setBounds(0, 0, 92, 65);
		txtDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatabase.setBackground(SystemColor.activeCaption);
		txtDatabase.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtDatabase.setDropMode(DropMode.INSERT);
		txtDatabase.setEditable(false);
		txtDatabase.setText("Database: " + this.database.get_size());

		textFilter = new JTextField();
		textFilter.setBounds(98, 0, 103, 65);
		textFilter.setHorizontalAlignment(SwingConstants.CENTER);
		textFilter.setBackground(SystemColor.activeCaption);
		textFilter.setText("filter: 0");
		textFilter.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textFilter.setEditable(false);
		textFilter.setDropMode(DropMode.INSERT);

		logoff = new JLabel("");
		logoff.setBounds(691, 19, 32, 32);
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
		logoff.setLabelFor(upperbar);
		logoff.setHorizontalAlignment(SwingConstants.LEFT);
		logoff.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Logout Rounded Up_32px.png")));
		layeredPane.setLayout(null);
		layeredPane.add(txtDatabase);
		layeredPane.add(textFilter);
		layeredPane.add(logoff);
		GroupLayout gl_upperbar = new GroupLayout(upperbar);
		gl_upperbar.setHorizontalGroup(
				gl_upperbar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upperbar.createSequentialGroup()
						.addGap(1)
						.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 742, GroupLayout.PREFERRED_SIZE))
				);
		gl_upperbar.setVerticalGroup(
				gl_upperbar.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
				);
		upperbar.setLayout(gl_upperbar);

		display = new JPanel();
		display.setBorder(new LineBorder(new Color(0, 0, 0)));
		display.setBackground(Color.WHITE);
		display.setBounds(50, 70, 754, 401);
		contentPane.add(display);
		display.setVisible(false);
		display.setLayout(null);

		JLayeredPane modemLocationDisplay = new JLayeredPane();
		modemLocationDisplay.setBounds(0, 0, 752, 431);
		display.add(modemLocationDisplay);
		modemLocationDisplay.setLayout(null);
		
		
		JLayeredPane userLocationDisplay = new JLayeredPane();
		userLocationDisplay.setBounds(0, 0, 752, 431);
		display.add(userLocationDisplay);

		mapDisplay = new JLayeredPane();
		mapDisplay.setBounds(0, 0, 752, 431);
		display.add(mapDisplay);


		filterDisplay = new JLayeredPane();
		filterDisplay.setBounds(0, 0, 744, 401);
		display.add(filterDisplay);

		Vector<String> filternames = new Vector<>();
		txtfilter = new JTextField();
		txtfilter.setFont(new Font("Calibri Light", Font.BOLD, 16));
		txtfilter.setBounds(66, 29, 575, 32);
		filterDisplay.add(txtfilter);
		txtfilter.setColumns(10);
		txtfilter.setText("filter( )");
		
		choosemac = new JTextField();
		choosemac.setVisible(false);
		choosemac.setBounds(93, 100, 121, 23);
		
		JButton MacOk = new JButton("OK");
		MacOk.setBounds(167, 142, 62, 37);
		filterDisplay.add(MacOk);
		MacOk.setVisible(false);
		MacOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = choosemac.getText();
				FilterByMAC fMac = new FilterByMAC(database.getdatabase(), str);
				System.out.println(fMac.filter());
				System.out.println(str);
			}
		});
		
		String newstr = "";
		tglbtnFilterByMac = new JToggleButton("Filter By mac");
		tglbtnFilterByMac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			/*	String filter = (String) chooseFilter.getSelectedItem();
				System.out.println(filter + "    filter");
				if(filter == "Device"){
					MySQLFilters gf = new MySQLFilters();
					String dev= (String)MinInput.getText();
					gf.DevFilter(dev);
					clicked = true;
					
				}*/
				
				
				if(tglbtnFilterByMac.isSelected()) {
					//MacOk.setVisible(true);
					String newstr = txtfilter.getText().substring(0, txtfilter.getText().length()) + "(MAC)";
					txtfilter.setText(newstr);

					
					filterDisplay.add(choosemac);
					choosemac.setColumns(10);
					choosemac.setVisible(true);
					MacOk.setVisible(true);
					
					filterDisplay.remove(txtfilter);
					filterDisplay.add(txtfilter);
					contentPane.add(filterDisplay);
					if(tglbtnFilterByMac.isSelected()) {
						String str = "(" + choosemac.getText() + ")";
						newstr ="";
						if(choosemac.getText().split(":").length==6) {
							newstr = txtfilter.getText().substring(0, str.length()) + str;
							
						}
						
						txtfilter.setText(""); 
						choosemac = new JTextField();
						choosemac.setBounds(93, 100, 121, 23);

						str = "";
						filterDisplay.remove(txtfilter);
						contentPane.add(filterDisplay);
					}
				}
			}
		});
		

				

		tglbtnFilterByMac.setBounds(93, 72, 121, 23);
		filterDisplay.add(tglbtnFilterByMac);


		tglbtnFilterByTime = new JToggleButton("Filter By Time");
		tglbtnFilterByTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filternames.add("Time");
			}
		});

		tglbtnFilterByTime.setBounds(236, 72, 121, 23);
		filterDisplay.add(tglbtnFilterByTime);

		tglbtnFilterByLocation = new JToggleButton("Filter By Location");
		tglbtnFilterByLocation.setBounds(379, 72, 121, 23);
		filterDisplay.add(tglbtnFilterByLocation);

		tglbtnFilterById = new JToggleButton("Filter By ID");
		tglbtnFilterById.setBounds(520, 72, 121, 23);
		filterDisplay.add(tglbtnFilterById);

		infoDisplay = new JLayeredPane();
		infoDisplay.setBounds(-10055, -10093, 752, 431);
		infoDisplay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();

				display.add(infoDisplay);
				display.repaint();
				display.revalidate();
			}
		});
		display.add(infoDisplay);

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(736, 372));
		table.setFillsViewportHeight(true);
		
		scrollmap = new JScrollPane(table);
		scrollmap.setBounds(0, 0, 0, 0);
		display.add(scrollmap);
	}
	
	private void googleMaps() {
		/*https://stackoverflow.com/questions/13487786/add-webview-control-on-swing-jframe*/

		Platform.runLater(() -> {
			WebView webView = new WebView();
			jfxPanel.setScene(new Scene(webView));
			webView.getEngine().load("https://www.google.co.il/maps/@31.9503288,34.768555,15z?hl=iw");
			display.add(jfxPanel, BorderLayout.CENTER);
		});
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
}
