import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Edituserdetails extends JPanel implements ActionListener,ItemListener
{
	JLabel lb,lbid,lbname,lbpass,lbmob,lbemail;
	JTextField txname,txpass,txmob,txemail;
	JButton btsave,btdelete,btclear,btexit;
	JPanel p1,p2,p3;
	JTable table;
	JScrollPane jsp;
	JComboBox cb;
	
	public Edituserdetails()
	{
		Font F1=new Font("times new roman",Font.BOLD,42);
		Font F2=new Font("monotype corsiva",Font.PLAIN,20);
		
		lb=new JLabel("EDIT USER DETAILS");
		//lb.setBackground(Color.green);
		lb.setFont(F1);
		lb.setForeground(Color.red);
		lbid=new JLabel("Unique ID");
		lbid.setFont(F2);
		lbid.setForeground(Color.blue);
		lbname=new JLabel("Username");
		lbname.setFont(F2);
		lbname.setForeground(Color.blue);
		lbpass=new JLabel("Password");
		lbpass.setFont(F2);
		lbpass.setForeground(Color.blue);
		lbmob=new JLabel("Mobile Number");
		lbmob.setFont(F2);
		lbmob.setForeground(Color.blue);
		lbemail=new JLabel("E-Mail ID");
		lbemail.setFont(F2);
		lbemail.setForeground(Color.blue);

		
		txname=new JTextField(20);
		txpass=new JTextField(20);
		txmob=new JTextField(10);
		txemail=new JTextField(20);
		
		btsave=new JButton("Save");
		btclear=new JButton("Clear");
		btexit=new JButton("Exit");
		btdelete=new JButton("Delete");
		
		cb=new JComboBox();
		cb.addItem("Select Unique ID");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt=con.createStatement();
				stmt.execute("use StudentManagementDB"); //database selection or open
				PreparedStatement pstmt1=con.prepareStatement("Select uniqueid from usersTb where type=?");
				pstmt1.setString(1, "User");
				ResultSet rs=pstmt1.executeQuery();
				while(rs.next())
				{
					cb.addItem(rs.getString(1));
				}
				
				con.close();
			}
			
			catch (SQLException e)
			{ 
				e.printStackTrace();
			}
		} 
		
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		}

		p1=new JPanel();
		p1.setLayout(new GridLayout(5,2));
		p1.add(lbid);
		p1.add(cb);
		p1.add(lbname);
		p1.add(txname);
		p1.add(lbpass);
		p1.add(txpass);
		p1.add(lbmob);
		p1.add(txmob);
		p1.add(lbemail);
		p1.add(txemail);
		
		p2=new JPanel();
		p2.setLayout(new GridLayout(1,3));
		p2.add(btsave);
		//p2.add(btdelete);
		p2.add(btclear);
		p2.add(btexit);
		
		p3=new JPanel();
		p3.setBackground(Color.darkGray);
		p3.add(lb,BorderLayout.CENTER);
		
		setLayout(new GridLayout(3,1));
		add(p3);
		add(p1);
		add(p2);

		btsave.addActionListener(this);
		btdelete.addActionListener(this);
		btclear.addActionListener(this);
		btexit.addActionListener(this);
		cb.addItemListener(this);
	}

	public void actionPerformed(ActionEvent ae) 
	{
		Object src=ae.getSource();
		if(src==btsave)
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				try
				{
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt=con.createStatement();
					stmt.execute("use StudentManagementDB"); //database selection or open
					
					PreparedStatement pstmt=con.prepareStatement("Update usersTb set Username=?,Password=?,Mobile=?,email=? where uniqueid=?");
						pstmt.setString(1, txname.getText());
						pstmt.setString(2, txpass.getText());
						pstmt.setString(3, txmob.getText());
						pstmt.setString(4,txemail.getText());
						pstmt.setString(5, cb.getSelectedItem().toString());
						
						pstmt.executeUpdate();
					
					con.close();
				}
				catch (SQLException e)
				{ 
					e.printStackTrace();
				}

			}
			catch (ClassNotFoundException e) 
			{
				
				e.printStackTrace();
			}
		validate();

		}
		else if(src==btdelete)
		{
			int uniqueid=Integer.parseInt(cb.getSelectedItem().toString());
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				try
				{
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt=con.createStatement();
					stmt.execute("use StudentManagementDB"); //database selection or open
					
					PreparedStatement pstmt=con.prepareStatement("delete from UsersTb where uniqueid=?");
					pstmt.setInt(1, uniqueid);
					
					pstmt.executeUpdate();
					con.close();
				}
				catch (SQLException e)
				{ 
					e.printStackTrace();
				}

			}
			catch (ClassNotFoundException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if(src==btclear)
		{
			cb.setSelectedIndex(0);
			txname.setText("");
			txpass.setText("");
			txmob.setText("");
			txemail.setText("");
			
		}
		else
			System.exit(0);
		
	}

	public void itemStateChanged(ItemEvent ie) 
	{
		if(ie.getStateChange()==ItemEvent.DESELECTED) return;
		String uniqueid=cb.getSelectedItem().toString();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt=con.createStatement();
				stmt.execute("Use StudentManagementDB");
				
				PreparedStatement pstmt1=con.prepareStatement("Select count(*) from usersTb where uniqueid=? ");
				pstmt1.setString(1, cb.getSelectedItem().toString());
				ResultSet rs=pstmt1.executeQuery();
				int c=0;
				while(rs.next())
				{
					c=rs.getInt(1);
				}
				
				PreparedStatement pstmt=con.prepareStatement("Select * from usersTb where uniqueid=? ");
				pstmt.setString(1, uniqueid);
				 rs=pstmt.executeQuery();
				
				
				while(rs.next())
				{
					txname.setText(rs.getString(2)+ "");
					txpass.setText(rs.getString(3));
					txmob.setText(rs.getInt(4)+"");
					txemail.setText(rs.getString(5));
					
				}
								con.close();
			}
			catch (SQLException e)
			{
				
				e.printStackTrace();
			}
			

		}
		catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		}

	validate();
		

		
	}

}
