import java.util.Scanner;
public class Salaried_Employee extends Employee {
	
	public Salaried_Employee(String name){		
		super(name);
	}
	
	public void setSalary(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Input weekly rate  of salary : ");
		long weeklySalary = scan.nextLong();
		
		employeeSalary = weeklySalary;
	}
	
	
}
