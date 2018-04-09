import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeletePatient extends JFrame {
	
	DatabaseHandler db = new DatabaseHandler();
	Container c;
	JLabel lblId;
	JTextField txtId;
	JButton btnSave,btnBack;
	JPanel p1,p2;

	DeletePatient(){
		c=getContentPane();
		p1=new JPanel();

		lblId=new JLabel("ID:");
		txtId=new JTextField(5);

		p1.add(lblId);
		p1.add(txtId);

		c.add(p1);

		p2=new JPanel();

		btnSave=new JButton("Save");
		btnBack=new JButton("Back");

		p2.add(btnSave);
		p2.add(btnBack);

		c.add("South",p2);

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
				
					db.deletePatient(pid);

				txtId.setText("");
				txtId.requestFocus();
			}//end of actionPerformed
		});//end of btnSave

		btnBack.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent ae){
				MainPage m=new MainPage();
				dispose();
			}
		});

		setSize(500,150);
		setLocationRelativeTo(null);
		setTitle("Delete Patient");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String args[])
	{
		DeletePatient d=new DeletePatient();
	}//end of main

}//end of class