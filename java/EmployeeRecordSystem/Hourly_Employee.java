import java.util.Scanner;
public class Hourly_Employee extends Employee {
	
	public Hourly_Employee(String name){
		super(name);
	}
	public void setSalary(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Input hourly rate  of salary : ");
		double rate = scan.nextDouble();
		System.out.print("Input hour worked : ");
		double hour = scan.nextDouble();
		
		employeeSalary = (long)(rate * hour);
	}
	
}
