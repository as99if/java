package SJF_Preemptive;

import java.util.Comparator;
import java.util.PriorityQueue;



public class SJF_P {

	/**
	 * @param args
	 */
	static PriorityQueue<process> pq = new PriorityQueue<process>(10,new Comparator<process>() {
		public int compare(process p1,process p2){
			return p1.at-p2.at;
		}

	});
	static PriorityQueue<process> mq = new PriorityQueue<process>(10,new Comparator<process>(){
		public int compare(process p1,process p2) {
			return p1.bt-p2.bt;
		}
	});
	static PriorityQueue<process> rq = new PriorityQueue<process>(10,new Comparator<process>() {
		public int compare(process p1,process p2) {
			return p1.bt-p2.bt;
		}

	});
	
	static timer rt = new timer(0);
	public static int totalTurnAroundTime = 0;
	public static int numberOfProcess = 0;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 process A = new process(1,2,2,rt);
	     process B = new process(2,0,5,rt);
	     process C = new process(3,2,1,rt);
	     process D = new process(4,5,3,rt);
	     //process E = new process(5,20,2,rt);
	     
	     pq.add(A);
	     pq.add(B);
	     pq.add(C);
         pq.add(D);
         //pq.add(E);
	     System.out.println("All processes are added in PQ , timer starts at 0");
	     while(true) {
	            while(!pq.isEmpty() && pq.element().at <= rt.time) {
	                mq.add(pq.poll());
	            }
	            while(!mq.isEmpty() && mq.element().at <= rt.time) {
	                rq.add(mq.poll());
	            }
	            if(!rq.isEmpty()) {
	                process running  = rq.poll();
	                running.runprocess();
	                
	                if(running.flag==0) {
	                	rq.add(running);
	                }
	                if(running.flag == 1){
	                	totalTurnAroundTime += running.turn_time ;
	                	numberOfProcess++ ;
	                }
	            }
	            else {
	            	rt.time++;	
	            }
                 if(rt.time==30)
                	 break;
	     }
	     final int avgTurnAroundTime = (totalTurnAroundTime / numberOfProcess); 
	     System.out.println("avg turnaround time : " +avgTurnAroundTime);
	     
	}
}

