package main.java.GUIPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginSQL extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textuser;
	private JPasswordField passwordField;
	private JTextField ipAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSQL frame = new LoginSQL();
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
	public LoginSQL(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Import SQL Database");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[434px]", "[261px]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		
		JLabel lblusername = new JLabel("User name:");
		lblusername.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblusername.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 16));
		
		JLabel lblIpAddressserver = new JLabel("IP address:");
		lblIpAddressserver.setHorizontalAlignment(SwingConstants.CENTER);
		lblIpAddressserver.setFont(new Font("Calibri", Font.PLAIN, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblusername, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(lblIpAddressserver, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(80)
					.addComponent(lblusername, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblIpAddressserver, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addGap(69))
		);
		panel.setLayout(gl_panel);
		
		JPanel mainFiled = new JPanel();
		contentPane.add(mainFiled, "cell 0 0,alignx left,aligny top");
		
		textuser = new JTextField();
		textuser.setHorizontalAlignment(SwingConstants.CENTER);
		textuser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		
		ipAddress = new JTextField();
		ipAddress.setHorizontalAlignment(SwingConstants.CENTER);
		ipAddress.setColumns(10);
		
		JButton btnCencel = new JButton("Cencel");
		btnCencel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		
		JButton btnOk = new JButton("Export");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(isEmpty()) {
					//Custom button text
					Object[] options = {"OK"};
					int choose = JOptionPane.showOptionDialog(contentPane,
					    "Error, One of the following field is empy please check again!",
					    "Error",
					    JOptionPane.OK_OPTION,
					    JOptionPane.ERROR_MESSAGE, null, options, options);
					
				}else setVisible(false);
			}
		});
		GroupLayout gl_mainFiled = new GroupLayout(mainFiled);
		gl_mainFiled.setHorizontalGroup(
			gl_mainFiled.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainFiled.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_mainFiled.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainFiled.createSequentialGroup()
							.addComponent(ipAddress, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_mainFiled.createSequentialGroup()
							.addGroup(gl_mainFiled.createParallelGroup(Alignment.TRAILING)
								.addComponent(textuser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
								.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
							.addGap(25))))
				.addGroup(Alignment.TRAILING, gl_mainFiled.createSequentialGroup()
					.addGap(162)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCencel, GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_mainFiled.setVerticalGroup(
			gl_mainFiled.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainFiled.createSequentialGroup()
					.addGap(79)
					.addComponent(textuser, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ipAddress, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_mainFiled.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCencel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnOk, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		mainFiled.setLayout(gl_mainFiled);
	}

	public String getTextuser() {
		return textuser.getText();
	}

	public String getPasswordField() {
		return passwordField.getText();
	}

	public String getIpAddress() {
		return ipAddress.getText();
	}
	
	public boolean isEmpty() {
		if(getTextuser() == null || getPasswordField() == null || getIpAddress() == null ) return true;
		return false;
	}
}
