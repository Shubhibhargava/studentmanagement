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
import javax.swing.JTextField;


public class StudentAcademicDetail extends JDialog implements ItemListener,ActionListener 
{
	JLabel lbstu,lbenrollno,lbyear,lbcourse,lbsem,lbbranch,lbsub,lbtmarks,lbpmarks,lbsub1,lbsub2,lbsub3,lbsub4,lbsub5,lbsub6,lbtotal,lbresult,lbper;
	JComboBox cbenroll,cbyear,cbcourse,cbsem,cbbranch;
	JTextField txsubt1,txsubp1,txsubt2,txsubp2,txsubt3,txsubp3,txsubt4,txsubp4,txsub5,txsub6,txtotal,txresult,txper;
	JPanel p1,p2,p3,p4;
	JButton btsave,btclear,btexit,btcal;
	
	public StudentAcademicDetail()
	{
		Font F1=new Font("times new roman",Font.BOLD,42);
		Font F2=new Font("monotype corsiva",Font.PLAIN,20);
		
		lbstu=new JLabel("Student Academic Details");
		lbstu.setFont(F1);
		lbstu.setForeground(Color.red);
		lbenrollno=new JLabel("Enrollment Number");
		lbenrollno.setFont(F2);
		lbenrollno.setForeground(Color.blue);
		lbyear=new JLabel("Year");
		lbyear.setFont(F2);
		lbyear.setForeground(Color.blue);
		lbcourse=new JLabel("Course");
		lbcourse.setFont(F2);
		lbcourse.setForeground(Color.blue);
		lbsem=new JLabel("Semester");
		lbsem.setFont(F2);
		lbsem.setForeground(Color.blue);
		lbbranch=new JLabel("Branch");
		lbbranch.setFont(F2);
		lbbranch.setForeground(Color.blue);
		lbsub=new JLabel("Subjects");
		lbsub.setFont(F2);
		lbsub.setForeground(Color.blue);
		lbtmarks=new JLabel("Theory Marks");
		lbtmarks.setFont(F2);
		lbtmarks.setForeground(Color.blue);
		lbpmarks=new JLabel("Practical Marks");
		lbpmarks.setFont(F2);
		lbpmarks.setForeground(Color.blue);
		lbsub1=new JLabel("Subject 1");
		lbsub1.setFont(F2);
		lbsub1.setForeground(Color.blue);
		lbsub2=new JLabel("Subject 2");
		lbsub2.setFont(F2);
		lbsub2.setForeground(Color.blue);
		lbsub3=new JLabel("Subject 3");
		lbsub3.setFont(F2);
		lbsub3.setForeground(Color.blue);
		lbsub4=new JLabel("Subject 4");
		lbsub4.setFont(F2);
		lbsub4.setForeground(Color.blue);
		lbsub5=new JLabel("Subject 5");
		lbsub5.setFont(F2);
		lbsub5.setForeground(Color.blue);
		lbsub6=new JLabel("Subject 6");
		lbsub6.setFont(F2);
		lbsub6.setForeground(Color.blue);
		lbtotal=new JLabel("Total Marks");
		lbtotal.setFont(F2);
		lbtotal.setForeground(Color.blue);
		lbresult=new JLabel("Result");
		lbresult.setFont(F2);
		lbresult.setForeground(Color.blue);
		lbper=new JLabel("Percentage");
		lbper.setFont(F2);
		lbper.setForeground(Color.blue);
		
		cbenroll=new JComboBox();
		cbenroll.addItem("Select Enrollment no");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt=con.createStatement();
				stmt.execute("use StudentManagementDB"); //database selection or open
				ResultSet rs=stmt.executeQuery("Select Enrollment_no from StudentPersonalTb");
				while(rs.next())
				{
					cbenroll.addItem(rs.getString(1));
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

		cbyear=new JComboBox();
		cbyear.addItem("Select Year");
		cbyear.addItem("I");
		cbyear.addItem("II");
		cbyear.addItem("III");
		cbyear.addItem("IV");
		
		cbsem=new JComboBox();
		cbsem.addItem("Select  Semester");
		cbsem.addItem("I");
		cbsem.addItem("II");
		cbsem.addItem("III");
		cbsem.addItem("IV");
		cbsem.addItem("V");
		cbsem.addItem("VI");
		cbsem.addItem("VII");
		cbsem.addItem("VIII");
		
		cbcourse=new JComboBox();
		cbcourse.addItem("Select course");
		cbcourse.addItem("Btech");
		cbcourse.addItem("Mtech");
		cbcourse.addItem("MBA");
		cbcourse.addItem("Diploma");
		cbcourse.addItem("BCA");
		cbcourse.addItem("BBA");

		cbbranch=new JComboBox();
		cbbranch.addItem("Select Branch");
		cbbranch.addItem("CSE");
		cbbranch.addItem("IT");
		cbbranch.addItem("ECE");
		cbbranch.addItem("EIC");
		cbbranch.addItem("CE");
		cbbranch.addItem("ME");

		txsubt1=new JTextField(5);
		txsubp1=new JTextField(5);
		txsubt2=new JTextField(5);
		txsubp2=new JTextField(5);
		txsubt3=new JTextField(5);
		txsubp3=new JTextField(5);
		txsubt4=new JTextField(5);
		txsubp4=new JTextField(5);
		txsub5=new JTextField(5);
		txsub6=new JTextField(5);
		txtotal=new JTextField(5);
		txtotal.setEditable(false);
		txresult=new JTextField(5);
		txresult.setEditable(false);
		txper=new JTextField(5);
		txper.setEditable(false);
		
		btsave=new JButton("Save");
		btclear=new JButton("Clear");
		btexit=new JButton("Exit");
		btcal=new JButton("Calculate Result");
				
		p1=new JPanel();
		p1.setBackground(Color.darkGray);
		p1.add(lbstu);
		
		p2=new JPanel();
		p2.setLayout(new GridLayout(5,2));
		p2.add(lbenrollno);
		p2.add(cbenroll);
		p2.add(lbyear);
		p2.add(cbyear);
		p2.add(lbsem);
		p2.add(cbsem);
		p2.add(lbcourse);
		p2.add(cbcourse);
		p2.add(lbbranch);
		p2.add(cbbranch);
		
		p3=new JPanel();
		p3.setVisible(false);
		p3.setLayout(new GridLayout(10,3));
		p3.add(lbsub);
		p3.add(lbtmarks);
		p3.add(lbpmarks);
		p3.add(lbsub1);
		p3.add(txsubt1);
		p3.add(txsubp1);
		p3.add(lbsub2);
		p3.add(txsubt2);
		p3.add(txsubp2);
		p3.add(lbsub3);
		p3.add(txsubt3);
		p3.add(txsubp3);
		p3.add(lbsub4);
		p3.add(txsubt4);
		p3.add(txsubp4);
		p3.add(lbsub5);
		p3.add(txsub5);
		p3.add(new JLabel());
		p3.add(lbsub6);
		p3.add(txsub6);
		p3.add(new JLabel());
		p3.add(lbtotal);
		p3.add(txtotal);
		p3.add(new JLabel());
		p3.add(lbresult);
		p3.add(txresult);
		p3.add(new JLabel());
		p3.add(lbper);
		p3.add(txper);
		p3.add(new JLabel());
		
		p4=new JPanel();
		p4.add(btsave);
		p4.add(btcal);
		p4.add(btclear);
		p4.add(btexit);
		
		
		setLayout(new GridLayout(4,1));
		add(p1);
		add(p2);
		add(p3);
		add(p4);

		btsave.addActionListener(this);
		btcal.addActionListener(this);
		btclear.addActionListener(this);
		btexit.addActionListener(this);
		cbenroll.addItemListener(this);
		cbyear.addItemListener(this);
		cbsem.addItemListener(this);
		cbcourse.addItemListener(this);
		cbbranch.addItemListener(this);
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
					stmt.executeUpdate("create database if not exists StudentManagementDB");
					stmt.execute("use StudentManagementDB"); //database selection or open
					stmt.executeUpdate("create table if not exists StudentAcademicTb(Enrollment_no varchar(20),Year varchar(5),Semester varchar(5),Course varchar(10),Branch varchar(5),Theory_Sub1 varchar(5),Theory_Sub2 varchar(5),Theory_Sub3 varchar(5),Theory_Sub4 varchar(5),Sub5 varchar(5),Sub6 varchar(5),Practical_Sub1 varchar(5),Practical_Sub2 varchar(5),Practical_Sub3 varchar(5),Practical_Sub4 varchar(5),Total varchar(5),Result varchar(5),Percentage varchar(5),primary key(Enrollment_no))");
					PreparedStatement pstmt=con.prepareStatement("Insert into StudentAcademicTb(Enrollment_no ,Year,Semester,Course,Branch,Theory_Sub1,Theory_Sub2,Theory_Sub3,Theory_Sub4,Sub5,Sub6,Practical_Sub1,Practical_Sub2,Practical_Sub3,Practical_Sub4,Total,Result,Percentage)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
					pstmt.setString(1, cbenroll.getSelectedItem().toString());
					pstmt.setString(2, cbyear.getSelectedItem().toString());
					pstmt.setString(3, cbsem.getSelectedItem().toString());
					pstmt.setString(4, cbcourse.getSelectedItem().toString());
					pstmt.setString(5, cbbranch.getSelectedItem().toString());
					pstmt.setString(6, txsubt1.getText());
					pstmt.setString(7, txsubt2.getText());
					pstmt.setString(8, txsubt3.getText());
					pstmt.setString(9, txsubt4.getText());
					pstmt.setString(10, txsub5.getText());
					pstmt.setString(11, txsub6.getText());
					pstmt.setString(12, txsubp1.getText());
					pstmt.setString(13, txsubp2.getText());
					pstmt.setString(14, txsubp3.getText());
					pstmt.setString(15, txsubp4.getText());
					pstmt.setString(16, txtotal.getText());
					pstmt.setString(17, txresult.getText());
					pstmt.setString(18, txper.getText()); 
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
		else if(src==btcal)
		{
		/*	if(txsubt1.getText().equals("") && (txsubt2.getText()).equals("")  && (txsubt3.getText()).equals("") && (txsubt4.getText()).equals("") && (txsub5.getText()).equals("") && (txsub6.getText()).equals("") && (txsubp1.getText()).equals("") &&(txsubp2.getText()).equals("") && (txsubp3.getText()).equals("") && (txsubp4.getText()).equals(""))
			{
				txsubt1.setText("0");  
			txsubt2.setText("0");  
			txsubt3.setText("0");  txsubt4.setText("0");  txsub5.setText("0"); txsub6.setText("0"); txsubp1.setText("0");txsubp2.setText("0"); txsubp3.setText("0"); txsubp4.setText("0");
			}*/
			
				int marks=Integer.parseInt(txsubt1.getText())+Integer.parseInt(txsubt2.getText())+Integer.parseInt(txsubt3.getText())+Integer.parseInt(txsubt4.getText())+Integer.parseInt(txsub5.getText())+Integer.parseInt(txsub6.getText())+Integer.parseInt(txsubp1.getText())+Integer.parseInt(txsubp2.getText())+Integer.parseInt(txsubp3.getText())+Integer.parseInt(txsubp4.getText());
			txtotal.setText(marks+"");
			
			if(Integer.parseInt(txsubt1.getText())>40 && Integer.parseInt(txsubt2.getText())>40  && Integer.parseInt(txsubt3.getText())>40 && Integer.parseInt(txsubt4.getText())>40 && Integer.parseInt(txsub5.getText())>40 && Integer.parseInt(txsub6.getText())>40 && Integer.parseInt(txsubp1.getText())>40 &&Integer.parseInt(txsubp2.getText())>40 && Integer.parseInt(txsubp3.getText())>40 && Integer.parseInt(txsubp4.getText())>40)
				txresult.setText("Pass");
			else
				txresult.setText("Fail");
			
			int per=(marks*100)/1000;
			txper.setText( per+ "%");
			validate();


		}
		else if(src==btclear)
		{
			cbenroll.setSelectedIndex(0);
			cbyear.setSelectedIndex(0);
			cbsem.setSelectedIndex(0);
			cbcourse.setSelectedIndex(0);
			cbbranch.setSelectedIndex(0);
			p3.setVisible(false);
			txsubt1.setText("");  
			txsubt2.setText("");  
			txsubt3.setText(""); 
			txsubt4.setText("");  
			txsub5.setText(""); 
			txsub6.setText("");
			txsubp1.setText("");
			txsubp2.setText(""); 
			txsubp3.setText(""); 
			txsubp4.setText("");
			txtotal.setText("");
			txresult.setText("");
			txper.setText("");

		}
		else
			System.exit(0);
		
	}

	public void itemStateChanged(ItemEvent ie)
	{
	
		if(/*cbenroll.getSelectedIndex()!=0 &&*/ cbyear.getSelectedIndex()!=0 && cbsem.getSelectedIndex()!=0 && cbcourse.getSelectedIndex()!=0 && cbbranch.getSelectedIndex()!=0)
			p3.setVisible(true);
		else
			p3.setVisible(false);
		
			}
}
