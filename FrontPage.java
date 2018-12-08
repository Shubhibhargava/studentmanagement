import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class FrontPage extends JApplet implements ActionListener,MenuListener
{
	JMenuBar MB;
	JMenu Mauthority,Muser,Mstudent,Mexit;
	JMenuItem mimrksht,miadmit,mistudentpersonal;
	JPanel P;
	public static JDialog D;
	//static int w,h;
	
	public void init()
	{
		
		//w=this.getSize().width;
		//h=this.getSize().height;

		
		mimrksht=new JMenuItem("MarkSheet");
		miadmit=new JMenuItem("Admit Card");
		mistudentpersonal=new JMenuItem("Register");
		
		Mauthority=new JMenu("Authority Cell");
		Muser=new JMenu("User Cell");
		Mstudent=new JMenu("Student Cell");
		Mexit=new JMenu("Exit");
		
		Mstudent.add(mimrksht);
		Mstudent.add(miadmit);
		Mstudent.add(mistudentpersonal);
		
		MB=new JMenuBar();
		MB.add(Mauthority);
		MB.add(Muser);
		MB.add(Mstudent);
		MB.add(Mexit);
		
		setJMenuBar(MB);
		miadmit.addActionListener(this);
		mimrksht.addActionListener(this);
		mistudentpersonal.addActionListener(this);
		Mauthority.addMenuListener(this);
		Muser.addMenuListener(this);
		
		//P=new JPanel();
		//add(P);
		
	}

	public void actionPerformed(ActionEvent ae)
	{
		Object src=ae.getSource();
		if(D!=null)
			D.dispose();
		if(src==miadmit)
			D=new AdmitCard();
		else if(src==mimrksht)
			D=new Marksheet();
		else if(src==mistudentpersonal)
			D=new StudentPersonalDetails();
		//D.setModal(true);
		/*remove(P);
		if(src==mimrksht)
			P=new Marksheet();
		else
			P=new AdmitCard();
		add(P);
		validate();*/
		
		
	}

	public void menuSelected(MenuEvent e)
	{
		
		Object src=e.getSource();
		
		if(D!=null)
			D.dispose();
		if(src==Mauthority)
		{
			D=new Login();
			
		}
			else
				if(src==Muser)
				{
					D=new Login();
				}
		//D.setFocusable(true);
		//D.setAutoRequestFocus(true);
		//D.setModal(true);
		D.setAlwaysOnTop(true);
	}
	

	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*public void paint(Graphics g)
	{
		w=this.getSize().width;
		h=this.getSize().height;
	}*/
		
}
