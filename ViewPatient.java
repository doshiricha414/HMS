import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class ViewPatient extends JFrame
{

	Container c;
	JTextArea taData;
	JScrollPane spData;
	JButton btnBack, btnView;
	JTextField txtDid;
	JLabel lbDid;
	JPanel p1,p2,p3;
	ViewPatient()
	{
		c=getContentPane();
		c.setLayout(null);
		p1=new JPanel();
		p3=new JPanel();
		p2=new JPanel();
		
		lbDid= new JLabel("D_id");
		txtDid= new JTextField(10);
		btnView = new JButton("view");
		taData=new JTextArea(9,40);
		taData.setEditable(false);
		spData=new JScrollPane(taData);
		spData.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spData.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		btnBack=new JButton("Back");
		
		p1.add(lbDid);
		p1.add(txtDid);
		p1.add(btnView);
		p3.add(spData);
		p1.setBounds(10, 10, 550, 50);
		p2.add(btnBack);
		p3.setBounds(10, 60, 550, 200);
		p2.setBounds(10, 250, 550, 80);
		c.add(p2);
		c.add(p1);
		c.add(p3);
		
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
				int did = Integer.parseInt(txtDid.getText());
				taData.setText(db.viewPatient(did).toString());
			}
		});
		setSize(550,350);
		setLocationRelativeTo(null);
		setTitle("View Patients:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}//end of constructor
	public static void main(String args[]){
		ViewPatient v=new ViewPatient();
	}//end of main

}//end of class
