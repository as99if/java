package SJF_Preemptive;


public class process {
	public int at;
	public int bt;
	public int id;
	timer t;
	public int flag=0;
	
	public static int turn_time;
	
	public process(int i, int a, int b, timer ti){
		at=a;
		bt=b;
		id=i;
		t=ti;
	}
	public void runprocess(){
        
         System.out.println("Running with ID: "+id+" , timer value: "+t.time );  
          t.time++;
          bt--;
          

          
          
          if(bt==0){
        	  turn_time = t.time - at;
        	  System.out.println("Process Complete with ID: "+id+" , at time : "+t.time);
        	  System.out.println("ID : " +id +" Turnaround time : " +turn_time);
        	  flag=1;
          } 
          
    }

}