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


public class LoginasUser extends JDialog implements ActionListener 
{
	JMenuBar MB;
	JMenu muser,mstu,mexit,mstudata;
	JMenuItem miedituser,mistuacademic;
	JDialog D;
	
	public LoginasUser()
	{
		
		int w=this.getToolkit().getScreenSize().width;
		int h=this.getToolkit().getScreenSize().height;
		setVisible(true);
		setSize(w,h);
		
		
		miedituser=new JMenuItem("Edit User Details");
		mistuacademic=new JMenuItem("Student Academic Details");
		
		muser=new JMenu("User Data");
		mstu=new JMenu("Student Data");
		mstudata=new JMenu("Student Academic Data");
		mexit=new JMenu("Exit");
		
		muser.add(miedituser);
		
		mstu.add(mstudata);
		
		mstudata.add(mistuacademic);
		
		MB=new JMenuBar();
		MB.add(muser);
		MB.add(mstu);
		MB.add(mexit);
		
		setJMenuBar(MB);
		
		miedituser.addActionListener(this);
		mistuacademic.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		Object src=ae.getSource();
		if(D!=null)
			D.dispose();
		if(src==miedituser)
			D=new Edituserdetailsdialog();
		else if(src==mistuacademic)
			D=new StudentAcademicDetail();

		
	}

	
}
