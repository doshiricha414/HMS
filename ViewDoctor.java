import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewDoctor extends JFrame
{

	Container c;
	JTextArea taData;
	JScrollPane spData;
	JButton btnBack,btnView;
	JTextField txtPid;
	JLabel lbPid;
	JPanel p1,p2,p3,p4;
	
	DatabaseHandler db = new DatabaseHandler();
	ViewDoctor()
	{
		c=getContentPane();
		c.setLayout(null);
		p1=new JPanel();
		p3= new JPanel();
		p4= new JPanel();
		lbPid= new JLabel("Patient's ID");
		txtPid = new JTextField(10);
		btnView = new JButton("  View Appointment Details");
		taData=new JTextArea(9,30);
		taData.setEditable(false);
		spData=new JScrollPane(taData);
		spData.setVerticalScrollBarPolicy
		(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spData.setHorizontalScrollBarPolicy
		(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p3.add(lbPid);
		p3.add(txtPid);
		p4.add(btnView);
		p3.setBounds(2, 10, 650, 50);
		p4.setBounds(10,50, 650, 50);
		c.add(p3);
		c.add(p4);
		
		p1.add(spData);
		c.add(p1);
		p1.setBounds(10, 100, 650, 200);
		p2=new JPanel();
		btnBack=new JButton("Back");
		p2.add(btnBack);
		p2.setBounds(10, 290, 650, 80);
		c.add("South",p2);
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				MainPage m=new MainPage();
				dispose();
			}
		});
		
		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				DatabaseHandler db = new DatabaseHandler();
				int pid = Integer.parseInt(txtPid.getText());
				taData.setText(db.viewDoctor(pid).toString());
			}
		});
		
		
		 
		setSize(700,400);
		setLocationRelativeTo(null);
		setTitle("appointment Details:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}//end of constructor
	public static void main(String args[]){
		ViewDoctor v=new ViewDoctor();
	}//end of main

}//end of class
