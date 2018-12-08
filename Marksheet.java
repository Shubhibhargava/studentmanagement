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

public class Marksheet extends JDialog implements ActionListener,ItemListener
{
	JLabel lbenroll,lbadmit,lbhead,lbtotal,lbper;
	JComboBox cbenroll;
	JButton btadmit;
	JPanel p1,p2,p3,p4,p5;
	JTable table;
	JScrollPane jsp;
	public Marksheet()
	{
		int w=this.getToolkit().getScreenSize().width;
		int h=this.getToolkit().getScreenSize().height;

		setVisible(true);
		setSize(w, h);
		
		Font F1=new Font("times new roman",Font.BOLD,42);
		Font F2=new Font("monotype corsiva",Font.PLAIN,20);
		
		btadmit=new JButton("get marksheet");
		lbenroll=new JLabel("Enrollment no");
		lbenroll.setFont(F2);
		lbenroll.setForeground(Color.blue);
		
		lbadmit=new JLabel("For marksheet click here");
		lbadmit.setFont(F2);
		lbadmit.setForeground(Color.blue);
		
		lbhead=new JLabel("MARKSHEET");
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
			ResultSet rs=stml.executeQuery("select enrollment_no from studentacademictb"); 
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
		
	String name,fname,course,result;
	int total,per;
	

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
			if(p3!=null && p4!=null && p5!=null)
			{
			remove(p3);
			remove(p4);
			remove(p5);
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
			Object[]colhead= {"S.no.","Subject Name","Marks"};
			Object[][]rowdata=null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stml=con.createStatement();
				
				stml.execute("use studentmanagementdb");
				
				rowdata=new Object[10][3];
				PreparedStatement pstmt=con.prepareStatement("select * from StudentAcademictb where enrollment_no=?");
				pstmt.setString(1,roll);
				ResultSet rs=pstmt.executeQuery(); 
				
				while(rs.next())
				{
					rowdata[0][2]=rs.getString(6)+"";
					rowdata[1][2]=rs.getString(7)+"";
					rowdata[2][2]=rs.getString(8)+"";
					rowdata[3][2]=rs.getString(9)+"";
					rowdata[4][2]=rs.getString(10)+"";
					rowdata[5][2]=rs.getString(11)+"";
					rowdata[6][2]=rs.getString(12)+"";
					rowdata[7][2]=rs.getString(13)+"";
					rowdata[8][2]=rs.getString(14)+"";
					rowdata[9][2]=rs.getString(15)+"";
					
				}
				rowdata[0][1]="Subject 1";
				rowdata[1][1]="Subject 2";
				rowdata[2][1]="Subject 3";
				rowdata[3][1]="Subject 4";
				rowdata[4][1]="Subject 5";
				rowdata[5][1]="Subject 6";
				rowdata[6][1]="Practical Subject 1";
				rowdata[7][1]="Practical Subject 2";
				rowdata[8][1]="Practical Subject 3";
				rowdata[9][1]="Practical Subject 4";
				
				rowdata[0][0]="1";
				rowdata[1][0]="2";
				rowdata[2][0]="3";
				rowdata[3][0]="4";
				rowdata[4][0]="5";
				rowdata[5][0]="6";
				rowdata[6][0]="7";
				rowdata[7][0]="8";
				rowdata[8][0]="9";
				rowdata[9][0]="10";
				
				
				
				
				if(jsp!=null)
				{
					jsp.remove(table);
					remove(jsp);
					validate();
				}
					table=new JTable(rowdata,colhead);
					jsp=new JScrollPane(table);
			
				
				con.close();
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
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stml=con.createStatement();
				stml.executeUpdate("create database if not exists studentmanagementdb");
				stml.execute("use studentmanagementdb");
				PreparedStatement pstmt=con.prepareStatement("select total,percentage,result from studentacademictb where enrollment_no=? ");
				
				pstmt.setString(1,roll);
				ResultSet rs=pstmt.executeQuery(); 
				while(rs.next())
				{
					total=rs.getInt("total");
					per=rs.getInt("percentage");
					result=rs.getString("result");		
					
				}
				con.close();
			} 
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			
			//p4.add(jsp);
			p5=new JPanel();
			lbtotal=new JLabel("total"+":"+total);
			lbper=new JLabel("percentage"+":"+per);
			p5.setLayout(new GridLayout(1, 3));
			p5.add(lbtotal);
			p5.add(lbper);
			p5.add(new JLabel("result:"+result));
			
			
			setLayout(new GridLayout(5, 1));
			add(p3);
			add(jsp);
			add(p5);
			validate();
		}
	}



}
