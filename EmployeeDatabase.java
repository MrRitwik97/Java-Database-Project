import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class EmployeeDatabase implements ActionListener, KeyListener
{
	JFrame frm;
	JLabel lblSearchName, lblID, lblName, lblAddress, lblMobile, lblGender, lblHobby, lblEducation, lblDialogBox1, lblDialogBox2, lblDialogBox3, lblDialogBox4, lblDialogBox5, lblDialogBox6, lblDialogBox7, lblDialogBox8, lblDialogBox9, lblDialogBox10, lblDialogBox11, lblDialogBox12, lblDialogBox13, lblDialogBox14, lblDialogBox15;
	JTextField txtID, txtName, txtMobile;
	JTextArea txtAddress;
	JScrollPane sp;
	JRadioButton rbMale, rbFemale;
	ButtonGroup bg;
	JCheckBox chkCricket, chkBadminton, chkFootball, chkGym;
	JComboBox cbEducation, cbSearchName;
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

		lblSearchName = new JLabel("Search By Name");
		lblID = new JLabel("ID");
		lblName = new JLabel("Name");
		lblAddress = new JLabel("Address");
		lblMobile = new JLabel("Mobile Number");
		lblGender = new JLabel("Gender");
		lblHobby = new JLabel("Hobbies");
		lblEducation = new JLabel("Education");
		lblDialogBox1 = new JLabel("Enter The Employee ID");
		lblDialogBox2 = new JLabel("Enter The Employee Name");
		lblDialogBox3 = new JLabel("Enter The Employee Address");
		lblDialogBox4 = new JLabel("Enter Employee Mobile Number");
		lblDialogBox5 = new JLabel("Mobile No Is Less Than 10 Digits");
		lblDialogBox6 = new JLabel("Mobile No Must Be Of 10 Digits Only");
		lblDialogBox7 = new JLabel("Select Valid Gender");
		lblDialogBox8 = new JLabel("Please Select Valid Education");
		lblDialogBox9 = new JLabel("Record Added");
		lblDialogBox10 = new JLabel("You are in last record");
		lblDialogBox11 = new JLabel("You are on first record");
		lblDialogBox12 = new JLabel("Record Deleted");
		lblDialogBox13 = new JLabel("Type proper name.");
		lblDialogBox14 = new JLabel("Mobile number must be of 10 numbers.");
		lblDialogBox15 = new JLabel("Mobile number must be of 10 numbers.");

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
		cbSearchName = new JComboBox();

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
		
		pnlCenter.add(lblSearchName);
		pnlCenter.add(cbSearchName);

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
	}

	public void displayComp()
	{
		frm.setVisible(true);
		frm.setSize(1366,730);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setResizable(true);
		frm.setTitle("Employee Database");
		
		
		txtID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		txtMobile.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		sp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
		// Creating new Font object
		Font font = new Font("Consolas",Font.BOLD,16);

		// Applying font to labels
		lblSearchName.setFont(font);
		lblID.setFont(font);
		lblName.setFont(font);
		lblAddress.setFont(font);
		lblMobile.setFont(font);
		lblGender.setFont(font);
		lblHobby.setFont(font);
		lblEducation.setFont(font);
		lblDialogBox1.setFont(font);
		lblDialogBox2.setFont(font);
		lblDialogBox3.setFont(font);
		lblDialogBox4.setFont(font);
		lblDialogBox5.setFont(font);
		lblDialogBox6.setFont(font);
		lblDialogBox7.setFont(font);
		lblDialogBox8.setFont(font);
		lblDialogBox9.setFont(font);
		lblDialogBox10.setFont(font);
		lblDialogBox11.setFont(font);
		lblDialogBox12.setFont(font);
		lblDialogBox13.setFont(font);
		lblDialogBox14.setFont(font);
		lblDialogBox15.setFont(font);

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
		lblSearchName.setHorizontalAlignment(JLabel.CENTER);
		lblID.setHorizontalAlignment(JLabel.CENTER);
		lblName.setHorizontalAlignment(JLabel.CENTER);
		lblAddress.setHorizontalAlignment(JLabel.CENTER);
		lblMobile.setHorizontalAlignment(JLabel.CENTER);
		lblGender.setHorizontalAlignment(JLabel.CENTER);
		lblHobby.setHorizontalAlignment(JLabel.CENTER);
		lblEducation.setHorizontalAlignment(JLabel.CENTER);
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
					JOptionPane.showMessageDialog(frm,lblDialogBox1);
				}
			
				else if(txtName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frm,lblDialogBox2);
				}
			
				else if(txtAddress.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frm,lblDialogBox3);
				}
		
				else if(txtMobile.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frm,lblDialogBox4);
				}
			
				else if(mobileNoLength<10)
				{
					JOptionPane.showMessageDialog(frm,lblDialogBox5);
				}
			
				else if(mobileNoLength>10)
				{
					JOptionPane.showMessageDialog(frm,lblDialogBox6);
				}
			
				else if((rbMale.isSelected()==false)&&(rbFemale.isSelected()==false))
				{
					JOptionPane.showMessageDialog(frm,lblDialogBox7);
				}
			
				else if(cbEducation.getSelectedItem().equals("Select Education"))
				{
					JOptionPane.showMessageDialog(frm,lblDialogBox8);
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
					JOptionPane.showMessageDialog(frm, lblDialogBox9);

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
				enable1();
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
					JOptionPane.showMessageDialog(frm,lblDialogBox10);
				}
				else
				{
					rs.next();
					data();
					enable1();
				}
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
					JOptionPane.showMessageDialog(frm,lblDialogBox11);
				}
				else
				{
					rs.previous();
					data();
					enable1();
				}
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
				enable1();
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

				JOptionPane.showMessageDialog(frm,lblDialogBox12);

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
		
		txtName.addKeyListener(this);
		txtMobile.addKeyListener(this);
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

	public void keyPressed(KeyEvent KeyEvt)
	{}

	public void keyReleased(KeyEvent keyEvt)
	{}

	public void keyTyped(KeyEvent keyEvt)
	{
		String strMobLn = txtMobile.getText();
		int mobLength =strMobLn.length();
		
		if(keyEvt.getSource().equals(txtName))
		{
			if(keyEvt.getKeyChar()>='0' && keyEvt.getKeyChar()<='9')
			{
				JOptionPane.showMessageDialog(frm,lblDialogBox13);
				keyEvt.consume();
			}
		}
		else if(keyEvt.getSource().equals(txtMobile))
		{
			if((keyEvt.getKeyChar()>='0' && keyEvt.getKeyChar()<='9') || (keyEvt.getKeyChar()==8))
			{}
			else
			{
				JOptionPane.showMessageDialog(frm,lblDialogBox14);
				keyEvt.consume();
			}
			if(mobLength>=10)
			{
				JOptionPane.showMessageDialog(frm,lblDialogBox15);
				keyEvt.consume();
			}
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
	}
}