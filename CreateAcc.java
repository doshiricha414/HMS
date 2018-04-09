import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class CreateAcc extends JFrame{

	Container c;
	JLabel lblUsername,lblPassword;
	JPanel p1,p2,p3;
	JTextField txtUsername,txtPassword;
	JButton btnCreate,btnBack;
	
	DatabaseHandler db= new DatabaseHandler();
	

	CreateAcc(){
	c = getContentPane();
		c.setLayout(null);
		p1 = new JPanel();
		lblUsername = new JLabel("Username:");
		txtUsername = new JTextField(10);
		p1.add(lblUsername);
		p1.add(txtUsername);
		p1.setBounds(10,5,550,50);
		c.add(p1);
		
		p2 = new JPanel();
		lblPassword = new JLabel("Password:");
		txtPassword = new JTextField(10);
		p2.add(lblPassword);
		p2.add(txtPassword);
		p2.setBounds(10,60,550,50);
		c.add(p2);
		
		p3 = new JPanel();
		btnCreate = new JButton("Sign Up");
		btnBack = new JButton("Already have an account?");
		p3.add(btnCreate);
		p3.add(btnBack);
		p3.setBounds(10,120,550,50);
		c.add(p3);
		
		setSize(600,250);
		setLocationRelativeTo(null);
		setTitle("Create New Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		btnCreate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				if(username.length()==0) {//Validation	
					JOptionPane.showMessageDialog(new JDialog(),"Username is empty");
					txtUsername.requestFocus();
					return;
				}
				if(password.length() == 0){
					JOptionPane.showMessageDialog(new JDialog(),"Password is empty");
					txtPassword.requestFocus();
					return;
				}
			        
				db.createAccount(username, password);
				
				txtUsername.setText("");
				txtPassword.setText("");
				
				
			}
		});
		
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Authentication m = new Authentication();
				dispose();
			}
		});
		
	}

	public static void main(String arg[]){
	CreateAcc a = new CreateAcc();
	}
	


}