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

public class ForgetPassword extends JDialog implements ActionListener
{
	JLabel lb,lbuser,lbmail,lbmob,lbnew;
	JTextField txuser,txmail,txmob,txnew;
	JButton btchange,btexit,btclear;
	JPanel p1,p3,p4;
	
	public ForgetPassword()
	{
		int w=this.getToolkit().getScreenSize().width;
		int h=this.getToolkit().getScreenSize().height;
		setVisible(true);
		setSize(w,h);

		Font f1=new Font("times new roman",Font.BOLD,42);
		Font f2=new Font("monotype corsiva",Font.PLAIN,20);
		
		lb=new JLabel("Forget Password");
		lb.setFont(f1);
		lb.setForeground(Color.red);
		
		lbuser=new JLabel("User name");
		lbuser.setFont(f2);
		lbuser.setForeground(Color.blue);
		lbmail=new JLabel("E-Mail");
		lbmail.setFont(f2);
		lbmail.setForeground(Color.blue);
		lbnew=new JLabel("New Password");
		lbnew.setFont(f2);
		lbnew.setForeground(Color.blue);
		lbmob=new JLabel("Mobile Number");
		lbmob.setFont(f2);
		lbmob.setForeground(Color.blue);
	
		txuser=new JTextField(200);
		txmail=new JTextField(50);
		txnew=new JTextField(50);
		txmob=new JTextField(10);
		
		btchange=new JButton("Change");
		btexit=new JButton("Exit");
		btclear=new JButton("Clear");
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(4,2));
		p1.add(lbuser);
		p1.add(txuser);
		p1.add(lbmob);
		p1.add(txmob);
		p1.add(lbmail);
		p1.add(txmail);
		p1.add(lbnew);
		p1.add(txnew);
		
		p3=new JPanel();
		p3.setLayout(new GridLayout(1,3));
		p3.add(btchange);
		p3.add(btclear);
		p3.add(btexit);
		
		p4=new JPanel();
		p4.setBackground(Color.darkGray);
		p4.add(lb,BorderLayout.CENTER);
		
		setLayout(new GridLayout(3,1));
		add(p4);
		add(p1);
		add(p3);
		
		btchange.addActionListener(this);
		btexit.addActionListener(this);
		
		
	}


	public void actionPerformed(ActionEvent ae)
	{
		Object src=ae.getSource();
		if(src==btchange)
		{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stml=con.createStatement();
			stml.execute("use studentmanagementdb");
			PreparedStatement pstmt=con.prepareStatement("select count(*) from userstb where username=? and email=? and mobile=? ");
			pstmt.setString(1,txuser.getText());
			pstmt.setString(2,txmail.getText());
			pstmt.setString(3,txmob.getText());
			ResultSet rs=pstmt.executeQuery();
			int c=0;
			
			while(rs.next())
			{
				c=rs.getInt(1);
			}
				if(c==0)
				JOptionPane.showMessageDialog(null, "Invalid details");
				else
				{
				PreparedStatement pstmt1=con.prepareStatement("update userstb set password=? where email=? and mobile=?");
				pstmt1.setString(1,txnew.getText());
				pstmt1.setString(2,txmail.getText());
				pstmt1.setString(3, txmob.getText());
				pstmt1.executeUpdate();
				JOptionPane.showMessageDialog(null, "Password Changed");
				}
				//else
					//JOptionPane.showMessageDialog(null, "Invalid details");
			
									
				
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
		else if(src==btclear)
		{
			txuser.setText("");
			txmob.setText("");
			txmail.setText("");
			txnew.setText("");
		}
		else
			System.exit(0);
	}
	

}
