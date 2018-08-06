//30-6 : Bank Account System : Bank class : Main   

import java.util.Scanner;
import java.util.Random;

public class Bank {

	public static void main(String[] args) {
		int flag = 1, choice, type;
		int createAccount = 0;
		String name, tdlNum;
		long minimum = 500;
		int mini = 1;
		double money;
		
		Scanner scan = new Scanner(System.in);
		Account A = null; 
		while(flag == 1){
			
			showMenu();
			choice = scan.nextInt();
			
			if(choice == 1){
				createAccount = 1;
				//randomly accNumber generate korbo ekhane//
				
				
				System.out.println("Input Name :");
				name = scan.next();
				Random random = new Random();
				long accNum = 10000 + random.nextInt(99999);
				
				System.out.println("Choose type : 1. Current Account \n2.Savings Account");
				type = scan.nextInt();
				
					
				if(type == 1){
					System.out.println("Input Trade License Number :");
					tdlNum = scan.next();
					System.out.println("Input Initial Balance :");
					 money = scan.nextDouble();
					
					A = new CurrentAccount(name, accNum, money, tdlNum);
					
					System.out.println("Current account created\nAccount Number : "+A.getAccountNumber());
				}
					
				else if(type == 2){
					while(mini == 1){
						System.out.println("Input Initial Balance :");
						double x=scan.nextDouble();
						if(x>=minimum){
							money = x;
							mini = 0;
						}
						else{
							System.out.println("Error");
						}
					}
					System.out.println("Input Maximum ammount of withdrawal :");
					long max = scan.nextLong();
					
					A = new SavingsAccount(name, accNum, 500, max);
					
					System.out.println("Savings account created\nAccount Number : "+A.getAccountNumber());
				}
				else{
					System.out.println("Error");
				}
					
			}
			
			else if(choice == 2){
				if(createAccount == 1){
					System.out.print("Ammount : ");
					A.deposit(scan.nextLong());
				}
				else 
					System.out.println("Create account first");
			}
			
			else if(choice == 3){
				if(createAccount == 1){
					System.out.println("Ammount :");
					A.withdraw(scan.nextLong());
				}
				else 
					System.out.println("Create account first");
			}
			
			else if(choice == 4){
				if(createAccount == 1){
					System.out.println("Balance : " +A.getBalance());
				}
				else 
					System.out.println("Create account first");
			}
			
			else{
				System.out.println("Error");
			}
		
		}
	}

	public static void showMenu(){
		System.out.println("Choose option : \n1.Create Account \n2.Deposit Money \n3.Withdraw Money"
				+ "\n4.Show Balance");
	}
}
