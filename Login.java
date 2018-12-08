import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Login extends JDialog implements ActionListener,ItemListener
{
	JLabel lb,lbname,lbpass,lbcb,lbid;
	JTextField txname,txlog;
	JPasswordField pfpass;
	JComboBox cbid;
	JButton btlogin,btexit,btforgetpass;
	JPanel p1,p2;
	JRadioButton rbt;
	JDialog D;
	public Login()
	{
		int w=this.getToolkit().getScreenSize().width-100;
		int h=this.getToolkit().getScreenSize().height-100;
		setVisible(true);
		setSize(w,h);
		setLocation(50, 50);
		Font F1=new Font("times new roman",Font.BOLD,42);
		Font F2=new Font("monotype corsiva",Font.PLAIN,20);
		
		lb=new JLabel("LOGIN");
		lb.setFont(F1);
		lb.setForeground(Color.red);
		lbname=new JLabel("Username");
		lbname.setFont(F2);
		lbname.setForeground(Color.blue);
		lbpass=new JLabel("Password");
		lbpass.setFont(F2);
		lbpass.setForeground(Color.blue);
		
		//lbforgetpass.setFont(F2);
		//lbforgetpass.setForeground(Color.blue);
		lbcb=new JLabel("Login As");
		lbcb.setFont(F2);
		lbcb.setForeground(Color.blue);
		
		lbid=new JLabel("unique id");
		lbid.setFont(F2);
		lbid.setForeground(Color.blue);
		
		txname=new JTextField(20);
		pfpass=new JPasswordField(20);
	//	pfpass.setEchoChar('*');
		txlog=new JTextField(20);
		txlog.setEditable(false);
		cbid=new JComboBox();
		cbid.addItem("select your id");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stml=con.createStatement();
		//	stml.executeUpdate("create database if not exists studentdb");
			stml.execute("use studentmanagementdb");
			ResultSet rs=stml.executeQuery("select uniqueid from userstb"); 
			while(rs.next())
			{
				cbid.addItem(rs.getString(1));
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
			btlogin=new JButton("Login");
		btexit=new JButton("Exit");
		rbt=new JRadioButton("show Password");
		btforgetpass=new JButton("Forget Password?");
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(7,2));
		p1.add(lbid);
		p1.add(cbid);
		p1.add(lbname);
		p1.add(txname);
		p1.add(lbpass);
		p1.add(pfpass);
		p1.add(new JLabel());
		p1.add(rbt);
		p1.add(lbcb);
		p1.add(txlog);
		p1.add(new JLabel());
		p1.add(btforgetpass);
		p1.add(btlogin);
		p1.add(btexit);
		
		p2=new JPanel();
		p2.setBackground(Color.darkGray);
		p2.setPreferredSize(new Dimension(100,50));
		p2.add(lb,BorderLayout.CENTER);
		
		setLayout(new GridLayout(2,1));
		add(p2);
		add(p1);
		btlogin.addActionListener(this);
		btforgetpass.addActionListener(this);
		rbt.addItemListener(this);
		btexit.addActionListener(this);
		cbid.addItemListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		Object src=ae.getSource();
		if(src==btlogin)
		{
			try
		{
				FrontPage.D.setAlwaysOnTop(false);
				
				Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			
			Statement stml=con.createStatement();
			stml.execute("use studentmanagementdb");
			
			PreparedStatement pstmt=con.prepareStatement("select  username,password from userstb");
			ResultSet rs=pstmt.executeQuery(); 
			
			while(rs.next())
			{
				if(txname.getText().equals(rs.getString(1)) &&  pfpass.getText().equals(rs.getString(2)))
				{
					if(txlog.getText().equals("Admin"))
					{
						if(D!=null)
							D.dispose();
						D=new LoginasAdmin();
					}
					else
						if(txlog.getText().equals("User"))
					{
						if(D!=null)
							D.dispose();
						D=new LoginasUser();
						
					}
						
					break;
					
					
				}
			
		}
			if(!rs.next())
				JOptionPane.showMessageDialog(null,"illegal user");
			
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
		else if(src==btforgetpass)
		{
			if(D!=null)
				D.dispose();
			D=new ForgetPassword();
		}
		else if(src==btexit)
		{
			System.exit(0);
		}
	}
	
	public void itemStateChanged(ItemEvent ae)
	{
		Object src=ae.getSource();
		
		if(rbt.isSelected())
			pfpass.setEchoChar((char)0);
		else
			pfpass.setEchoChar('*');
		

		String id=cbid.getSelectedItem().toString();
		if(ae.getStateChange()==ItemEvent.DESELECTED)return;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stml=con.createStatement();
			stml.executeUpdate("create database if not exists studentmanagementdb");
			stml.execute("use studentmanagementdb");
			PreparedStatement pstmt=con.prepareStatement("select type from userstb where uniqueid=? ");
			
			pstmt.setString(1,id);
			ResultSet rs=pstmt.executeQuery(); 
			while(rs.next())
			{
				String type=rs.getString("type");
				txlog.setText(type);
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
		validate();
	}
		
}


