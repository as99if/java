package os_lab_SJF;

import os_lab_SJF.Timer1;

public class Process {
	int id;	
	int AT;		//arrival time
	int BT;		//burst time
	
	Timer1 t1;
	
	public Process(int id, int AT, int BT, Timer1 t1){
		this.id = id;
		this.AT = AT;
		this.BT = BT;
		this.t1 = t1;
	}
	
	public void runprocess(){
		for(int runtime1 = 0; runtime1<BT; runtime1++){
			System.out.println("Running with ID :" + id + " in time : " + t1.timer);
			t1.timer += 1 ;   
		}
		System.out.println("Process completed with ID : " +id);
	}
	
	public int getAT(){
		return AT;
	}

	public int getBT(){
		return BT;
	}

	

}
