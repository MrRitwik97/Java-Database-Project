import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class EmployeeDatabase implements ActionListener
{
	JFrame frm;
	JLabel lblApplication, lblApplication2, lblID, lblName, lblAddress, lblMobile, lblGender, lblHobby, lblEducation;
	JTextField txtID, txtName, txtMobile;
	JTextArea txtAddress;
	JScrollPane sp;
	JRadioButton rbMale, rbFemale;
	ButtonGroup bg;
	JCheckBox chkCricket, chkBadminton, chkFootball, chkGym;
	JComboBox cbEducation;
	JButton btnFirst, btnNext, btnPrev, btnLast, btnAdd, btnUpdate, btnDelete, btnClear, btnExit;
	JPanel pnlCenter, pnlSouth, pnlGender, pnlHobby;
	BorderLayout br;
	GridLayout gr1, gr2, gr3;
	FlowLayout fl;
	Connection con;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;

	public void createComp()
	{
		fl = new FlowLayout();

		String[] Education = {"Select Education", "BSC.IT", "BSC.CS", "BCA", "MCA", "HM", "CA"};

		frm = new JFrame("Employee Database");

		lblApplication = new JLabel("Employee ");
		lblApplication2 = new JLabel("Database");
		lblID = new JLabel("ID");
		lblName = new JLabel("Name");
		lblAddress = new JLabel("Address");
		lblMobile = new JLabel("Mobile Number");
		lblGender = new JLabel("Gender");
		lblHobby = new JLabel("Hobbies");
		lblEducation = new JLabel("Education");

		txtID = new JTextField(2);
		txtName = new JTextField(10);
		txtMobile = new JTextField(10);

		txtAddress = new JTextArea(2,30);
		sp = new JScrollPane(txtAddress);

		bg = new ButtonGroup();
		rbMale = new JRadioButton("Male");
		rbFemale = new JRadioButton("Female");

		chkCricket = new JCheckBox("Cricket");
		chkFootball = new JCheckBox("Football");
		chkBadminton = new JCheckBox("Badminton");
		chkGym = new JCheckBox("Gym");

		cbEducation = new JComboBox(Education);

		btnFirst = new JButton("First");
		btnLast = new JButton("Last");
		btnAdd = new JButton("Add");
		btnNext = new JButton(">>");
		btnPrev = new JButton("<<");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnClear = new JButton("Clear");
		btnExit = new JButton("Exit");

		gr1 = new GridLayout(8,2,0,60);
		gr2 = new GridLayout(1,2);
		gr3 = new GridLayout(2,2);

		pnlCenter = new JPanel();
		pnlSouth = new JPanel();
		pnlGender = new JPanel();
		pnlHobby = new JPanel();

		br = new BorderLayout();
	}

	public void addComp()
	{
		frm.setLayout(br);

		pnlCenter.setLayout(gr1);
		
		pnlCenter.add(lblApplication);
		pnlCenter.add(lblApplication2);

		pnlCenter.add(lblID);
		pnlCenter.add(txtID);
		
		pnlCenter.add(lblName);
		pnlCenter.add(txtName);
		
		pnlCenter.add(lblAddress);
		pnlCenter.add(sp);

		pnlCenter.add(lblMobile);
		pnlCenter.add(txtMobile);
		
		pnlCenter.add(lblGender);
		pnlCenter.add(pnlGender);
		pnlGender.setLayout(gr2);
		bg.add(rbMale);
		bg.add(rbFemale);
		pnlGender.add(rbMale);
		pnlGender.add(rbFemale);
		
		pnlCenter.add(lblHobby);
		pnlCenter.add(pnlHobby);
		pnlHobby.setLayout(gr3);
		pnlHobby.add(chkCricket);
		pnlHobby.add(chkBadminton);
		pnlHobby.add(chkFootball);
		pnlHobby.add(chkGym);
		
		pnlCenter.add(lblEducation);
		pnlCenter.add(cbEducation);

		pnlSouth.add(btnFirst);
		pnlSouth.add(btnLast);
		pnlSouth.add(btnNext);
		pnlSouth.add(btnPrev);
		pnlSouth.add(btnAdd);
		pnlSouth.add(btnUpdate);
		pnlSouth.add(btnDelete);
		pnlSouth.add(btnClear);
		pnlSouth.add(btnExit);

		frm.add(pnlCenter,BorderLayout.CENTER);
		frm.add(pnlSouth,BorderLayout.SOUTH);
		
		txtID.setEditable(false);
		txtName.setEditable(false);
		txtAddress.setEditable(false);
		txtMobile.setEditable(false);
		rbMale.setEnabled(false);
		rbFemale.setEnabled(false);
		chkCricket.setEnabled(false);
		chkFootball.setEnabled(false);
		chkBadminton.setEnabled(false);
		chkGym.setEnabled(false);
		cbEducation.setEnabled(false);
	}

	public void displayComp()
	{
		frm.setVisible(true);
		frm.setSize(1366,730);
	}

	public void data()
	{
		try
		{
			chkCricket.setSelected(false);
			chkFootball.setSelected(false);
			chkGym.setSelected(false);
			chkBadminton.setSelected(false);

			txtID.setText(""+rs.getInt(1));
			txtName.setText(""+rs.getString(2));
			txtAddress.setText(""+rs.getString(3));
			txtMobile.setText(""+rs.getString(4));
			cbEducation.setSelectedItem(""+rs.getString(10));

			if(rs.getString(5).equals("male"))
			{
				rbMale.setSelected(true);
			}
			else
			{
				rbFemale.setSelected(true);
			}

			if(rs.getString(6).equals("cricket"))
			{
				chkCricket.setSelected(true);
			}
			if(rs.getString(7).equals("football"))
			{
				chkFootball.setSelected(true);
			}
			if(rs.getString(8).equals("badminton"))
			{
				chkBadminton.setSelected(true);
			}
			if(rs.getString(9).equals("gym"))
			{
				chkGym.setSelected(true);
			}
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void fontChange()
	{
		// Creating new Font
		Font font = new Font("Consolas",Font.BOLD,16);
		Font font2 = new Font("Times New Roman",Font.BOLD,20);

		// Applying font to labels
		lblApplication.setFont(font2);
		lblApplication2.setFont(font2);
		lblID.setFont(font);
		lblName.setFont(font);
		lblAddress.setFont(font);
		lblMobile.setFont(font);
		lblGender.setFont(font);
		lblHobby.setFont(font);
		lblEducation.setFont(font);

		// Applying font to textfields
		txtID.setFont(font);
		txtName.setFont(font);
		txtAddress.setFont(font);
		txtMobile.setFont(font);

		// Applying font to radiobuttons
		rbMale.setFont(font);
		rbFemale.setFont(font);

		// Applying font to checkboxes
		chkCricket.setFont(font);
		chkFootball.setFont(font);
		chkGym.setFont(font);
		chkBadminton.setFont(font);

		// Applying font to combobox
		cbEducation.setFont(font);

		// Applying font to buttons
		btnAdd.setFont(font);
		btnFirst.setFont(font);
		btnLast.setFont(font);
		btnNext.setFont(font);
		btnPrev.setFont(font);
		btnUpdate.setFont(font);
		btnDelete.setFont(font);
		btnClear.setFont(font);
		btnExit.setFont(font);
	}

	public void setLabelAlignment()
	{
		lblApplication.setHorizontalAlignment(JLabel.RIGHT);
		lblApplication2.setHorizontalAlignment(JLabel.LEFT);
		lblID.setHorizontalAlignment(JLabel.CENTER);
		lblName.setHorizontalAlignment(JLabel.CENTER);
		lblAddress.setHorizontalAlignment(JLabel.CENTER);
		lblMobile.setHorizontalAlignment(JLabel.CENTER);
		lblGender.setHorizontalAlignment(JLabel.CENTER);
		lblHobby.setHorizontalAlignment(JLabel.CENTER);
		lblEducation.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void searchOperation()
	{
		try
		{
			
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		String strMobileNo = txtMobile.getText();
		int mobileNoLength = strMobileNo.length();

		if(ae.getSource().equals(btnExit))
		{
			try
			{	
				System.exit(0);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		if(ae.getSource().equals(btnAdd))
		{

				if(txtID.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frm,"Enter The Employee ID");
				}
			
				else if(txtName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frm,"Enter The Employee Name");
				}
			
				else if(txtAddress.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frm,"Enter The Employee Address");
				}
		
				else if(txtMobile.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frm,"Enter Employee Mobile Number");
				}
			
				else if(mobileNoLength<10)
				{
					JOptionPane.showMessageDialog(frm,"Mobile No Is Less Than 10 Digits");
				}
			
				else if(mobileNoLength>10)
				{
					JOptionPane.showMessageDialog(frm,"Mobile No Must Be Of 10 Digits Only");
				}
			
				else if((rbMale.isSelected()==false)&&(rbFemale.isSelected()==false))
				{
					JOptionPane.showMessageDialog(frm,"Select Valid Gender");
				}
			
				else if(cbEducation.getSelectedItem().equals("Select Education"))
				{
					JOptionPane.showMessageDialog(frm,"Please Select Valid Education");
				}
				try
				{
					ps = con.prepareStatement("Insert into EmpDetails values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					int empID = Integer.parseInt(txtID.getText());
					String empName = txtName.getText();
					String empAddress = txtAddress.getText();
					String empMobile = txtMobile.getText();
					String empQuali = (String)cbEducation.getSelectedItem();

					ps.setInt(1, empID);
					ps.setString(2, empName);
					ps.setString(3, empAddress);
					ps.setString(4, empMobile);
					ps.setString(10, empQuali);

					if(rbMale.isSelected()==true)
					{
						ps.setString(5, "male");
					}
					else if(rbFemale.isSelected()==true);
					{
						ps.setString(5, "female");
					}

					if(chkCricket.isSelected()==true)
					{
						ps.setString(6, "cricket");
					}
					else
					{
						ps.setString(6, "");
					}
					
					if(chkFootball.isSelected()==true)
					{
						ps.setString(7, "football");
					}
					else
					{
						ps.setString(7, "");
					}
					
					if(chkBadminton.isSelected()==true)
					{
						ps.setString(8, "badminton");
					}
					else
					{
						ps.setString(8, "");
					}
					
					if(chkGym.isSelected()==true)
					{
						ps.setString(9, "gym");
					}
					else
					{
						ps.setString(9, "");
					}

					int i = ps.executeUpdate();
					JOptionPane.showMessageDialog(frm, "Record Added");

					txtID.setText("");
					txtName.setText("");
					txtAddress.setText("");
					txtMobile.setText("");		

					bg.remove(rbMale);
					bg.remove(rbFemale);
			
					rbMale.setSelected(false);
					rbFemale.setSelected(false);

					bg.add(rbMale);
					bg.add(rbFemale);
			
					chkCricket.setSelected(false);
					chkFootball.setSelected(false);
					chkBadminton.setSelected(false);
					chkGym.setSelected(false);
			
					cbEducation.setSelectedItem("Select Education");
				}
			
			catch(Exception e)
			{
				System.out.println(e);
			}
		}		

		if(ae.getSource().equals(btnClear))
		{
			try
			{
				txtID.setText("");
				txtName.setText("");
				txtAddress.setText("");
				txtMobile.setText("");		

				bg.remove(rbMale);
				bg.remove(rbFemale);
			
				rbMale.setSelected(false);
				rbFemale.setSelected(false);

				bg.add(rbMale);
				bg.add(rbFemale);
			
				chkCricket.setSelected(false);
				chkFootball.setSelected(false);
				chkBadminton.setSelected(false);
				chkGym.setSelected(false);
			
				cbEducation.setSelectedItem("Select Education");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		if(ae.getSource().equals(btnFirst))
		{
			try
			{
				rs.first();
				data();
				
				txtID.setEditable(false);
				txtName.setEditable(false);
				txtAddress.setEditable(false);
				txtMobile.setEditable(false);
				rbMale.setEnabled(false);
				rbFemale.setEnabled(false);
				chkCricket.setEnabled(false);
				chkFootball.setEnabled(false);
				chkBadminton.setEnabled(false);
				chkGym.setEnabled(false);
				cbEducation.setEnabled(false);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		if(ae.getSource().equals(btnNext))
		{
			try
			{
				if(rs.isLast())
				{
					JOptionPane.showMessageDialog(frm,"You are in last record");
				}
				else
				{
					rs.next();
					data();
				}
				txtID.setEditable(false);
				txtName.setEditable(false);
				txtAddress.setEditable(false);
				txtMobile.setEditable(false);
				rbMale.setEnabled(false);
				rbFemale.setEnabled(false);
				chkCricket.setEnabled(false);
				chkFootball.setEnabled(false);
				chkBadminton.setEnabled(false);
				chkGym.setEnabled(false);
				cbEducation.setEnabled(false);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		if(ae.getSource().equals(btnPrev))
		{
			try
			{
				if (rs.isFirst())
				{
					JOptionPane.showMessageDialog(frm,"You are on first record");
				}
				else
				{
					rs.previous();
					data();
				}
				txtID.setEditable(false);
				txtName.setEditable(false);
				txtAddress.setEditable(false);
				txtMobile.setEditable(false);
				rbMale.setEnabled(false);
				rbFemale.setEnabled(false);
				chkCricket.setEnabled(false);
				chkFootball.setEnabled(false);
				chkBadminton.setEnabled(false);
				chkGym.setEnabled(false);
				cbEducation.setEnabled(false);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		if(ae.getSource().equals(btnLast))
		{
			try
			{
				rs.last();
				data();
				
				txtID.setEditable(false);
				txtName.setEditable(false);
				txtAddress.setEditable(false);
				txtMobile.setEditable(false);
				rbMale.setEnabled(false);
				rbFemale.setEnabled(false);
				chkCricket.setEnabled(false);
				chkFootball.setEnabled(false);
				chkBadminton.setEnabled(false);
				chkGym.setEnabled(false);
				cbEducation.setEnabled(false);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		if(ae.getSource().equals(btnUpdate))
		{
			try
			{
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

		if(ae.getSource().equals(btnDelete))
		{
			try
			{
				ps = con.prepareStatement("delete EmpDetails where id = ?");
				ps.setInt(1, Integer.parseInt(txtID.getText()));
				int i = ps.executeUpdate();

				JOptionPane.showMessageDialog(frm,"Record Deleted");

				txtID.setText("");
				txtName.setText("");
				txtAddress.setText("");
				txtMobile.setText("");		

				bg.remove(rbMale);
				bg.remove(rbFemale);
			
				rbMale.setSelected(false);
				rbFemale.setSelected(false);

				bg.add(rbMale);
				bg.add(rbFemale);
			
				chkCricket.setSelected(false);
				chkFootball.setSelected(false);
				chkBadminton.setSelected(false);
				chkGym.setSelected(false);
			
				cbEducation.setSelectedItem("Select Education");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	public void registerEvent()
	{
		btnFirst.addActionListener(this);
		btnLast.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		btnAdd.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnClear.addActionListener(this);
		btnExit.addActionListener(this);
	}

	public void databaseConnection()
	{
		try
		{
			String username = "sa";
			String password = "sasa";
			String url = "jdbc:sqlserver://RITWIK-PC;databaseName=Ritwik";
	
			con = DriverManager.getConnection(url,username,password);
		
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			rs = stmt.executeQuery("select * from EmpDetails");
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
	}

 	public static void main(String[] args)
	{
		EmployeeDatabase obj = new EmployeeDatabase();
		obj.createComp();
		obj.addComp();
		obj.displayComp();
		obj.registerEvent();
		obj.fontChange();
		obj.setLabelAlignment();
		obj.databaseConnection();
		obj.searchOperation();
	}
}
