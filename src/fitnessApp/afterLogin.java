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
import javax.swing.SwingConstants;

@SuppressWarnings({ "serial", "unused" })
public class afterLogin extends JFrame {

	private JPanel contentPane;
	private JTextField weightField;
    private JLabel CaloriesLbl = new JLabel("Calories:");
    private JLabel proteinLbl = new JLabel("Protein:");
	private JLabel carbsLbl = new JLabel("Carbohydrates:");
	private JLabel fatLbl = new JLabel("Fats:");
	@SuppressWarnings("rawtypes")
	private JComboBox activityField = new JComboBox();
	private final JLabel lblNewLabel = new JLabel("11 = Activity Level One");
	private final JLabel lblActivity = new JLabel("12 = Activity Level Two");
	private final JLabel lblActivity_1 = new JLabel("13 = Activity Level Three");
	private final JLabel lblAvtivity = new JLabel("14 = Avtivity Level Four");
	private final JButton btnProfile = new JButton("Profile");
	private final JButton btnSave = new JButton("Save");
	private JLabel caloriesLabel = new JLabel("");
	private JLabel proteinLabel = new JLabel("");
	private JLabel carbsLabel = new JLabel("");
	private JLabel fatsLabel = new JLabel("");
	private String id;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					afterLogin frame = new afterLogin("0");
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
	@SuppressWarnings("unchecked")
	public afterLogin(String userID) {
		id = userID;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterWeightkg = new JLabel("Enter Weight (KG)");
		lblEnterWeightkg.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblEnterWeightkg.setBounds(10, 11, 129, 46);
		contentPane.add(lblEnterWeightkg);
		
		JLabel lblActivityLevel = new JLabel("Activity Level");
		lblActivityLevel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblActivityLevel.setBounds(10, 49, 129, 46);
		contentPane.add(lblActivityLevel);
		
		weightField = new JTextField();
		weightField.setBounds(166, 25, 86, 20);
		contentPane.add(weightField);
		weightField.setColumns(10);
		
		// calculates macros for cut when cut button is pressed
		
		JButton btnCalculate = new JButton("Cut");
		btnCalculate.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(weightField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "No data");
				}
				else{
					double weightLbs = Double.parseDouble(weightField.getText()) * 2.20462262;
					double calories = weightLbs * Integer.parseInt((String) activityField.getSelectedItem());
					double protein = weightLbs;
					double fats = (double) (weightLbs * 0.3);
					double carbs;
					
					carbs = (((protein * 4) + (fats * 9)) - weightLbs) / 4;
					
					caloriesLabel.setText(""+(int) calories);;
					proteinLabel.setText(""+(int) protein);
					fatsLabel.setText(""+(int) fats);
					carbsLabel.setText(""+(int) carbs);
				}
				
			}
		});
		btnCalculate.setBounds(166, 99, 89, 23);
		contentPane.add(btnCalculate);
		
		// logout button  closes afterlogin form and takes you back to login
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				window frame = new window();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnLogOut.setBounds(323, 232, 89, 23);
		contentPane.add(btnLogOut);
		CaloriesLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		CaloriesLbl.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		
		
		CaloriesLbl.setBounds(274, 28, 92, 14);
		contentPane.add(CaloriesLbl);
		proteinLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		proteinLbl.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		
		
		proteinLbl.setBounds(274, 66, 92, 14);
		contentPane.add(proteinLbl);
		carbsLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		carbsLbl.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		
	
		carbsLbl.setBounds(274, 103, 92, 14);
		contentPane.add(carbsLbl);
		fatLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		fatLbl.setFont(new Font("Trajan Pro", Font.PLAIN, 9));
		
		
		fatLbl.setBounds(274, 138, 92, 14);
		contentPane.add(fatLbl);
		
		
		activityField.setModel(new DefaultComboBoxModel(new String[] {"11", "12", "13", "14"}));
		activityField.setBounds(166, 63, 86, 20);
		contentPane.add(activityField);
		lblNewLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 156, 197, 14);
		
		contentPane.add(lblNewLabel);
		lblActivity.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblActivity.setBounds(10, 181, 197, 14);
		
		contentPane.add(lblActivity);
		lblActivity_1.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblActivity_1.setBounds(10, 208, 197, 14);
		
		contentPane.add(lblActivity_1);
		lblAvtivity.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		lblAvtivity.setBounds(10, 236, 197, 14);
		
		contentPane.add(lblAvtivity);
		
		// calculates macros for bulk when bulk button pressed
		
		JButton btnBulk = new JButton("Bulk");
		btnBulk.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnBulk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(weightField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "No data");
				}
				else{
					double weightLbs = Double.parseDouble(weightField.getText()) * 2.20462262;
					double calories = weightLbs * Integer.parseInt((String) activityField.getSelectedItem()) + 500;
					double protein = weightLbs;
					double fats = (double) (weightLbs * 0.6);
					double carbs;
					
					carbs = (((protein * 4) + (fats * 9)) - weightLbs) / 4;
					
					caloriesLabel.setText(""+(int)calories);;
					proteinLabel.setText(""+(int)protein);
					fatsLabel.setText(""+(int)fats);
					carbsLabel.setText(""+(int)carbs);
				}

			}
		});
		btnBulk.setBounds(66, 99, 89, 23);
		contentPane.add(btnBulk);
		btnProfile.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Profile frame = new Profile(userID);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		btnProfile.setBounds(323, 204, 89, 23);
		
		// profile button and saving macros and checking if not empty
		
		contentPane.add(btnProfile);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
	
				String calories, carbohydrates, fats, protein;
				calories = caloriesLabel.getText();
				carbohydrates = carbsLabel.getText();
				fats = fatsLabel.getText();
				protein = proteinLabel.getText();
				
				
				try {
			    	if(caloriesLabel.getText().equals("")){
			    		 // Checking if Workplace is empty
			    		 JOptionPane.showMessageDialog(null, "No data");
			    	}
			    		else if(carbsLabel.getText().equals("")){
			    		  // Checking if username is empty
					      JOptionPane.showMessageDialog(null, "No data");
			    	  }
					   else if(fatsLabel.getText().equals("")){
						   // Checking if password is empty
						  JOptionPane.showMessageDialog(null, "No data");
					   }
					   else if(proteinLabel.getText().equals("")){  
						   //  Checking if Name is empty
						  JOptionPane.showMessageDialog(null, "No data");
					   }
					   else{
						   // saves macros on afterlogin page to database to be used later and the weight is saved to database too
						   
						   Class.forName("com.mysql.jdbc.Driver").newInstance();
						   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=");
						   Statement st = con.createStatement();
						   /*								MySQL command 																*/
						   st.executeUpdate("insert into macros(id, protein, carbs, fat, calories) VALUES( '"
								   +id+"','"+Integer.valueOf(protein)+"','"+Integer.valueOf(carbohydrates)+"','"
								   	+Integer.valueOf(fats)+"' ,'"+Integer.valueOf(calories)+"')");
						   st.executeUpdate("update users set weight='"+weightField.getText()+"' where id="+id);
						   JOptionPane.showConfirmDialog(null, "Macros updated", "User", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						   st.close();
						   con.close();
					 }
			    	  
			    }//try
			    catch (Exception e1){
			        System.out.println("Exception:" + e1);
			    }
			}		
		});
		btnSave.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		btnSave.setBounds(323, 177, 89, 23);
		
		contentPane.add(btnSave);
		
		caloriesLabel.setBounds(376, 27, 46, 14);
		contentPane.add(caloriesLabel);
		
		proteinLabel.setBounds(376, 65, 46, 14);
		contentPane.add(proteinLabel);
		
		carbsLabel.setBounds(376, 103, 46, 14);
		contentPane.add(carbsLabel);
		
		fatsLabel.setBounds(376, 137, 46, 14);
		contentPane.add(fatsLabel);
	}
	
	public void setID(String myID){
		id = myID;
	}
}
