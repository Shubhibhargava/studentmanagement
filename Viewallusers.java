

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Viewallusers extends JPanel 
{

	JTable table;
	JScrollPane jsp;
//	JButton btdelete,btedit,btexit;
	//JPanel p1,p2;
	
	 public Viewallusers() 
	 {
	
	
		Object []colHeads={"Unique_ID","Username","Mobile_Number","Email_ID"};
		Object [][]rowData=null;
		
	/*	btdelete=new JButton("Delete User");
		btedit=new JButton("Edit User Details");
		btexit=new JButton("Exit"); */
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			try
			{
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt=con.createStatement();
				stmt.execute("use StudentManagementDB"); //database selection or open
				ResultSet rs=stmt.executeQuery("Select count(*) from UsersTb");
				int c=0;
				while(rs.next())
				{
					c=rs.getInt(1);
				}
				
				if(c==0)
				{
					JOptionPane.showMessageDialog(null, "no data");
					return;
				}
				rowData=new Object[c][4];
				rs=stmt.executeQuery("Select * from UsersTb");
				int i=0;
				while(rs.next())
				{
					rowData[i][0]=rs.getString(1);
					rowData[i][1]=rs.getString(2);
					rowData[i][2]=rs.getString(4);
					rowData[i][3]=rs.getString(5);
					i++;
				}
				table=new JTable(rowData,colHeads);
				jsp=new JScrollPane(table);
			
				
				/*p2=new JPanel();
				p2.setLayout(new GridLayout(1,3));
				p2.add(btdelete);
				p2.add(btedit);
				p2.add(btexit);*/
				
					
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
		add(jsp);
	}

	/*public void actionPerformed(ActionEvent ae)
	{
		Object src=ae.getSource();
		if(src==btdelete)
		{
			
		}
		else if(src==btedit)
		{
			
		}
		else 
			System.exit(0);
		
	}

*/
}
