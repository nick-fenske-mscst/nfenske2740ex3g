package ex3g;
import java.text.DecimalFormat;

public class Payroll 
{
	private int id;
	private String name;
	private double payRate;
	private double hours;
	
	public Payroll(int id, String name, double payRate) 
	{
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = 0;
	}
	
	public Payroll(int id, String name, double payRate, double hours) 
	{
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public boolean setId(int id) 
	{
		if (id > 100) 
		{
			this.id = id;
			return true;
		}
		return false;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public boolean setName(String name) 
	{
		if(!name.isEmpty())
		{
			this.name = name;
			return true;
		}
		return false;
	}
	
	public double getPayRate() 
	{
		return payRate;
	}
	
	public boolean setPayRate(double payRate) 
	{
		if(payRate >= 7.25 && payRate <= 100.0)
		{
			this.payRate = payRate;
			return true;
		}
		return false;
	}
	
	public double getHours() 
	{
		return hours;
	}
	
	public void setHours(double hours) 
	{
		this.hours = hours;
	}
	
	public boolean addHours(double hours)
	{
		if(hours >= 0.1 && hours <= 20.0)
		{
			this.hours += hours;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() 
	{
		DecimalFormat formatDollar = new DecimalFormat("#,##0.00");
		return this.id + " " + this.name + ", Pay rate= " + formatDollar.format(this.payRate);
	}
	
	public double calcGrossPay()
	{
		double grossPay, overTimePay;
		
		if(this.hours > 40)
		{
			grossPay = 40 * this.payRate;
			overTimePay = (this.hours * 40) * (this.payRate * 1.5);
			grossPay += overTimePay;
		} else {
			grossPay = this.payRate * this.hours;
		}
		return grossPay;
	}
}
