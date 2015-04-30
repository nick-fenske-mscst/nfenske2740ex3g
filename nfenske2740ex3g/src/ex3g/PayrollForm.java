package ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JList employeeList;
	private JTextField hoursTextField;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private JTextField txtName;
	private JTextField txtPayRate;
	private JTextField txtEmployeeId;
	private JButton btnUpdate;
	private JButton addHoursButton;
	private JButton btnClear;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("NFenske 2740 Ex3G Payroll");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select Employee:");
		lblSelectEmployee.setBounds(10, 11, 116, 14);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 36, 345, 117);
		contentPane.add(scrollPane);
		
		//employeeList = new JList();
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100):");
		lblEmployeeId.setBounds(10, 164, 133, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmplyeeName = new JLabel("Emplyee Name:");
		lblEmplyeeName.setBounds(10, 189, 88, 14);
		contentPane.add(lblEmplyeeName);
		
		JLabel lblPayRate = new JLabel("Pay Rate (7.25 - 100):");
		lblPayRate.setBounds(10, 214, 133, 14);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter Hours (0.1 - 20.0):");
		lblEnterHours.setBounds(10, 239, 133, 14);
		contentPane.add(lblEnterHours);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_hoursTextField_focusGained(e);
			}
		});
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setText("0.00");
		hoursTextField.setBounds(160, 236, 67, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_addHoursButton_mouseClicked(arg0);
			}
		});
		addHoursButton.setBounds(230, 235, 48, 22);
		contentPane.add(addHoursButton);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_btnClear_mouseClicked(e);
			}
		});
		btnClear.setBounds(288, 235, 67, 22);
		contentPane.add(btnClear);
		
		JLabel lblTotalHours = new JLabel("Total Hours:");
		lblTotalHours.setBounds(10, 264, 88, 14);
		contentPane.add(lblTotalHours);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(108, 264, 112, 14);
		contentPane.add(totalHoursLabel);
		
		JLabel lblGrossPay = new JLabel("Gross Pay:");
		lblGrossPay.setBounds(10, 289, 88, 14);
		contentPane.add(lblGrossPay);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(108, 289, 112, 14);
		contentPane.add(grossPayLabel);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_btnClearForm_mouseClicked(e);
			}
		});
		btnClearForm.setBounds(249, 310, 106, 23);
		contentPane.add(btnClearForm);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_btnUpdate_mouseClicked(arg0);
			}
		});
		btnUpdate.setBounds(133, 310, 106, 23);
		contentPane.add(btnUpdate);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.RIGHT);
		txtName.setColumns(10);
		txtName.setBounds(111, 186, 116, 20);
		contentPane.add(txtName);
		
		txtPayRate = new JTextField();
		txtPayRate.setText("7.25");
		txtPayRate.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPayRate.setColumns(10);
		txtPayRate.setBounds(160, 211, 67, 20);
		contentPane.add(txtPayRate);
		
		txtEmployeeId = new JTextField();
		txtEmployeeId.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEmployeeId.setColumns(10);
		txtEmployeeId.setBounds(160, 161, 67, 20);
		contentPane.add(txtEmployeeId);
	}
	public boolean textBoxEmpty(String input)
	{
		return input.isEmpty();
	}
	protected void do_employeeList_mouseClicked(MouseEvent arg0) 
	{
		Payroll employee = (Payroll) employeeList.getSelectedValue();
		this.txtEmployeeId.setText(Integer.toString(employee.getId()));
		this.txtName.setText(employee.getName());
		DecimalFormat decFormat = new DecimalFormat("###0.00");
		this.txtPayRate.setText(decFormat.format(employee.getPayRate()));
		this.totalHoursLabel.setText(Double.toString(employee.getHours()));
		DecimalFormat dollarFormat = new DecimalFormat("$#,##0.00");
		this.grossPayLabel.setText(dollarFormat.format(employee.calcGrossPay()));
		this.hoursTextField.requestFocus();
		this.addHoursButton.setEnabled(true);
		this.btnClear.setEnabled(true);
		this.btnUpdate.setEnabled(true);
	}
	protected void do_addHoursButton_mouseClicked(MouseEvent arg0) 
	{
		Payroll employee = (Payroll) employeeList.getSelectedValue();
		if(!textBoxEmpty(hoursTextField.getText()))
		{
			if(!employee.addHours(Double.parseDouble(hoursTextField.getText())))
			{
				JOptionPane.showMessageDialog(null, "Invalid hour range. \nMust be from 0.1 to 20.0");
				hoursTextField.setText("0.00");
				hoursTextField.requestFocus();
				hoursTextField.selectAll();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a value for hours.");
			hoursTextField.setText("0.00");
			hoursTextField.requestFocus();
			hoursTextField.selectAll();
		}
		DecimalFormat decFormat = new DecimalFormat("###0.00");
		this.totalHoursLabel.setText(decFormat.format(employee.getHours()));
		DecimalFormat dollarFormat = new DecimalFormat("$#,##0.00");
		this.grossPayLabel.setText(dollarFormat.format(employee.calcGrossPay()));
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
	}
	protected void do_btnClear_mouseClicked(MouseEvent e) 
	{
		Payroll employee = (Payroll) employeeList.getSelectedValue();
		employee.setHours(0);
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
	}
	protected void do_btnClearForm_mouseClicked(MouseEvent e) 
	{
		this.txtEmployeeId.setText("0");
		this.txtName.setText("");
		this.txtPayRate.setText("0.00");
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection();
		this.addHoursButton.setEnabled(false);
		this.btnClear.setEnabled(false);
		this.btnUpdate.setEnabled(false);
	}
	protected void do_hoursTextField_focusGained(FocusEvent e) 
	{
		hoursTextField.selectAll();
	}
	protected void do_btnUpdate_mouseClicked(MouseEvent arg0) 
	{
		String name = txtName.getText();
		
		Payroll employee = (Payroll) employeeList.getSelectedValue();
		DecimalFormat formatRate = new DecimalFormat("##0.00");
		
		if(!textBoxEmpty(txtEmployeeId.getText()))
		{
			int id = Integer.parseInt(txtEmployeeId.getText());
			if(!employee.setId(id))
			{
				JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
				txtEmployeeId.setText(Integer.toString(employee.getId()));
				txtEmployeeId.requestFocus();
				txtEmployeeId.selectAll();
				return;//avoid multiple errors
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please enter an employee ID.");
			txtEmployeeId.setText(Integer.toString(employee.getId()));
			txtEmployeeId.requestFocus();
			txtEmployeeId.selectAll();
			return;//avoid multiple errors
		}
		
		if(!employee.setName(name))
		{
			JOptionPane.showMessageDialog(null, "Invalid employee name.");
			txtName.setText(employee.getName());
			txtName.requestFocus();
			txtName.selectAll();
			return;//avoid multiple errors
		}
		
		if(!textBoxEmpty(txtPayRate.getText()))
		{
			double rate = Double.parseDouble(txtPayRate.getText());
			if(!employee.setPayRate(rate))
			{
				JOptionPane.showMessageDialog(null, "Invalid pay rate. \nMust be in range 7.25 - 100.");
				txtPayRate.setText(formatRate.format(employee.getPayRate()));
				txtPayRate.requestFocus();
				txtPayRate.selectAll();
				return;//avoid multiple errors
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a pay rate.");
			txtPayRate.setText(formatRate.format(employee.getPayRate()));
			txtPayRate.requestFocus();
			txtPayRate.selectAll();
			return;//avoid multiple errors
		}
		
		employeeList.repaint();
	}
	
	protected void do_this_windowClosing(WindowEvent arg0) 
	{
		if(payrollObjMapper != null)
		{
			payrollObjMapper.writeAllPayroll(employeeListModel);
		}
	}
}
