package main.java.GUIPack;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
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

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import main.java.databasePack.MainDB;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class MainPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JPanel contentPane, display;
	
	private JLabel addIcon, filterIcon;
	private JLabel mapIcon, tableIcon;
	
	private final JFXPanel jfxPanel = new JFXPanel();
	private static HashMap<File, FileTime> files = new HashMap<>();
	private MainDB database = new MainDB(), filterDB = new MainDB();
	
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
	private JPopupMenu csvPick;
	private JTextField txtfilter;
	private JToggleButton tglbtnFilterByMac;
	private JToggleButton tglbtnFilterByTime;
	private JToggleButton tglbtnFilterByLocation;
	private JToggleButton tglbtnFilterById;
	private JTextField choosemac;
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
		checkModifiedTime(selectedFile);
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
		filterIcon.setLabelFor(controlPanel);
		filterIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.add(filterDisplay);
				filterDisplay.setVisible(true);
			}
		});
		filterIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/filter_32px.png")));
		filterIcon.setForeground(new Color(248, 248, 255));
		
		mapIcon = DefaultComponentFactory.getInstance().createLabel("");
		mapIcon.setLabelFor(controlPanel);
		mapIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				display.removeAll();
				display.repaint();
				display.revalidate();
				
				display.add(mapDisplay);
				display.repaint();
				display.revalidate();
				
			}
		});
		mapIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Globe_32px.png")));
		
		tableIcon = DefaultComponentFactory.getInstance().createLabel("");
		tableIcon.setLabelFor(controlPanel);
		tableIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();
				
				display.add(tableDisplay);
				display.repaint();
				display.revalidate();
	
				String table = "csv_table";
				Table window = new Table(table);
				window.setVisible(true);
				window.setSize(770,440);
			}
		});
		tableIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Bulleted List_32px.png")));
		tableIcon.setForeground(new Color(248, 248, 255));
		
		JLabel csvButton = new JLabel("");
		csvButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				csvPick.setVisible(true);
			}
		});
		csvButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/CSV_32px.png")));
		
		JLabel kmlIcon = new JLabel("");
		kmlIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		kmlIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/KML_32px.png")));
		
		userIcon = new JLabel("");
		userIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();
				
				display.add(userLocationDisplay);
				display.repaint();
				display.revalidate();
			}
		});
		userIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Location_50px.png")));
		
		modemIcon = new JLabel("");
		modemIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.removeAll();
				display.repaint();
				display.revalidate();
				
				display.add(modemLocationDisplay);
				display.repaint();
				display.revalidate();
			}
		});
		modemIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Wi-Fi Router_50px.png")));
		
		infoIcon = new JLabel("");
		infoIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Info_32px.png")));
		GroupLayout gl_controlPanel = new GroupLayout(controlPanel);
		gl_controlPanel.setHorizontalGroup(
			gl_controlPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(userIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(modemIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_controlPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(infoIcon)))
					.addContainerGap())
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(filterIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(mapIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(addIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18))
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(kmlIcon)
					.addContainerGap(20, Short.MAX_VALUE))
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(csvButton, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_controlPanel.setVerticalGroup(
			gl_controlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(filterIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mapIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(tableIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(csvButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(kmlIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(modemIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoIcon)
					.addGap(111))
		);
		
		csvPick = new JPopupMenu();
		controlPanel.setLayout(gl_controlPanel);
		
		JMenuItem mainDatabase = new JMenuItem("Main Database");
		mainDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		mainDatabase.setSelected(true);
		JMenuItem filterDatabase = new JMenuItem("Filtered Database");
		filterDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		filterDatabase.setSelected(true);

		class maindata implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// parent component of the dialog
				JFrame parentFrame = new JFrame();
				 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fileChooser.showSaveDialog(parentFrame);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				    WriteCsv write = new WriteCsv(database.getdatabase() ,fileToSave.getAbsolutePath());
				    if(!database.isEmpty()) write.write();
				}
			}
		}
		
		class filterdata implements ActionListener, PopupMenuListener{
			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				csvPick.setVisible(false);
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				// parent component of the dialog
				JFrame parentFrame = new JFrame();
				 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fileChooser.showSaveDialog(parentFrame);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				    WriteCsv write = new WriteCsv(filterDB.getdatabase() ,fileToSave.getAbsolutePath());
					if(!filterDB.isEmpty())write.write();
					csvPick.setVisible(false);
				}else if(userSelection == JFileChooser.CANCEL_OPTION) {
					csvPick.setVisible(false);
				}
				
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		
		mainDatabase.addActionListener(new maindata());
		filterDatabase.addActionListener( new filterdata());
		
		
		csvPick.add(mainDatabase);
		csvPick.add(filterDatabase);
		addPopup(csvButton, csvPick);
		
		
		
		
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
				System.exit(0);
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
		modemLocationDisplay.setBounds(-13, 0, 765, 431);
		display.add(modemLocationDisplay);
		
		JLayeredPane userLocationDisplay = new JLayeredPane();
		userLocationDisplay.setBounds(-13, 0, 765, 431);
		display.add(userLocationDisplay);
		
		tableDisplay = new JLayeredPane();
		tableDisplay.setBounds(-13, 0, 765, 431);
		display.add(tableDisplay);
		
		mapDisplay = new JLayeredPane();
		mapDisplay.setBounds(-13, 0, 765, 431);
		display.add(mapDisplay);
		
		
		filterDisplay = new JLayeredPane();
		filterDisplay.setBounds(-13, 0, 765, 431);
		display.add(filterDisplay);
		
		Vector<String> filternames = new Vector<>();
		txtfilter = new JTextField();
		txtfilter.setFont(new Font("Calibri Light", Font.BOLD, 16));
		txtfilter.setBounds(66, 29, 575, 32);
		filterDisplay.add(txtfilter);
		txtfilter.setColumns(10);
		txtfilter.setText("filter( )");
		
		
		tglbtnFilterByMac = new JToggleButton("Filter By mac");
	    tglbtnFilterByMac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglbtnFilterByMac.isSelected()) {
					String newstr = txtfilter.getText().substring(0, txtfilter.getText().length()) + "MAC)";
					txtfilter.setText(newstr);
					
					choosemac = new JTextField();
					choosemac.setBounds(93, 100, 121, 23);
					
					filterDisplay.add(choosemac);
					choosemac.setColumns(10);
					
					filterDisplay.remove(txtfilter);
					filterDisplay.add(txtfilter);
					contentPane.add(filterDisplay);
					if(tglbtnFilterByMac.isSelected()) {
						  String str = "(" + choosemac.getText() + ")";
				          newstr ="";
				          if(choosemac.getText().split(":").length==6) {
				        	  newstr = txtfilter.getText().substring(0, str.length()) + str;
				          }
						  txtfilter.setText(newstr); 
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
		  	}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
