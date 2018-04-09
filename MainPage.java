import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class MainPage extends JFrame{

	Container c;
	JButton add_patient,update_Patient,delete_Patient,view_Patient,view_doctor;
	JPanel p1,p2;

	public MainPage(){

		c = getContentPane();
		c.setLayout(null); 
		p1 = new JPanel();
		p2 = new JPanel();
		add_patient = new JButton("Add Patient");
		p1.add(add_patient);
		view_Patient = new JButton("View Patient");
		p1.add(view_Patient);
		update_Patient = new JButton("Update Patient");
		p1.add(update_Patient);
		delete_Patient = new JButton("Delete Patient");
		p1.add(delete_Patient);
		p1.setBounds(10,20,550,50);
		c.add(p1);


		view_doctor = new JButton("View Appointment Details");
		p2.add(view_doctor);
		p2.setBounds(10,80,550,50);
		c.add(p2);

		add_patient.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				AddPatient a = new AddPatient();
				dispose();
			}
		});

		view_Patient.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				ViewPatient a = new ViewPatient();
				dispose();
			}
		});

		update_Patient.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				UpdatePatient a = new UpdatePatient();
				dispose();
			}
		});

		delete_Patient.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				DeletePatient a = new DeletePatient();
				dispose();
			}
		});

		view_doctor.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				ViewDoctor a = new ViewDoctor();
				dispose();
			}
		});

		setSize(600,250);
		setLocationRelativeTo(null);
		setTitle("Hospital Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String arg[]){
		new MainPage();
	}
}
/*
Compile : javac -cp ojdbc14.jar *.java
Run : java -cp ojdbc14.jar;. MainFrame
 */






