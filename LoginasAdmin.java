import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class LoginasAdmin extends JDialog implements ActionListener 
{
	JMenuBar MB;
	JMenu Muser,Mstudent,Mexit,Mstupersonal;
	JMenuItem miall,miaddnew,miallstu,miaddnewstu,mistudetail;
	JDialog D;
	
	public LoginasAdmin()
	{
		int w=this.getToolkit().getScreenSize().width;
		int h=this.getToolkit().getScreenSize().height;
		setVisible(true);
		setSize(w,h);

		
		miall=new JMenuItem("All User");
		miaddnew=new JMenuItem("Add new User");
		miallstu=new JMenuItem("All Students");
		miaddnewstu=new JMenuItem("Add new Student");
		mistudetail=new JMenuItem("Student Detail");
		
		Muser=new JMenu("User Data");
		Mstudent=new JMenu("Student Data");
		Mexit=new JMenu("Exit");
		Mstupersonal=new JMenu("Student Personal Data");
		
		Muser.add(miall);
		Muser.add(miaddnew);
		
		Mstudent.add(Mstupersonal);
		
		Mstupersonal.add(miallstu);
		//Mstupersonal.add(miaddnewstu);
		//Mstupersonal.add(mistudetail);
		
		MB=new JMenuBar();
		MB.add(Muser);
		MB.add(Mstudent);
		MB.add(Mexit);
		
		setJMenuBar(MB);
		
		miall.addActionListener(this);
		miaddnew.addActionListener(this);
		miallstu.addActionListener(this);
		miaddnewstu.addActionListener(this);
		mistudetail.addActionListener(this);
	
	}

	public void actionPerformed(ActionEvent ae)
	{
		Object src=ae.getSource();
		if(D!=null)
			D.dispose();
		if(src==miall)
			D=new UserCardLayout();
		else if(src==miaddnew)
			D=new Addnewuser();
		else if(src==miallstu)
			D=new StudentCardLayout();
		
		 

		
	}
	
}
