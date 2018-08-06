package SJF_Preemptive;


public class process {
	public int at;
	public int bt;
	public int id;
	timer t;
	public int flag=0;
	
	public process(int i, int a, int b, timer ti){
		at=a;
		bt=b;
		id=i;
		t=ti;
	}
	public void runprocess(){
        
         System.out.println("Running with ID: "+id+" , timer value: "+t.time+" to "+(t.time+1));  
          t.time++;
          bt--;

          if(bt==0){
        	  System.out.println("Process Complete with ID: "+id+" , timer value: "+t.time);
        	  flag=1;
          }
     
    }

}