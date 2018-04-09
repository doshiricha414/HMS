import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
class DatabaseHandler extends JFrame
{
	public static Connection con;
	public static void getConnection()
	{
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HMS", "hello123");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}//end of getConnection
	public void addPatient(int pid, String pname, String gender, long contact, String adate, int did)
	{
		getConnection();
		try {
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date = sdf1.parse(adate);
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
			
			 String query= "SELECT count(*) AS COUNT FROM PATIENT WHERE ADATE=? and Did = ?";
			 PreparedStatement pst1 = con.prepareStatement(query);
			 pst1.setDate(1, sqlStartDate);
			 pst1.setInt(2, did);
			 ResultSet rs= pst1.executeQuery();
			 rs.next();
			 int count = rs.getInt("count");
			 
			 if(count>=2)
			 {
					JOptionPane.showMessageDialog(new JDialog(),"No appointments available");
					return;
			 }
			String s = "INSERT INTO PATIENT VALUES(?, ?, ?, ?, ?, ?) ";
			PreparedStatement pst = con.prepareStatement(s);
			pst.setInt(1, pid);
			pst.setString(2, pname);
			pst.setString(3, gender);
			pst.setLong(4,contact);
			pst.setDate(5, sqlStartDate);
			pst.setInt(6, did);
			System.out.println(pst);

			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " records inserted.");
		}//end of try
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Insertion Cannot Be Performed");
		}//end of catch
	}//end of addEmployee
	
	public void updatePatient(int pid, String pname, String gender, long contact, int did)
	{
		getConnection();
		try {
			
			String s = "UPDATE PATIENT SET Name=?, Gender=?, Contact=?, Did=? where Pid=? ";
			PreparedStatement pst = con.prepareStatement(s);
			pst.setString(1, pname);
			pst.setString(2, gender);
			pst.setLong(3,contact);
			pst.setInt(4, did);
			pst.setInt(5, pid);
			System.out.println(pst);

			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " records updated.");
		}//end of try
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Error occured in insertion." + e);
		}//end of catch
	
	}//end of addEmployee
	
	public void deletePatient (int pid)
	{
		getConnection();
		try {
			String s = "DELETE FROM PATIENT where Pid=? ";
			PreparedStatement pst = con.prepareStatement(s);
			pst.setInt(1, pid);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " records deleted.");
		}//end of try
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Error occured in insertion." + e);
		}//end of catch
	
	}
	
	
	
	
	public void createAccount(String username, String password)
	{
		getConnection();
		try {
		String s = "INSERT INTO ACCOUNT VALUES(?, ?) ";
		PreparedStatement pst = con.prepareStatement(s);
		pst.setString(1, username);
		pst.setString(2, password);
		
		
		int r = pst.executeUpdate();
		JOptionPane.showMessageDialog(new JDialog(), r + " records inserted.");
		}
		catch(Exception e)
		{
			

			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Error occured in insertion." + e);
		}
		
		
	}
	
	
	
	public void authentication(String username, String password)
	{
		
		getConnection();
		
			
		 try {
			 
			 String query= " SELECT count(*) as count FROM ACCOUNT where username like ? and password like ?";
			 
			 PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, username);
				pst.setString(2, password);
				
				ResultSet rs = pst.executeQuery();
				
				rs.next();
				 int count = rs.getInt("count");
				 
				 if(count==1)
				 {
					 JOptionPane.showMessageDialog(new JDialog(), "login successful!"); 
					
					 MainPage mp = new MainPage();
					 dispose();
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(new JDialog(), "login unsuccessful( create an account)"); 
					 return;
				 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
				JOptionPane.showMessageDialog(new JDialog(), "Error occured in deletion." + e); 
		 }
				 
		 
		 
		 
	}
	
	
	public StringBuffer viewDoctor(int Pid)
	{
		getConnection();
		StringBuffer result=new StringBuffer("");
		String s = "SELECT DOCTOR.Name as dname, PATIENT.Name as pname, Adate FROM  PATIENT, DOCTOR WHERE PATIENT.Did=DOCTOR.Did AND PATIENT.Pid=" + Pid;
		System.out.println(s);
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(s);
			
			while(rs.next())
			{
				String pname = rs.getString("pname");
				String adate = rs.getString("Adate").substring(0,10);
				
				
			result.append("Doctor Name : \t" + rs.getString("dname") + "\nPatient:\nName :" + pname  + "\nDate of Appointment :" + adate + "\n");
			
			
			}
			result.toString();
			return result;
		}//end of try
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Error occured in Viewing." + e);
		}//end of catch
		return result; 
	}
	
	
	
	
	
	
	
	
	
	public StringBuffer viewPatient(int Did)
	{
		getConnection();
		StringBuffer result=new StringBuffer("");
		String s = "SELECT DOCTOR.Name as dname, PATIENT.Name as pname, Adate, Gender, Contact FROM  PATIENT, DOCTOR WHERE PATIENT.Did=DOCTOR.Did AND DOCTOR.Did=" + Did;
		System.out.println(s);
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(s);
			int i = 1;
			while(rs.next())
			{
				String pname = rs.getString("pname");
				String adate = rs.getString("Adate").substring(0,10);
				String gender= rs.getString("Gender");
				long contact = rs.getLong("Contact");
				if(i == 1)
					result.append("Doctor Name : \n" + rs.getString("dname") + "\n\nPatients:\nName :" + pname + "\tGender :" + gender + "\tContact :" + contact + "\tDate of Appointment :" + adate + "\n");
				else
					result.append("\nName :" + pname + "\tGender :" + gender + "\tContact :" + contact + "\tDate of Appointment :" + adate + "\n");
				i++;
			}
			result.toString();
			return result;
		}//end of try
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Error occured in Viewing." + e);
		}//end of catch
		return result; 
	}
	
	
	
	
	
	
	
	
	public void deleteEmployee(int id)
	{
		getConnection();
		String s = "DELETE FROM EMPLOYEE WHERE EID = " + id;
		try {
			Statement st = con.createStatement();
			int r=st.executeUpdate(s);
			JOptionPane.showMessageDialog(new JDialog(), r + " record deleted.");
		}//end of try
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Error occured in deletion." + e);
		}//end of catch
	}
	public void updateEmployee(int id, String name)
	{
		getConnection();
	
	}		
}