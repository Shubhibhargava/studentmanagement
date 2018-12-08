import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class StudentCardLayout extends JDialog implements ActionListener,ItemListener
{
JButton btedit,btdelete,btview,btclose;
JPanel pn,pmain;
CardLayout CLO;
public StudentCardLayout()
{
	int w=this.getToolkit().getScreenSize().width;
	int h=this.getToolkit().getScreenSize().height;
	setVisible(true);
	setSize(w,h);

	btedit=new JButton("Edit");
	btdelete=new JButton("Delete");
	btview=new JButton("View");
	btclose=new JButton("Close");

		
	pn=new JPanel();
	pn.setLayout(new GridLayout(1,4));
	pn.add(btview);
	pn.add(btedit);
	pn.add(btdelete);
	pn.add(btclose);
	
	pmain=new JPanel();
	CLO=new CardLayout();
	pmain.setLayout(CLO);
	pmain.add("View", new ViewallStudents());
	pmain.add("Edit", new Editstudentdetail());
	pmain.add("Delete", new DeleteStudent());
	//pmain.add("Close", new ClosePanel());
	
	setLayout(new BorderLayout());
	add(pn,BorderLayout.NORTH);
	add(pmain,BorderLayout.CENTER);
	
	btedit.addActionListener(this);
	btview.addActionListener(this);
	btdelete.addActionListener(this);
	btclose.addActionListener(this);
}

public void itemStateChanged(ItemEvent e) {
	
	
}

public void actionPerformed(ActionEvent ae) 
{
	JButton src=(JButton) ae.getSource();
	if(src==btclose)
	{
		int ans=JOptionPane.showConfirmDialog(null, "U want to Exit...");
		if(ans==JOptionPane.YES_OPTION)
				System.exit(0);
	
	}
	CLO.show(pmain,src.getLabel());
		
}
}
