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

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteStudent extends JPanel implements ActionListener,ItemListener
{
	
		JLabel lb,lbname,lbfname,lbmname,lbffees,lbdfees,lbtfees,lbaddress,lbdegree,lbgender,lbdob,lbfacility,lbenrol,lbtellgender;
		 
		JTextField txcname ,txfname,txmname,txffees,txdfees,txtfees,txaddress;
		JCheckBox male,female,mess,hostel,bus,rd;
		ButtonGroup gr;
		JButton btsubmit,btdelete,btclear,btexit;
		JComboBox mm,dd,yy,degree,cbenroll;
		JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9;

		public DeleteStudent()
		{
			Font F1=new Font("times new roman",Font.BOLD,42);
			Font F2=new Font("monotype corsiva",Font.PLAIN,20);
			
			lb=new JLabel("DELETE STUDENT");
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

			txcname=new JTextField(15);
			txfname=new JTextField(15);
			txmname=new JTextField(15);
			txdfees=new JTextField(7);
			txdfees.setEditable(false);
			txffees=new JTextField(7);
			txffees.setEditable(false);
			txtfees=new JTextField(10);
			txtfees.setEditable(false);
			txaddress=new JTextField();
			
			cbenroll=new JComboBox();
			cbenroll.addItem("select");
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stml=con.createStatement();
				stml.executeUpdate("create database if not exists studentmanagementdb");
				stml.execute("use studentmanagementdb");
				ResultSet rs=stml.executeQuery("select Enrollment_no from studentpersonaltb"); 
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
			
			mess=new JCheckBox("Mess");
			hostel=new JCheckBox("Hostel");
			bus=new JCheckBox("Bus");

			gr=new ButtonGroup();
			gr.add(male=new JCheckBox("Male",false));
			gr.add(female=new JCheckBox("Female",false));
			gr.add(rd=new JCheckBox("",false));
			
			mm=new JComboBox();
			mm.addItem("month");
			mm.addItem("jan");
			mm.addItem("feb");
			mm.addItem("march");
			mm.addItem("april");
			mm.addItem("may");
			mm.addItem("june");
			mm.addItem("july");
			mm.addItem("aug");
			mm.addItem("sept");
			mm.addItem("oct");
			mm.addItem("nov");
			mm.addItem("dec");
			
			dd=new JComboBox();
			dd.addItem("dd");
			for(int i=1;i<=31;i++)
				dd.addItem("" + i);
			
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
			
			btsubmit=new JButton("update");
			btclear=new JButton("clear");
			btexit=new JButton("Exit");
			btdelete=new JButton("Delete");
			
			p1=new JPanel();
			p1.setLayout(new GridLayout(4,2));
			p1.add(lbenrol);
			p1.add(cbenroll);
			p1.add(lbname);
			p1.add(txcname);
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
			p6.setLayout(new GridLayout(2,2));
			p6.add(lbffees);
			p6.add(txffees);
			p6.add(lbtfees);
			p6.add(txtfees);
			
			p7=new JPanel();
			p7.setLayout(new GridLayout(1,3));
			//p7.add(btsubmit);
			p7.add(btdelete);
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
			 btsubmit.addActionListener(this);
			 btdelete.addActionListener(this);
			 btclear.addActionListener(this);
			 btexit.addActionListener(this);
			 cbenroll.addItemListener(this);
		}


		public void actionPerformed(ActionEvent ae)
		{
			String roll=cbenroll.getSelectedItem().toString();
			Object src=ae.getSource();
			
			if(src==btsubmit)
			{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stml=con.createStatement();
				
				stml.execute("use studentmanagementdb");
				
				PreparedStatement pstmt=con.prepareStatement("update studentpersonaltb set name=?"
						+ ",father_name=?"
						+ ",mother_name=?,Gender=?,"
						+ "address=?,degree=?"
						+ ",degree_fees=?,"
						+ "fac_fee=? where rollno=?");
				pstmt.setString(1,txcname.getText());
				pstmt.setString(2,txfname.getText());
				pstmt.setString(3,txmname.getText());
				pstmt.setString(4,lbtellgender.getText());
				pstmt.setString(5,txaddress.getText());
				pstmt.setString(6,degree.getSelectedItem().toString());
				pstmt.setString(7,txdfees.getText());
				pstmt.setString(8,txffees.getText());
				pstmt.setString(9,txtfees.getText());
				pstmt.setString(10,cbenroll.getSelectedItem().toString());
				
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
			else if(src==btdelete)
			{
					
					try {
						Class.forName("com.mysql.jdbc.Driver");
						
						try
						{
							Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
							Statement stmt=con.createStatement();
							stmt.execute("use StudentManagementDB"); //database selection or open
							
							PreparedStatement pstmt=con.prepareStatement("delete from StudentPersonalTb where enrollment_no=?");
							pstmt.setString(1, roll);
							
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
				txcname.setText("");
				cbenroll.setSelectedIndex(0);
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
			else if(src==btexit)
			{
				System.exit(0);
			}

			}
		


		public void itemStateChanged(ItemEvent ae)
		{

			String roll=cbenroll.getSelectedItem().toString();
			Object src=ae.getSource();
			
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stml=con.createStatement();
				
				stml.execute("use studentmanagementdb");
				PreparedStatement pstmt=con.prepareStatement("select * from studentpersonaltb where enrollment_no=? ");
				pstmt.setString(1,roll);
				ResultSet rs=pstmt.executeQuery(); 
			
				while(rs.next())
				{

					String name=rs.getString(1);
					String enroll =rs.getString(2);
					String father=rs.getString(3);
					String mother=rs.getString(4);
					String gender=rs.getString(5);
					String dob=rs.getString(6);
					String address=rs.getString(7);
					String degree=rs.getString(8);
					String degree_fees=rs.getString(9);
					String facility=rs.getString(10);
					String fac_fee=rs.getString(11);
					txcname.setText(name);
					txfname.setText(father);
					txmname.setText(mother);
					txaddress.setText(address);
					txdfees.setText(degree_fees);
					txffees.setText(fac_fee);
					
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


			
		}	
	

