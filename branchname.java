import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class branchname extends JApplet implements ActionListener
{
	JLabel lbbranch;
	JTextField txbranch;
	JButton btinsert,btclose;
	
	
	public void init()
	{
	
		
		lbbranch=new JLabel("branch");
		txbranch=new JTextField(50);
		btinsert= new JButton("insert");
		btclose=new JButton("close");
	setLayout(new GridLayout(2, 2));
	add(lbbranch);
	add(txbranch);
	add(btinsert);
	add(btclose);
	
	btinsert.addActionListener(this);
	btclose.addActionListener(this);
	
	}


	public void actionPerformed(ActionEvent ae)
	{
			Object src=ae.getSource();
			
			if(src==btinsert)
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stml=con.createStatement();
					stml.executeUpdate("create database if not exists studentmanagementdb");
					stml.execute("use studentmanagementdb");
					stml.executeUpdate("create table if not exists branchtb(branch_name varchar(20))");
					PreparedStatement pstmt=con.prepareStatement("insert into branchtb(branch_name)values(?)");
					pstmt.setString(1,txbranch.getText());
					
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
	

}
