
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class subjectname extends JApplet implements ActionListener,FocusListener
{
	JComboBox cbbranch,cbsem;
	JLabel lbsub,lbthcode[],lbthsub[],lbsem,lbbranch,lbpcode[],lbpsub[];
	JTextField txsub,txthcode[],txthsub[],txpcode[],txpsub[];
	JButton btadd,btclose;
	JPanel p1,p2,p3,p4;
	
	public void init()
	{
		cbbranch=new JComboBox();
		cbbranch.addItem("select");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stml=con.createStatement();
			stml.executeUpdate("create database if not exists studentmanagementdb");
			stml.execute("use studentmanagementdb");
			ResultSet rs=stml.executeQuery("select branch_name from branchtb"); 
			while(rs.next())
			{
				cbbranch.addItem(rs.getString(1));
			}
			con.close();
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		cbsem=new JComboBox();
		cbsem.addItem("select semester");
		cbsem.addItem("I");
		cbsem.addItem("II");
		
		
		lbsem=new JLabel("Semester");
		lbsub=new JLabel("No of Subjects");
		
		lbbranch=new JLabel("select branch");
		
		txsub=new JTextField(2);
		
		btadd=new JButton("add");
		btclose=new JButton("close");
		

		p1=new JPanel();
		p1.setLayout(new GridLayout(3,2));
		p1.add(lbbranch);
		p1.add(cbbranch);
		p1.add(lbsem);
		p1.add(cbsem);
		p1.add(lbsub);
		p1.add(txsub);
		
		
		p3=new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(btadd);
		p3.add(btclose);
		
		setLayout(new GridLayout(3,1));
		add(p1);

		
		
		btadd.addActionListener(this);
		btclose.addActionListener(this);
		txsub.addFocusListener(this);
	}


	public void actionPerformed(ActionEvent ae)
	{
		Object src=ae.getSource();
		
		if(src==btadd)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stml=con.createStatement();
				stml.executeUpdate("create database if not exists studentmanagementdb");
				stml.execute("use studentmanagementdb");
				stml.executeUpdate("create table if not exists subjectstb(branch_name varchar(20),sem varchar(20),Total_sub varchar(50),thsub_code1 varchar(20),thsub_name1 varchar(20),thsub_code2 varchar(20),thsub_name2 varchar(20),thsub_code3 varchar(20),thsub_name3 varchar(20),thsub_code4 varchar(20),thsub_name4 varchar(20),thsub_code5 varchar(20),thsub_name5 varchar(20),thsub_code6 varchar(20),thsub_name6 varchar(20),psub_code1 varchar(20),psub_name1 varchar(20),psub_code2 varchar(20),psub_name2 varchar(20),psub_code3 varchar(20),psub_name3 varchar(20),psub_code4 varchar(20),psub_name4 varchar(20))");
				PreparedStatement pstmt=con.prepareStatement("insert into subjectstb(branch_name,sem,Total_sub,thsub_code1,thsub_name1,thsub_code2,thsub_name2,thsub_code3,thsub_name3,thsub_code4,thsub_name4,thsub_code5,thsub_name5,thsub_code6,thsub_name6,psub_code1,psub_name1,psub_code2,psub_name2,psub_code3,psub_name3,psub_code4,psub_name4)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1,cbbranch.getSelectedItem().toString());
				pstmt.setString(2,cbsem.getSelectedItem().toString());
				pstmt.setString(3,txsub.getText());
				pstmt.setString(4,txthcode[0].getText());
				pstmt.setString(5,txthsub[0].getText());
				pstmt.setString(6,txthcode[1].getText());
				pstmt.setString(7,txthsub[1].getText());
				pstmt.setString(8,txthcode[2].getText());
				pstmt.setString(9,txthsub[2].getText());
				pstmt.setString(10,txthcode[3].getText());
				pstmt.setString(11,txthsub[3].getText());
				pstmt.setString(12,txthcode[4].getText());
				pstmt.setString(13,txthsub[4].getText());
				pstmt.setString(14,txthcode[5].getText());
				pstmt.setString(15,txthsub[5].getText());
				pstmt.setString(16,txpcode[0].getText());
				pstmt.setString(17,txpsub[0].getText());
				pstmt.setString(18,txpcode[1].getText());
				pstmt.setString(19,txpsub[1].getText());
				pstmt.setString(20,txpcode[2].getText());
				pstmt.setString(21,txpsub[2].getText());
				pstmt.setString(22,txpcode[3].getText());
				pstmt.setString(23,txpsub[3].getText());



				pstmt.executeUpdate();
				con.close();
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		if(src==btclose)
		{
			System.exit(0);
		}
		
	}


	public void focusGained(FocusEvent e)
	{
		
		
	}


	public void focusLost(FocusEvent e) 
	{
		Object src=e.getSource();
	
		if(src==txsub)
		{
		
		int n=Integer.parseInt(txsub.getText());
		
		lbthcode=new JLabel[n];
		lbthsub=new JLabel[n];
		txthcode=new JTextField[n];
		txthsub=new JTextField[n];

		lbpcode=new JLabel[4];
		lbpsub=new JLabel[4];
		txpcode=new JTextField[4];
		txpsub=new JTextField[4];
		
		for(int i=0;i<n;i++)
		{
			lbthcode[i]=new JLabel("Subject Code "+(i+1));
			lbthsub[i]=new JLabel("Subject Name "+(i+1));
			txthcode[i]=new JTextField(50);
			txthsub[i]=new JTextField(50);
			
		}
		for(int i=0;i<4;i++)
		{
			lbpcode[i]=new JLabel("Practical Sub Code " +(i+1));
			lbpsub[i]=new JLabel("Practical Sub "+(i+1));
			txpcode[i]=new JTextField(10);
			txpsub[i]=new JTextField(50);
		}
		if(p2 !=null)
			remove(p2);
		
		p2=new JPanel();
		p2.setLayout(new GridLayout(2*(n+4), 2));
		for(int i=0;i<n;i++)
		{
		p2.add(lbthcode[i]);
		p2.add(txthcode[i]);
		p2.add(lbthsub[i]);
		p2.add(txthsub[i]);
		}
		
		for(int i=0;i<4;i++)
		{
		p2.add(lbpcode[i]);
		p2.add(lbpsub[i]);
		p2.add(txpcode[i]);
		p2.add(txpsub[i]);

		}
		add(p2,BorderLayout.CENTER);
		add(p3,BorderLayout.SOUTH);
		validate();
		}
		
	}
	


}
