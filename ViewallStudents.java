

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewallStudents extends JPanel
{

	JTable table;
	JScrollPane jsp;
	//JButton btdelete,btedit,btexit;
	//JPanel p1,p2;
	
	public ViewallStudents()
	{
		Object []colHeads={"Enrollment_No","Username","father_name","mother_name","Gender","DOB" ,"Address","Degree","Degree_fees","Facility","Fac_fee"};
		Object [][]rowData=null;
		
		//btdelete=new JButton("Delete Student");
		//btedit=new JButton("Edit Student Details");
		//btexit=new JButton("Exit");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			try
			{
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt=con.createStatement();
				stmt.execute("use StudentManagementDB"); //database selection or open
				ResultSet rs=stmt.executeQuery("Select count(*) from StudentPersonalTb");
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
				rowData=new Object[c][12];
				rs=stmt.executeQuery("Select * from StudentPersonalTb");
				int i=0;
				while(rs.next())
				{
					rowData[i][0]=rs.getString(2);
					rowData[i][1]=rs.getString(1);
					rowData[i][2]=rs.getString(3);
					rowData[i][3]=rs.getString(4);
					rowData[i][4]=rs.getString(5);
					rowData[i][5]=rs.getString(6);
					rowData[i][6]=rs.getString(7);
					rowData[i][7]=rs.getString(8);
					rowData[i][8]=rs.getString(9);
					rowData[i][9]=rs.getString(10);
					rowData[i][10]=rs.getString(11);
					i++;
				}
				table=new JTable(rowData,colHeads);
				jsp=new JScrollPane(table);
				//p1=new JPanel();
				add(jsp);
				
				//p2=new JPanel();
				//p2.setLayout(new GridLayout(1,3));
				//p2.add(btdelete);
				//p2.add(btedit);
				//p2.add(btexit);
				
			//	setLayout(new GridLayout(2,1));
				//add(p1);
				//add(p2);
				
				con.close();
				
				//btdelete.addActionListener(this);
				//btedit.addActionListener(this);
				//btexit.addActionListener(this);
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
