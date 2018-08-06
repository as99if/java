
import java.util.Scanner;
public class EmployeeMain {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int emp = 0, choice, run = 1, typeChoice, i=0; 
		Employee[] list = new Employee[10] ;
		int[] check = new int[10];
		
		while(run == 1){
			
			System.out.println("Input choice : 1. Add Employee \t2. See employee names \t3. Set Salary \t4. Get Salary  ");
			
			choice = scan.nextInt();
			
			
			if(choice == 1){
				System.out.println("Input name of the employee :  ");
				String employeeName = scan.next();
				
				System.out.println("Input type : 1. Salaried \t2. Hourly \t3. Commissioned ");
				typeChoice = scan.nextInt();
				check[i] = 1;
				emp = 1;
				if(typeChoice == 1){
					
					list[i] = new Salaried_Employee(employeeName); 
					
					
					
					list[i].setSalary();
					i++;
				}
				if(typeChoice == 2){
					list[i] = new Hourly_Employee(employeeName); 
										
					list[i].setSalary();
					i++;
				}
				if(typeChoice == 3){
					list[i] = new Commissioned_Employee(employeeName);
										
					list[i].setSalary();
					i++;
				}
				
				System.out.println("Added Employees :");
				for(int k = 0; check[k] == 1; k++){
					System.out.println(k +". " +list[k].getName());
				}
		
			}
			
			else if(emp == 1 && choice == 2){
				for(int k = 0; check[k] == 1; k++){
					System.out.println(k +". " +list[k].getName());
				}
				
			}
			
			else if(emp == 1 && choice == 3){				//set salary
				System.out.print("Choose employee number : ");
				int j = scan.nextInt();
				list[j].setSalary();
					
			}
			
			else if(emp == 1 && choice == 4){				//get salary
				
				System.out.print("Choose employee number : ");
				int j = scan.nextInt();
				System.out.println("Salary of " +list[j].getName() + " : " +list[j].getSalary());
								
			}
			
			else{
				System.out.println("Error in choice");
			}
			
			
		}
		
	}
	
}
