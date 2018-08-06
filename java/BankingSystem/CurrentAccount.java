//30-6  Bank Account System  CurrentAccount subclass 
public class CurrentAccount extends Account {

	protected String tradeLicenseNumber;
	
	//constructor override
	public CurrentAccount(String name, long accNum, double iniBalance, String tdlNum){
		super(name, accNum, iniBalance);
		tradeLicenseNumber = tdlNum;
	}
	public double getBalance(){
		return accountBalance;
	}
	public void withdraw(long money){
		if(money <= accountBalance){
			
			accountBalance = accountBalance - money;
//			getBalance();
		}
		else
			System.out.println("Error");
	}
}
