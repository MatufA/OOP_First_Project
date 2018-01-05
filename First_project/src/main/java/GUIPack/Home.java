package main.java.GUIPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel upload;
	private static File[] selectedFile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setTitle("KML for CSV");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/main/java/GUIPack/images/app-earth-icon.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 440);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		upload = new JPanel();
		upload.setBounds(100, 100, 630, 421);
		upload.setVisible(false);
		
		JLabel lblKmlToCsv = new JLabel("KML to CSV");
		lblKmlToCsv.setBounds(42, 40, 170, 43);
		contentPane.add(lblKmlToCsv);
		lblKmlToCsv.setFont(new Font("Levenim MT", Font.PLAIN, 30));
		lblKmlToCsv.setForeground(UIManager.getColor("Button.background"));
		lblKmlToCsv.setBackground(UIManager.getColor("Button.background"));
		
		JLabel lblDevelopedByAdiel = new JLabel("Developed By: Adiel Matuf");
		lblDevelopedByAdiel.setFont(new Font("Levenim MT", Font.PLAIN, 18));
		lblDevelopedByAdiel.setForeground(UIManager.getColor("Button.background"));
		lblDevelopedByAdiel.setBounds(52, 80, 238, 26);
		contentPane.add(lblDevelopedByAdiel);
		
		JLabel lblAdiAchwal = new JLabel("Adi Achwal");
		lblAdiAchwal.setBounds(187, 100, 102, 26);
		contentPane.add(lblAdiAchwal);
		lblAdiAchwal.setForeground(SystemColor.menu);
		lblAdiAchwal.setFont(new Font("Levenim MT", Font.PLAIN, 18));
		
		JLabel lblYehudaNeumann = new JLabel("Yehuda Newmann");
		lblYehudaNeumann.setBounds(187, 120, 166, 26);
		contentPane.add(lblYehudaNeumann);
		lblYehudaNeumann.setForeground(SystemColor.menu);
		lblYehudaNeumann.setFont(new Font("Levenim MT", Font.PLAIN, 18));
		
		Button upload_button = new Button("Upload Wiggle Wifi CSV");
		upload_button.setForeground(new Color(255, 255, 255));
		upload_button.setBackground(new Color(0, 0, 51));
		upload_button.setFont(new Font("Calibri", Font.PLAIN, 12));
		upload_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					uploadfile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 255, 224), new Color(255, 255, 224), null, null));
		panel.setBackground(new Color(51, 51, 51));
		panel.setBounds(0, 0, 365, 443);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Earth_logo = new JLabel("");
		Earth_logo.setBounds(0, 0, 3000, 3075);
		panel.add(Earth_logo);
		Earth_logo.setIcon(new ImageIcon(Home.class.getResource("/main/java/GUIPack/images/Earth_banner_1600x600_v4.jpg")));
		upload_button.setBounds(444, 179, 219, 44);
		contentPane.add(upload_button);
		
		Button Instraction_Button = new Button("Instraction");
		Instraction_Button.setForeground(Color.WHITE);
		Instraction_Button.setFont(new Font("Calibri", Font.PLAIN, 12));
		Instraction_Button.setBackground(new Color(0, 0, 51));
		Instraction_Button.setBounds(444, 229, 219, 44);
		contentPane.add(Instraction_Button);
		
		Button exit_button = new Button("Exit");
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		exit_button.setForeground(Color.WHITE);
		exit_button.setFont(new Font("Calibri", Font.PLAIN, 12));
		exit_button.setBackground(new Color(0, 0, 51));
		exit_button.setBounds(444, 279, 219, 44);
		contentPane.add(exit_button);
	}
	private void uploadfile() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("csv file only (.csv)","csv"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		getContentPane().add(fileChooser, BorderLayout.CENTER);
		int result = fileChooser.showOpenDialog(upload);
		//File file;
		if (result == JFileChooser.APPROVE_OPTION) {
		   selectedFile = fileChooser.getSelectedFiles();
		    /*for (int i = 0; i < selectedFile.length; i++) {
				 file = selectedFile[i];
				 System.out.println("Selected file: " + file.getAbsolutePath());
			}*/
		   this.setVisible(false);
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
	}
}
