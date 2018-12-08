


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Addnewuser extends JDialog implements ActionListener
{
	JLabel lb,lbid,lbname,lbpass,lbmob,lbemail,lbcb;
	JTextField txid,txname,txpass,txmob,txemail;
	JButton btadd,btclear,btexit;
	JComboBox cb;
	JPanel p1,p2,p3;
	
	public Addnewuser()
	{
		int w=this.getToolkit().getScreenSize().width;
		int h=this.getToolkit().getScreenSize().height;
		setVisible(true);
		setSize(w,h);

		Font f1=new Font("times new roman",Font.BOLD,42);
		Font f2=new Font("monotype corsiva",Font.PLAIN,20);
		
		lb=new JLabel("ADD NEW USERS");
		lb.setFont(f1);
		lb.setForeground(Color.RED);
		
		lbid=new JLabel("unique id");
		lbid.setFont(f2);
		lbid.setForeground(Color.blue);
		
		lbname=new JLabel("user name");
		lbname.setFont(f2);
		lbname.setForeground(Color.blue);
		
		lbpass=new JLabel("password");
		lbpass.setFont(f2);
		lbpass.setForeground(Color.blue);
		
		lbmob=new JLabel("mobile number");
		lbmob.setFont(f2);
		lbmob.setForeground(Color.blue);
		
		lbemail=new JLabel("email adress");
		lbemail.setFont(f2);
		lbemail.setForeground(Color.blue);
		
		lbcb=new JLabel("add As");
		lbcb.setFont(f2);
		lbcb.setForeground(Color.blue);

		txid=new JTextField(20);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stml=con.createStatement();
			stml.executeUpdate("create database if not exists studentmanagementdb");
			stml.execute("use studentmanagementdb");
			stml.executeUpdate("create table if not exists userstb(uniqueid varchar(20)"
					+ ",username varchar(18),"
					+ "password varchar(124),mobile varchar(10),email varchar(30),type varchar(10),"
					+ "primary key(uniqueid))");
			ResultSet rs=stml.executeQuery("select count(*) from userstb");
			int c=0;
			while(rs.next())
			{
				c=rs.getInt(1);
				txid.setText("ecb/2017-2018/"+(c+1));
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
		txname=new JTextField(20);
		txpass=new JTextField(20);
		txmob=new JTextField(20);
		txemail=new JTextField(20);
		
		cb=new JComboBox();
		cb.addItem("Select");
		cb.addItem("Admin");
		cb.addItem("User");

		btadd=new JButton("add");
		btclear=new JButton("clear");
		btexit=new JButton("exit");
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(6,2));
		p1.add(lbid);
		p1.add(txid);
		p1.add(lbname);
		p1.add(txname);
		p1.add(lbpass);
		p1.add(txpass);
		p1.add(lbmob);
		p1.add(txmob);
		p1.add(lbemail);
		p1.add(txemail);
		p1.add(lbcb);
		p1.add(cb);
		
		
		p2=new JPanel();
		p2.setLayout(new GridLayout(1,3));

		p2.add(btadd);
		p2.add(btclear);
		p2.add(btexit);
		
		p3=new JPanel();
		p3.setBackground(Color.darkGray);
		p3.add(lb,BorderLayout.CENTER);
		
		setLayout(new GridLayout(3,1));
		add(p3);
		add(p1);
		add(p2);
		
		btadd.addActionListener(this);
		btclear.addActionListener(this);
		btexit.addActionListener(this);
		
		
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
		
				PreparedStatement pstmt=con.prepareStatement("insert into userstb(uniqueid,username,password,mobile,email,type)values(?,?,?,?,?,?)");
				pstmt.setString(1,txid.getText());
				pstmt.setString(2,txname.getText());
				pstmt.setString(3,txpass.getText());
				pstmt.setString(4,txmob.getText());
				pstmt.setString(5,txemail.getText());
				pstmt.setString(6,cb.getSelectedItem().toString());
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
		if(src==btclear)
		{

			txid.setText("");
			txname.setText("");
			txpass.setText("");
			txmob.setText("");
			txemail.setText("");
			cb.setSelectedIndex(0);
		}
		if(src==btexit)
		{
			System.exit(0);
		}
		
		
	}

}
