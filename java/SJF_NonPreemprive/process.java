package SJF_NonPreemprive;

public class process {
	public int at;
	public int bt;
	public int id;
	timer t;
	public int rt; 
	public process(int i, int a, int b, timer ti)
	{
		at=a;
		bt=b;
		id=i;
		t=ti;
	}
	public void runprocess()
    {
        for(int runtime1=0; runtime1<bt ;runtime1++)
        {
            System.out.println("Running with ID: "+id+" , timer value: "+t.time+" to "+(t.time+1));  
            t.time++;
        }
        System.out.println("Process Complete with ID: "+id+" , timer value: "+t.time);

    }

}
