package SJF_NonPreemprive;

public class process {
	public int at;
	public int bt;
	public int id;
	timer t;
	public int rt; 
	
	public int turn_time;
	public int wait_time;
	
	public process(int i, int a, int b, timer ti){
		at=a;
		bt=b;
		id=i;
		t=ti;
	}
	public void runprocess(){
        for(int runtime1=0; runtime1<bt ;runtime1++){
            System.out.println("Running with ID: "+id+" , timer value: "+t.time);
            t.time++;
        }
        turn_time = t.time - at; 
        wait_time = t.time - (at + bt);
        System.out.println("Process Complete with ID: "+id+" , at time : " +t.time);
        System.out.println("Waiting time : " +wait_time);
        System.out.println("Turnaround time : " +turn_time);

    }

}
