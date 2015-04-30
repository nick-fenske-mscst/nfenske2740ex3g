package ex3g;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class PayrollObjMapper 
{
	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	
	public PayrollObjMapper(String fileName) 
	{
		this.fileName = fileName;
	}
	
	public boolean openInputFile()
	{
		boolean fileOpened = false;
		
		try 
		{
	    	File file = new File(this.fileName);
			fileOpened = file.exists();
			if(fileOpened) 
			{
			    this.inputFile = new Scanner(file);
			}
	    }
	    catch (IOException e) {}
		
		return fileOpened;
	}
	
	public boolean openOutputFile()
	{
		boolean fileOpened = false;
		
		try 
		{
			this.outputFile = new PrintWriter(this.fileName);
			if(this.outputFile != null)
			{
				fileOpened = true;
			}
	    }
	    catch (IOException e) {}
		
		return fileOpened;
	}
	
	public void closeInputFile()
	{
		if(this.inputFile != null)
			this.inputFile.close();
	}
	
	public void closeOutputFile()
	{
		if(this.outputFile != null)
			this.outputFile.close();
	}
	
	public Payroll getNextPayroll()
	{
		Payroll p = null;
	
		int id = 0;
		String name = "";
		double payRate = 0.0;
		double hours = 0.0;
		
		try
		{
			String textLine = this.inputFile.nextLine();
			id = Integer.parseInt(textLine);
			name = this.inputFile.nextLine();
			textLine = this.inputFile.nextLine();
			payRate = Double.parseDouble(textLine);
			textLine = this.inputFile.nextLine();
			hours = Double.parseDouble(textLine);
			p = new Payroll(id, name, payRate, hours);
		} 
		catch (NoSuchElementException e) {}
		catch (NumberFormatException e) {}
	
		
		return p;
	}
	
	public void writePayroll(Payroll payroll)
	{
		if(this.outputFile != null && payroll != null)
		{
			this.outputFile.println(payroll.getId());
			this.outputFile.println(payroll.getName());
			this.outputFile.println(payroll.getPayRate());
			this.outputFile.println(payroll.getHours());
		}
	}
	
	public DefaultListModel getAllPayroll()
	{
		DefaultListModel payrollCollection = new DefaultListModel();
		
		if(this.openInputFile())
		{
			while(this.inputFile.hasNext())
			{
				Payroll p = this.getNextPayroll();
				
				if(p != null)
				{
					payrollCollection.addElement(p);
				}
			}
			this.closeOutputFile();
		}
		
		return payrollCollection;
	}
	
	public void writeAllPayroll(DefaultListModel payrollCollection)
	{
		if(this.openOutputFile())
		{
			for(int i = 0 ; i < payrollCollection.getSize(); i++)
			{
				Payroll p = (Payroll) payrollCollection.get(i);
				this.writePayroll(p);
			}
			
			this.closeOutputFile();
		}
	}
}
