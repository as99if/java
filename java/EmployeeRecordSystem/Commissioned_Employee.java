import java.util.Scanner;
public class Commissioned_Employee extends Employee{
	protected long sale;
	protected double percentage;
	public Commissioned_Employee(String name){
		super(name);
	}
	public void setSalary(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Input weekly sale : ");
		long sale = scan.nextLong();
		System.out.print("Input percentage : ");
		double percentage = scan.nextDouble();
		
		employeeSalary = (long)(sale*(percentage/100));
	}
		
}
