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
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField nameField;
	private JTextField surnameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		lblUsername.setBounds(21, 39, 115, 57);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		lblPassword.setBounds(21, 83, 115, 57);
		contentPane.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setBounds(132, 58, 266, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(132, 102, 266, 20);
		contentPane.add(passwordField);
		
		
		// back button
		
		JButton exitButton = new JButton("Back");
		exitButton.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				window frame = new window();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		exitButton.setBounds(335, 227, 89, 23);
		contentPane.add(exitButton);
		
		// register button checks if fields are empty 
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				
				String username = null, password = null, name = null, surname = null;
				
				try {
			    	if(usernameField.getText().equals("")){
			    		 // Checking if Workplace is empty
			    		 JOptionPane.showMessageDialog(null, "No data");
			    	}
			    		else if(passwordField.getText().equals("")){
			    		  // Checking if username is empty
					      JOptionPane.showMessageDialog(null, "No data");
			    	  }
					   else if(nameField.getText().equals("")){
						   // Checking if password is empty
						  JOptionPane.showMessageDialog(null, "No data");
					   }
					   else if(surnameField.getText().equals("")){  
						   //  Checking if Name is empty
						  JOptionPane.showMessageDialog(null, "No data");
					   }
					   else{
						 
						username = usernameField.getText();
						password = passwordField.getText();
						name = nameField.getText();
						surname = surnameField.getText();
						   // if username is already present in databse than another username is asked
						   if(exists(username)){
							   JOptionPane.showMessageDialog(null, "Username Active! Use different username!");
						   }
						   
						 // Create user
						   else{
						 
							   
							   /*								connection									*/ 
							   Class.forName("com.mysql.jdbc.Driver").newInstance();
							   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=");
							   Statement st = con.createStatement();
							   /*								MySQL command 																*/
							   st.executeUpdate("insert into users(name, surname, username, password) VALUES( '"
							   +name+"','"+surname+"','"+username+"' ,'"+password+"')");
							   JOptionPane.showConfirmDialog(null, "User Created!", "User", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
							   st.close();
							   con.close();
							   /*									Close Registration 														*/
							   dispose();
							   afterLogin frame = new afterLogin("0");
							   frame.setVisible(true);
							   frame.setLocationRelativeTo(null);
							   
						   }
					 }
			    	  
			    }//try
			    catch (Exception e1)

			    {
			        System.out.println("Exception:" + e1);
			    }
				
			}
		});
		btnRegister.setBounds(239, 227, 89, 23);
		contentPane.add(btnRegister);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		lblName.setBounds(21, 128, 115, 57);
		contentPane.add(lblName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(132, 147, 266, 20);
		contentPane.add(nameField);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Trajan Pro", Font.PLAIN, 15));
		lblSurname.setBounds(21, 177, 115, 57);
		contentPane.add(lblSurname);
		
		surnameField = new JTextField();
		surnameField.setColumns(10);
		surnameField.setBounds(132, 196, 266, 20);
		contentPane.add(surnameField);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the following details");
		lblPleaseEnterThe.setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		lblPleaseEnterThe.setBounds(54, 0, 344, 36);
		contentPane.add(lblPleaseEnterThe);
	}
	
	// checks if username already exists
	
	private boolean exists(String username) {
		   try{           
			   
		       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=");   
		       /*								MySQL command 																*/
		       PreparedStatement pst = conn.prepareStatement("Select username from users where username = ?");
		       pst.setString(1, username); 
		       pst.executeQuery();                        
		       ResultSet rs = pst.executeQuery();                        
		       if(rs.next()){            
		           return true;    
		       }
		   }
		   catch(Exception e){
		       e.printStackTrace();
		       return false;
		   }
		return false;
   
		}
}
