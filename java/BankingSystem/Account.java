//30-6 : Bank Account System : Account class 
public abstract class Account {
	protected String memberName;
	protected long accountNumber;
	protected double accountBalance = 0;
	
	public Account(String name, long accNum, double iniBalance){
		memberName = name;
		accountNumber = accNum;
		accountBalance = iniBalance;
	}
	
	public void setName(String name){
		memberName = name;
	}
	public String getName(){
		return memberName;
	}
	public long getAccountNumber(){
		return accountNumber;
	}
	public abstract double getBalance();
			
	public void deposit(long money){
		accountBalance = accountBalance + money;
		getBalance();
	}
	
	public abstract void withdraw(long money);
}
