package fitnessApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class window extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String id;
	private String userID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window frame = new window();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		lblUsername.setBounds(21, 65, 115, 57);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		lblPassword.setBounds(21, 112, 115, 57);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(132, 85, 266, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(132, 132, 266, 20);
		contentPane.add(textField_1);
		
	
		
		// login button creation  and method 
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = textField.getText();
				String password = textField_1.getText();
				
				if(username == null){
					  // Checking for empty field
				      JOptionPane.showMessageDialog(null, "Fill in all details!");
				      
				 }
				 
				   else if(password == null){ 
					  // Checking for empty field
				      JOptionPane.showMessageDialog(null, "Fill in all details!");
				      
				   }
				 
				   else{
				       
				       if(validate_login(username, password)){
				    	   afterLogin frame = new afterLogin(id);
				    	   frame.setLocationRelativeTo(null);
				    	   frame.setVisible(true);
				    	   dispose();
				       }//If  
				       else{
				          JOptionPane.showMessageDialog(null, "Incorrect Details!");
				       }//Else     
				   }//Else	
			}
		});
		btnLogin.setBounds(132, 178, 89, 23);
		contentPane.add(btnLogin);
		
		// exit button 
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}
		});
		exitButton.setBounds(335, 227, 89, 23);
		contentPane.add(exitButton);
		
		// register button and takes you to register form
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Register frame = new Register();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		btnRegister.setBounds(231, 178, 89, 23);
		contentPane.add(btnRegister);
		
		JLabel lblQuickfitFitnessHelper = new JLabel("QuickFit Fitness Helper");
		lblQuickfitFitnessHelper.setFont(new Font("Trajan Pro", Font.PLAIN, 19));
		lblQuickfitFitnessHelper.setBounds(84, 24, 360, 30);
		contentPane.add(lblQuickfitFitnessHelper);
	}
	
	// login validation function
	
	private boolean validate_login(String username,String password) {
		   try{	
			   
		       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=");     
		       PreparedStatement pst = conn.prepareStatement("Select * from users where username=? and password=?");
		       pst.setString(1, username); 
		       pst.setString(2, password);
		       ResultSet rs = pst.executeQuery();                        
		       if(rs.next()){
		    	   id = String.valueOf(rs.getInt("id"));
		    	   userID = id;
		    	   return true;   
		       }
		       
		       else{
		           return false;
		       }
		   }
		   catch(Exception e){
		       e.printStackTrace();
		       return false;
		   }       
		}
}
