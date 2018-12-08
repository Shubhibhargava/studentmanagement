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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdmitCard extends JDialog implements ActionListener,ItemListener
{
	JLabel lbenroll,lbadmit,lbhead;
	JComboBox cbenroll;
	JButton btadmit;
	JPanel p1,p2,p3,p4;
	JTable table;
	JScrollPane jsp;
	public AdmitCard()
	{
		//int w=this.getParent().getSize().width;
		//int h=this.getParent().getSize().height;
	//	setAlwaysOnTop(true);
	//	setModal(true);
		
		int w=this.getToolkit().getScreenSize().width;
		int h=this.getToolkit().getScreenSize().height;
		setVisible(true);
		setSize(w,h);
		//setLocation(200, 100);
		Font F1=new Font("times new roman",Font.BOLD,42);
		Font F2=new Font("monotype corsiva",Font.PLAIN,20);
		
		btadmit=new JButton("get card");
		lbenroll=new JLabel("Enrollment no");
		lbenroll.setFont(F2);
		lbenroll.setForeground(Color.blue);
		
		lbadmit=new JLabel("For card click here");
		lbadmit.setFont(F2);
		lbadmit.setForeground(Color.blue);
		
		lbhead=new JLabel("ADMIT CARD");
		lbhead.setFont(F1);
		lbhead.setForeground(Color.red);
		
		cbenroll=new JComboBox();
		cbenroll.addItem("select enrollment no");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stml=con.createStatement();
		//	stml.executeUpdate("create database if not exists studentdb");
			stml.execute("use studentmanagementdb");
			ResultSet rs=stml.executeQuery("select enrollment_no from studentpersonaltb"); 
			while(rs.next())
			{
				cbenroll.addItem(rs.getString(1));
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
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(2, 2));
		p1.add(lbenroll);
		p1.add(cbenroll);
		p1.add(lbadmit);
		p1.add(btadmit);
		
		p2=new JPanel();
		p2.setBackground(Color.darkGray);
		p2.add(lbhead);
		
		setLayout(new GridLayout(2,1));
		add(p2);
		add(p1);
		
		btadmit.addActionListener(this);
		cbenroll.addItemListener(this);
		
	}
		
	String name,fname,course; 
	

	public void itemStateChanged(ItemEvent ae)
	{
		String roll=cbenroll.getSelectedItem().toString();
		if(ae.getStateChange()==ItemEvent.DESELECTED)return;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stml=con.createStatement();
			stml.executeUpdate("create database if not exists studentmanagementdb");
			stml.execute("use studentmanagementdb");
			PreparedStatement pstmt=con.prepareStatement("select name,father_name,degree from studentpersonaltb where enrollment_no=? ");
			
			pstmt.setString(1,roll);
			ResultSet rs=pstmt.executeQuery(); 
			while(rs.next())
			{
				name=rs.getString("name");
				fname=rs.getString("father_name");
				course=rs.getString("degree");		
				
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
		
	}
	
	

	public void actionPerformed(ActionEvent e)
	{
		Font F2=new Font("monotype corsiva",Font.PLAIN,20);
		Object src=e.getSource();
		String roll=cbenroll.getSelectedItem().toString();
		if(src==btadmit)
		{
			p3=new JPanel();
			p3.setLayout(new GridLayout(4,2));
		
			p3.add(new JLabel("enrollment no:"+roll));
			p3.add(new JLabel("candidate name:"+name));
			p3.add(new JLabel("father's name:"+fname));
			p3.add(new JLabel("course:"+course));
			
			p4=new JPanel();
			Object[]colhead= {"subject code","Theory paper","subject code","Practical paper"};
			Object[][]rowdata=null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stml=con.createStatement();
				
				stml.execute("use studentmanagementdb");
				ResultSet rs=stml.executeQuery("select count(*) from subjectstb");
				int c=0;
				while(rs.next())
				{
					c=rs.getInt(1);
				}
				if(c==0)
				{
					return;
				}
				rowdata=new Object[c][4];
				rs=stml.executeQuery("select * from subjectstb");
				int i=0;
				while(rs.next())
				{
					rowdata[i][0]=rs.getString(2)+"";
					rowdata[i][1]=rs.getString(3)+"";
					rowdata[i][2]=rs.getString(4)+"";
					rowdata[i][3]=rs.getString(5)+"";
					i++;
				}
			table=new JTable(rowdata,colhead);
			
			jsp=new JScrollPane(table);
			
			setLayout(new GridLayout(4, 1));
			add(p3);
			add(jsp);
			validate();
			}
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
}
