import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Admindetails extends JPanel
{
	JLabel lbid,lbname,lbpass,lbmob,lbemail,lb; 
	JTextField txid,txname,txpass,txmob,txemail;
	JButton btconfirm,btclear,btexit;
	JPanel p1,p2,p3;
	public Admindetails()
	{
		Font F1=new Font("times new roman",Font.BOLD,42);
		Font F2=new Font("monotype corsiva",Font.ITALIC,18);
		
		lb=new JLabel("ADMIN DETAILS");
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
				
		txid=new JTextField(20);
		txname=new JTextField(20);
		txpass=new JTextField(20);
		txmob=new JTextField(10);
		txemail=new JTextField(20);
		
		btconfirm=new JButton("Confirm");
		btconfirm.setSize(30,50);
		//btconfirm.setPreferredSize(30,50);
		btclear=new JButton("Clear");
		btexit=new JButton("Exit");
		
		p1=new JPanel();
		p1.setLayout(new GridLayout(5,2));
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
		
		p2=new JPanel();
		p2.setForeground(Color.cyan);
		p2.setPreferredSize(new Dimension(10,10));
		p2.setVisible(true);
		p2.setLayout(new GridLayout(1,3,20,20));
		p2.add(btconfirm);
		p2.add(btclear);
		p2.add(btexit);
		
		p3=new JPanel();
		p3.setBackground(Color.darkGray);
		p3.add(lb,BorderLayout.CENTER);
		
		setLayout(new GridLayout(3,1));
		add(p3);
		add(p1);
		add(p2);
		
	}
}
