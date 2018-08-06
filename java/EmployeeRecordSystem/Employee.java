//7-7-15 : Employee Record System
public abstract class Employee {
	protected String employeeName = "";
	protected long employeeSalary = 0;
	
	public Employee(String name){
		employeeName = name;
	}
	
	public String getName(){
		return employeeName;
	}
	
	public abstract void setSalary();
	
	public long getSalary(){
		return employeeSalary;
	}
}
