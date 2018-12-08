import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StudentPersonalDetails extends JDialog implements ActionListener,ItemListener
{
	JLabel lb,lbname,lbfname,lbmname,lbffees,lbdfees,lbtfees,lbaddress,lbdegree,lbgender,lbdob,lbfacility,lbenrol,lbtellgender;
	JTextField txcname,txfname,txmname,txffees,txdfees,txtfees,txaddress,txenrol;
	JCheckBox male,female,mess,hostel,bus,rd;
	ButtonGroup gr;
	JButton btsubmit,btclear,btexit;
	JComboBox mm,dd,yy,degree;
	JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9;

	public StudentPersonalDetails()
	{
		int w=this.getToolkit().getScreenSize().width;
		int h=this.getToolkit().getScreenSize().height;
		setVisible(true);
		setSize(w,h);

		Font F1=new Font("times new roman",Font.BOLD,42);
		Font F2=new Font("monotype corsiva",Font.PLAIN,20);
		
		lb=new JLabel("STUDENT PERSONAL DETAILS");
		lb.setFont(F1);
		lb.setForeground(Color.red);
		lbname=new JLabel("Student Name");
		lbname.setFont(F2);
		lbname.setForeground(Color.blue);
		lbenrol=new JLabel("Enrollment Number");
		lbenrol.setFont(F2);
		lbenrol.setForeground(Color.blue);
		lbfname=new JLabel("Father's Name");
		lbfname.setFont(F2);
		lbfname.setForeground(Color.blue);
		lbmname=new JLabel("Mother's Name");
		lbmname.setFont(F2);
		lbmname.setForeground(Color.blue);
		lbffees=new JLabel("Facilities Fees");
		lbffees.setFont(F2);
		lbffees.setForeground(Color.blue);
		lbdfees=new JLabel("Degree Fees");
		lbdfees.setFont(F2);
		lbdfees.setForeground(Color.blue);
		lbtfees=new JLabel("Total Fees");
		lbtfees.setFont(F2);
		lbtfees.setForeground(Color.blue);
		lbaddress=new JLabel("Address");
		lbaddress.setFont(F2);
		lbaddress.setForeground(Color.blue);
		lbdegree=new JLabel("Degree");
		lbdegree.setFont(F2);
		lbdegree.setForeground(Color.blue);
		lbgender=new JLabel("Gender");
		lbgender.setFont(F2);
		lbgender.setForeground(Color.blue);
		lbdob=new JLabel("D.O.B");
		lbdob.setFont(F2);
		lbdob.setForeground(Color.blue);
		lbfacility=new JLabel("Facilities");
		lbfacility.setFont(F2);
		lbfacility.setForeground(Color.blue);
		lbtellgender=new JLabel();

		txcname=new JTextField(15);
		txenrol=new JTextField(10);
		txenrol.setEditable(false);
		txfname=new JTextField(15);
		txmname=new JTextField(15);
		txdfees=new JTextField(7);
		txdfees.setEditable(false);
		txffees=new JTextField(7);
		txffees.setEditable(false);
		txtfees=new JTextField(10);
		txtfees.setEditable(false);
		txaddress=new JTextField();
		
		mess=new JCheckBox("Mess");
		hostel=new JCheckBox("Hostel");
		bus=new JCheckBox("Bus");

		gr=new ButtonGroup();
		gr.add(male=new JCheckBox("Male",false));
		gr.add(female=new JCheckBox("Female",false));
		gr.add(rd=new JCheckBox("",false));
		
		dd=new JComboBox();
		dd.addItem("date");
		for(int i=1;i<=31;i++)
			dd.addItem("" + i);
		
		mm=new JComboBox();
		mm.addItem("month");
		for(int i=1;i<=31;i++)
			mm.addItem("" + i);
		
		yy=new JComboBox();
		yy.addItem("yy");
		for(int i=1993;i<=2000;i++)
			yy.addItem("" + i);
		
		degree=new JComboBox();
		degree.addItem("Select degree");
		degree.addItem("Btech");
		degree.addItem("Mtech");
		degree.addItem("MBA");
		degree.addItem("Diploma");
		degree.addItem("BCA");
		degree.addItem("BBA");
		
		btsubmit=new JButton("Submit");
		btclear=new JButton("Clear");
		btexit=new JButton("Exit");
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(4,2));
		p1.add(lbname);
		p1.add(txcname);
		p1.add(lbenrol);
		p1.add(txenrol);
		p1.add(lbfname);
		p1.add(txfname);
		p1.add(lbmname);
		p1.add(txmname);
		
		p2=new JPanel();
		p2.setLayout(new GridLayout(1,3));
		p2.add(lbgender);
		p2.add(male);
		p2.add(female);	
		
		p3=new JPanel();
		p3.setLayout(new GridLayout(1,4));
		p3.add(lbdob);
		p3.add(mm);
		p3.add(dd);
		p3.add(yy);

		p4=new JPanel();
		p4.setLayout(new GridLayout(3,2));
		p4.add(lbaddress);
		p4.add(txaddress);
		p4.add(lbdegree);
		p4.add(degree);
		p4.add(lbdfees);
		p4.add(txdfees);
		
		p5=new JPanel();
		p5.setLayout(new GridLayout(1,4));
		p5.add(lbfacility);
		p5.add(mess);
		p5.add(hostel);
		p5.add(bus);
		
		p6=new JPanel();
		p6.setLayout(new GridLayout(1,2));
		p6.add(lbffees);
		p6.add(txffees);
	//	p6.add(lbtfees);
	//	p6.add(txtfees);
		
		p7=new JPanel();
		p7.setLayout(new GridLayout(1,4));
		p7.add(btsubmit);
		p7.add(btclear);
		p7.add(btexit);
		
		p8=new JPanel();
		p8.setBackground(Color.darkGray);
		p8.add(lb,BorderLayout.CENTER);

		setLayout(new GridLayout(8,1));
		add(p8);
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		add(p7);
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt=con.createStatement();
				stmt.executeUpdate("Create Database if not exists StudentManagementDb");
				stmt.execute("use StudentManagementDb"); //database selection or open
				stmt.executeUpdate("create table if not exists StudentPersonalTb(name varchar(200),Enrollment_no varchar(50),father_name varchar(200),mother_name varchar(200),Gender varchar(10),dob date,address varchar(255),degree varchar(200),degree_fees int,facility varchar(200),fac_fee int,primary key(Enrollment_no))");
				ResultSet rs=stmt.executeQuery("Select count(*) from StudentPersonalTb");
				int c=0;
				while(rs.next())
				{
					c=rs.getInt(1);
				}
				
				txenrol.setText("ECB/2017-2018/"+(c+1));
				
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

		if(male.isSelected())
			lbtellgender.setText("Female");
		else 
			lbtellgender.setText("Male");
	
		
		//male.addItemListener(this);
		//female.addItemListener(this);
		mess.addItemListener(this);
		bus.addItemListener(this);
		hostel.addItemListener(this);
		degree.addItemListener(this);
		btsubmit.addActionListener(this);
		btclear.addActionListener(this);
		btexit.addActionListener(this);
}
	String msg="";
	public void actionPerformed(ActionEvent ae) 
	{
		Object src=ae.getSource();
		if(src==btsubmit)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				try
				{
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt=con.createStatement();
					stmt.executeUpdate("create database if not exists StudentManagementDB");
					stmt.execute("use StudentManagementDB"); //database selection or open
					stmt.executeUpdate("create table if not exists StudentPersonalTb(name varchar(200),Enrollment_no varchar(50),father_name varchar(200),mother_name varchar(200),Gender varchar(10),dob date,address varchar(255),degree varchar(200),degree_fees int,facility varchar(200),fac_fee int,primary key(Enrollment_no))");
					PreparedStatement pstmt=con.prepareStatement("Insert into StudentPersonalTb(name ,Enrollment_no ,father_name ,mother_name ,Gender ,dob ,address ,degree ,degree_fees ,facility,fac_fee )values(?,?,?,?,?,?,?,?,?,?,?)");
					pstmt.setString(1, txcname.getText());
					pstmt.setString(2, txenrol.getText());
					pstmt.setString(3,txfname.getText());
					pstmt.setString(4, txmname.getText());
					pstmt.setString(5, lbtellgender.getText());
					java.sql.Date dt=new Date(Integer.parseInt(yy.getSelectedItem().toString()),mm.getSelectedIndex() ,dd.getSelectedIndex() );
					pstmt.setDate(6,dt);
					pstmt.setString(7, txaddress.getText());
					pstmt.setString(8, degree.getSelectedItem().toString());
					pstmt.setString(9, txdfees.getText());
					pstmt.setString(10, msg);
					pstmt.setString(11, txffees.getText());
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

		}
		else if(src==btclear)
		{
			txcname.setText("");
			txenrol.setText("");
			txfname.setText("");
			txmname.setText("");
			txdfees.setText("");
			txffees.setText("");
			txtfees.setText("");
			txaddress.setText("");
			degree.setSelectedIndex(0);
			mm.setSelectedIndex(0);
			dd.setSelectedIndex(0);
			yy.setSelectedIndex(0);
			rd.setSelected(true);
			mess.setSelected(false);
			hostel.setSelected(false);
			bus.setSelected(false);
			validate();
	}
		else 
			System.exit(0);
		}

	public void itemStateChanged(ItemEvent ie) 
	{
		if(ie.getStateChange()==ItemEvent.DESELECTED)
			return;
		Object src=ie.getSource();
			
	  if(src==degree && degree.getSelectedIndex() !=0 && ie.getStateChange()==ItemEvent.SELECTED)
			{
				switch(degree.getSelectedIndex())
				{
				case 1:
				txdfees.setText("70000");
				break;
				case 2:
				txdfees.setText("80000");
				break;
				case 3:
				txdfees.setText("100000");
				break;
				case 4:
				txdfees.setText("11000");
				break;
				case 5:
				txdfees.setText("9000");
				break;
				case 6:
				txdfees.setText("7000");
				break;
				default :
					txdfees.setText("0");
				}
				}
				else
					if(src==mess || src==hostel|| src==bus)
				{
				int fee=0;
				if(mess.isSelected())
				{
					fee+=20000;
					msg+="mess";
				}
					if(hostel.isSelected())
					{	fee+=15000;
					msg+="hostel";
					}
				if(bus.isSelected())
				{
					fee+=10000;
					msg+="bus";
				}
					txffees.setText(fee + "");
					}
	  
/*		if(txffees!=null && txdfees!=null)
			txtfees.setText((Integer.parseInt(txdfees.getText())+Integer.parseInt(txffees.getText()))+"");

		else if(txffees.getText().equals(""))
			txffees.setText("0"); */

		validate();			
	}
								
			}
		
	
