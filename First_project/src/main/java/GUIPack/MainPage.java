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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import main.java.readPack.ReadCsv;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import main.java.databasePack.MainDB;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
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
	private MainDB database = new MainDB();
	
	private JTextField txtDatabase;
	private JTextField textFilter;
	private JLabel logoff;
	private JLayeredPane layeredPane;
	private JLayeredPane tableDisplay;
	private JLayeredPane mapDisplay;
	private JLayeredPane filterDisplay;
	private JLabel userIcon;
	private JLabel modemIcon;
	private JLabel label_1;
	private JLabel infoIcon;
	private static File[] selectedFile;
	private JPanel upload;
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
		
		JPanel upperbar = new JPanel();
		upperbar.setBorder(new LineBorder(new Color(0, 0, 0)));
		upperbar.setBackground(UIManager.getColor("activeCaption"));
		upperbar.setBounds(52, 0, 754, 71);
		contentPane.add(upperbar);
		upperbar.setLayout(null);
		
		upload = new JPanel();
		upload.setBounds(100, 100, 630, 421);
		upload.setVisible(false);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(SystemColor.activeCaption);
		layeredPane.setBounds(2, 1, 773, 70);
		upperbar.add(layeredPane);
		
		textFilter = new JTextField();
		textFilter.setBackground(SystemColor.activeCaption);
		textFilter.setText("filter: 0");
		textFilter.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		textFilter.setEditable(false);
		textFilter.setDropMode(DropMode.INSERT);
		textFilter.setColumns(1);
		
		logoff = new JLabel("");
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
		
		txtDatabase = new JTextField();
		txtDatabase.setBackground(SystemColor.activeCaption);
		txtDatabase.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		txtDatabase.setDropMode(DropMode.INSERT);
		txtDatabase.setEditable(false);
		txtDatabase.setText("Database: " + this.database.get_size());
		
		label_1 = new JLabel("");
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtDatabase, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(86)
							.addComponent(textFilter, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 511, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(logoff, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(1)
							.addComponent(txtDatabase, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFilter, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(logoff, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layeredPane.setLayout(gl_layeredPane);
		
		display = new JPanel();
		display.setBorder(new LineBorder(new Color(0, 0, 0)));
		display.setBackground(Color.WHITE);
		display.setBounds(52, 67, 754, 433);
		contentPane.add(display);
		display.setLayout(new CardLayout(0, 0));
		display.setVisible(false);
		
		JLayeredPane modemLocationDisplay = new JLayeredPane();
		display.add(modemLocationDisplay, "name_353906401068356");
		
		JLayeredPane userLocationDisplay = new JLayeredPane();
		display.add(userLocationDisplay, "name_353928723796349");
		
		tableDisplay = new JLayeredPane();
		display.add(tableDisplay, "name_353803376029634");
		
		mapDisplay = new JLayeredPane();
		display.add(mapDisplay, "name_353839197399555");
		
		filterDisplay = new JLayeredPane();
		display.add(filterDisplay, "name_353859816218952");
		
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
				display.setVisible(false);
				mapDisplay.setVisible(true);
			}
		});
		filterIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/filter_32px.png")));
		filterIcon.setForeground(new Color(248, 248, 255));
		
		mapIcon = DefaultComponentFactory.getInstance().createLabel("");
		mapIcon.setLabelFor(controlPanel);
		mapIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mapDisplay.setVisible(false);
				display.setVisible(true);
				googleMaps();
				
			}
		});
		mapIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Globe_32px.png")));
		
		tableIcon = DefaultComponentFactory.getInstance().createLabel("");
		tableIcon.setLabelFor(controlPanel);
		tableIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				display.setVisible(false);
				mapDisplay.setVisible(true);
			}
		});
		tableIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Bulleted List_32px.png")));
		tableIcon.setForeground(new Color(248, 248, 255));
		
		JLabel csvButton = new JLabel("");
		csvButton.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/CSV_32px.png")));
		
		JLabel kmlIcon = new JLabel("");
		kmlIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/KML_32px.png")));
		
		userIcon = new JLabel("");
		userIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Location_50px.png")));
		
		modemIcon = new JLabel("");
		modemIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Wi-Fi Router_50px.png")));
		
		infoIcon = new JLabel("");
		infoIcon.setIcon(new ImageIcon(MainPage.class.getResource("/main/java/GUIPack/images/Info_32px.png")));
		GroupLayout gl_controlPanel = new GroupLayout(controlPanel);
		gl_controlPanel.setHorizontalGroup(
			gl_controlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addGroup(gl_controlPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_controlPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(kmlIcon))
						.addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_controlPanel.createSequentialGroup()
								.addGap(17)
								.addComponent(csvButton, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
							.addGroup(gl_controlPanel.createSequentialGroup()
								.addGap(20)
								.addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(filterIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(mapIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tableIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(addIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addGap(18))
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(userIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_controlPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(infoIcon))
						.addComponent(modemIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		controlPanel.setLayout(gl_controlPanel);
		
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
}
