//30-6 : Bank Account System : SavingsAccount subclass 
public class SavingsAccount extends Account {
	
	protected double interest = 5;
	protected long maximum;
	protected double minimum = 500;
		
	//constructor override
	public SavingsAccount(String name, long accNum, double iniBalance, long max){
		super(name, accNum, iniBalance);
		maximum = max;
		
	}
	
	//method override
	public double getBalance(){
		return (accountBalance + accountBalance*(interest/100));
	}
	
	public void withdraw(long money){
		if(money <= maximum && accountBalance - money >= minimum){
			accountBalance = accountBalance - money;	//minimum 500tk na thakle wotdraw hobe na
		//	getBalance();
		}
		else
			System.out.println("Error");
	}
}
