import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Authentication extends JFrame
{
	Container c;
	JLabel lblUsername,lblPassword;
	JPanel p1,p2,p3;
	JTextField txtUsername,txtPassword;
	JButton btnSignIn,btnNewAcc;
	DatabaseHandler db = new DatabaseHandler();
	
	
	Authentication(){
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
		btnSignIn = new JButton("SignIn");
		btnNewAcc = new JButton("Create Acccount");
		p3.add(btnSignIn);
		p3.add(btnNewAcc);
		p3.setBounds(10,120,550,50);
		c.add(p3);
		
		btnNewAcc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				CreateAcc m = new CreateAcc();
				dispose();
			}
		});
		
		btnSignIn.addActionListener(new ActionListener(){
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
				
				
				db.authentication( username, password);
				
						
				
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		
	setSize(600,250);
	setLocationRelativeTo(null);
	setTitle("Authentication");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
		
	}

	public static void main(String arg[]){
	Authentication a = new Authentication();
	}
}