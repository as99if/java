package os_lab_SJF;
/*non-preemptive*/

import java.util.Comparator;
import java.util.PriorityQueue;

import os_lab_SJF.Process;
import os_lab_SJF.Timer1;

public class SJF {
	
	static PriorityQueue<Process> pq = new PriorityQueue<Process>(10, new Comparator<Process>() {
		/*Input Queue*/
		@Override
		public int compare(Process p1, Process p2) {
			// TODO Auto-generated method stub
			
			return (p1.AT - p2.AT);
		}
	});
	
	static PriorityQueue<Process> mq = new PriorityQueue<Process>(10, new Comparator<Process>() {
		/*Burst time sort*/
		@Override
		public int compare(Process p1, Process p2) {
			// TODO Auto-generated method stub
			
			return (p1.BT - p2.BT);
		}
	});
	static PriorityQueue<Process> rq = new PriorityQueue<Process>(10, new Comparator<Process>() {
		/*Ready Queue*/
		@Override
		public int compare(Process p1, Process p2) {
			// TODO Auto-generated method stub
			return (p1.BT - p2.BT);
		}
	});
	
	
	static Timer1 t1 = new Timer1(0);
	
	public static void main(String []args){
		Process A = new Process(1, 1, 10, t1);			// (id, at, bt, timer )
		Process B = new Process(2, 2, 5, t1);
		Process C = new Process(3, 4, 2, t1);
		Process D = new Process(4, 8, 14, t1);
		
		pq.add(A);
		pq.add(B);
		pq.add(C);
		pq.add(D);
		
		System.out.println("Processes are added to PQ");
		
		while(true){
			while( !pq.isEmpty() && pq.element().AT <= t1.timer ){
				mq.add(pq.poll());			
			}
			
			while( !mq.isEmpty() && mq.element().AT <= t1.timer ){
				rq.add(mq.poll());			
			}
						
			if(!rq.isEmpty()){
				Process running = rq.poll();
				running.runprocess();
			}
			
			else{
				t1.timer++;
			}
				
		}
	}

	
	
}
