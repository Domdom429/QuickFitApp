package fitnessApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class Profile extends JFrame {

	private JPanel contentPane;
	private final JButton btnBack = new JButton("Back");
	private final JLabel lblProfileMessage = new JLabel("Hello .....");
	private JTextField currentWeightField;
	private JLabel lblFats = new JLabel("Fats :");
	private JLabel lblProtein = new JLabel("Protein :");
	private JLabel lblCalories = new JLabel("Calories :");
	private JLabel lblCarbohydrates = new JLabel("Carbohydrates :");
	private String id;
	private JLabel lblWeightUpdate = new JLabel("You have lost/gained ...... KG!");
	private JLabel lblCurrentWeightHeader = new JLabel("Current Weight: ");
	private String weight=".......";
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile("0");
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
	public Profile(String userID) {
		id = userID;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnBack.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				afterLogin frame = new afterLogin(userID);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		
		contentPane.add(btnBack);
		lblProfileMessage.setFont(new Font("Trajan Pro", Font.PLAIN, 33));
		lblProfileMessage.setBounds(10, -33, 345, 107);
		
		contentPane.add(lblProfileMessage);
		
		JLabel lblCurrentMacros = new JLabel("Current Macros");
		lblCurrentMacros.setFont(new Font("Trajan Pro", Font.BOLD, 14));
		lblCurrentMacros.setBounds(10, 62, 149, 29);
		contentPane.add(lblCurrentMacros);
			
		lblCalories.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		lblCalories.setBounds(10, 102, 149, 29);
		contentPane.add(lblCalories);

		lblCarbohydrates.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		lblCarbohydrates.setBounds(10, 130, 149, 29);
		contentPane.add(lblCarbohydrates);

		lblFats.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		lblFats.setBounds(10, 158, 149, 29);
		contentPane.add(lblFats);
		
		
		lblProtein.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		lblProtein.setBounds(10, 184, 149, 29);
		contentPane.add(lblProtein);
		
		JLabel lblGoals = new JLabel("Goals");
		lblGoals.setFont(new Font("Trajan Pro", Font.BOLD, 14));
		lblGoals.setBounds(180, 65, 124, 22);
		contentPane.add(lblGoals);
		
		JLabel lblCurrentWeight = new JLabel("Update Weight (KG)  :");
		lblCurrentWeight.setFont(new Font("Trajan Pro", Font.PLAIN, 10));
		lblCurrentWeight.setBounds(180, 105, 145, 23);
		contentPane.add(lblCurrentWeight);
		
		currentWeightField = new JTextField();
		currentWeightField.setBounds(335, 106, 89, 20);
		contentPane.add(currentWeightField);
		currentWeightField.setColumns(10);
		
		
		
		lblCurrentWeightHeader.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblCurrentWeightHeader.setBounds(239, 62, 195, 29);
		contentPane.add(lblCurrentWeightHeader);
		
		
		// update the weight
		
		JButton btnSet = new JButton("Set");
		btnSet.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnSet.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {

				String CurrentWeightHeader = null;
				
				try {
			    	if(currentWeightField.getText().equals("")){
			    		 // Checking if Workplace is empty
			    		 JOptionPane.showMessageDialog(null, "No data");
			    	}
				   else{
					   CurrentWeightHeader = (currentWeightField.getText());
					   Class.forName("com.mysql.jdbc.Driver").newInstance();
					   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=");
					   Statement st = con.createStatement();
					   /*								MySQL command 																*/
					   st.executeUpdate("update users set weight='"+CurrentWeightHeader+"' where id="+id);
					   JOptionPane.showConfirmDialog(null, "Weight updated!", "User", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					   st.close();
					   con.close();
					   /*									Close Registration 														*/
					   lblCurrentWeightHeader.setText("Current Weight: " + CurrentWeightHeader);
					   
					   if(Integer.valueOf(weight) < Integer.valueOf(CurrentWeightHeader)){
						   lblWeightUpdate.setText("You have gained " + (Integer.valueOf(CurrentWeightHeader) - Integer.valueOf(weight)) + " KG!");
						   onLoad();
					   }
					   else{
						   lblWeightUpdate.setText("You have lost " + (Integer.valueOf(weight) - Integer.valueOf(CurrentWeightHeader)) + " KG!");
						   onLoad();
					   }
					   
				 }	  
			    }catch (Exception e1){
			        System.out.println("Exception:" + e1);
			    }
				
			}
		});
		btnSet.setBounds(335, 130, 89, 23);
		contentPane.add(btnSet);
		
		lblWeightUpdate.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblWeightUpdate.setBounds(180, 228, 225, 19);
		contentPane.add(lblWeightUpdate);
		
		onLoad();
	}
	
	// when profile page is loaded all the data of the user is loaded from the database
	
	public void onLoad(){
		int calories=0, protein=0, carbs=0, fats=0;
		try{	 
		       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=");     
		       PreparedStatement pst = conn.prepareStatement("Select * from macros where id=?");
		       pst.setString(1, id); 
		       ResultSet rs = pst.executeQuery();                        
		       while(rs.next()){
		    	   protein = rs.getInt("protein");
		    	   carbs = rs.getInt("carbs");
		    	   fats = rs.getInt("fat");
		    	   calories = rs.getInt("calories");
		       }
		       
		       lblCalories.setText("Calories: " + calories);
		       lblCarbohydrates.setText("Carbohydrates: " + carbs);
		       lblProtein.setText("Protein: " + protein);
		       lblFats.setText("Fats: " + fats);
		   }
		   catch(Exception e){
		       e.printStackTrace();
		   } 
		
		String name=".....";
		
		
		try{	 
			
			// retrieving name and weight from the database
		       Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
		       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=");     
		       PreparedStatement pst = conn.prepareStatement("Select name, weight from users where id=?");
		       pst.setString(1, id); 
		       ResultSet rs = pst.executeQuery();                        
		       while(rs.next()){
		    	   name = rs.getString("name");
		    	   weight = rs.getString("weight");
		       }
		       
		       lblProfileMessage.setText("Hello " + name);
		       lblCurrentWeightHeader.setText("Current Weight: "+ weight);
		   }
		   catch(Exception e){
		       e.printStackTrace();
		   }     
	}
}
