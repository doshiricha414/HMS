import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



class AddPatient extends JFrame{
	Container c;
	JLabel lblId,lblName,lblGender,lblContact,lblDid,lblDate;
	JPanel p1,p2,p3,p4;
	JTextField txtId,txtName,txtGender,txtContact,txtDid,txtDate;
	JButton btnSave,btnBack;
	int index; 
	AddPatient(){
		
		DatabaseHandler db = new DatabaseHandler();
		db.getConnection();
		c = getContentPane();
		c.setLayout(null);
		p1 = new JPanel();
		lblId= new JLabel("ID:");
		txtId = new JTextField(5);
		lblName = new JLabel("NAME:");
		txtName = new JTextField(10);
		lblGender = new JLabel("Gender(M/F):");
		txtGender = new JTextField(3);
		lblContact= new JLabel("Contact:");
		txtContact = new JTextField(10);

		lblDid= new JLabel("Doctor Id:");
		txtDid = new JTextField(10);

		lblDate= new JLabel("Appointment Date");
		txtDate = new JTextField(10);

		lblContact= new JLabel("Contact:");
		txtContact = new JTextField(10);
		
		String selected_did;
		
		JComboBox combo = new JComboBox();


		
		db.getConnection();
		try {
		String query= "SELECT DID, NAME, SPECIALITY FROM DOCTOR";
		Statement stmt = db.con.createStatement();
		ResultSet rs= stmt.executeQuery(query);
		 while(rs.next())
		 {
			 int did= rs.getInt("DID");
			 String name= rs.getString("NAME");
			 String speciality= rs.getString("SPECIALITY");
			 String s= did+"."+name+"|"+speciality;
			 combo.addItem(s);
			 
			 			 
		 }
		 
		}
		catch(Exception e)
		{
			
		}
		
		 combo.addActionListener(
	                new ActionListener(){
	                    public void actionPerformed(ActionEvent e){
	                        JComboBox combo = (JComboBox)e.getSource();
	                         String selected_did = (String)combo.getSelectedItem();
	                         int i= selected_did.indexOf('.');
	                         index= Integer.parseInt(selected_did.substring(0,i));
	                         System.out.println(selected_did);
	                         
	                    }
	                }            
	        );

		 
		 
		 
		 

		p1.add(lblId);
		p1.add(txtId);
		p1.add(lblName);
		p1.add(txtName);
		p1.add(lblGender);
		p1.add(txtGender);
		p1.setBounds(10,10,550,50);
		//p1.setBackground(Color.RED);
		c.add(p1);
		p3 = new JPanel();
		p3.add(lblContact);
		p3.add(txtContact);	
		p3.add(lblDid);
		//p3.add(txtDid);	
		p3.add(combo);
		p3.setBounds(10,60,550,50);
		//p3.setBackground(Color.YELLOW);
		c.add(p3);		

		p4 = new JPanel();
		p4.add(lblDate);
		p4.add(txtDate);
		p4.setBounds(10,100,550,50);
		c.add(p4);

		p2 = new JPanel();
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");
		p2.add(btnSave);
		p2.add(btnBack);
		p2.setBounds(10,150,550,50);
		//p2.setBackground(Color.PINK);
		c.add(p2);
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				int pid;
				try {
					pid = Integer.parseInt(txtId.getText());
				} catch(Exception e) {
					JOptionPane.showMessageDialog(new JDialog(),"Id is empty or invalid");
					txtId.requestFocus();
					return;
				}
				String pname = txtName.getText();
				String gender = txtGender.getText();
				long contact = Long.parseLong(txtContact.getText());
				String adate = txtDate.getText();
				
				
				
				
				if(pname.length()==0) //Validation
				{
					JOptionPane.showMessageDialog(new JDialog(),"Name is empty");
					txtName.requestFocus();
					return;
				}
				if(gender.length()==0) //Validation
				{
					JOptionPane.showMessageDialog(new JDialog(),"Gender is empty");
					txtName.requestFocus();
					return;
				}
				
				db.addPatient(pid, pname, gender, contact, adate, index);
				txtId.setText("");
				txtName.setText("");
				txtGender.setText("");
				txtContact.setText("");
				txtDid.setText("");
				txtDate.setText("");
				txtId.requestFocus();
			}
		});
		
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				MainPage m = new MainPage();
				dispose();
			}
		});
		setSize(550,300);
		setLocationRelativeTo(null);
		setTitle("Add Patient Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/*public void dropdown()
	{
		
		
		}
		catch(Exception e)
		{
		}
		
		
		
		}*/
		 
	

	public static void main(String arg[]){
		AddPatient a = new AddPatient();
	}

}	