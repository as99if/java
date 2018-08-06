package SJF_NonPreemprive;


import java.util.Comparator;
import java.util.PriorityQueue;

public class SJF {

	/**
	 * @param args
	 */
	static PriorityQueue<process> pq = new PriorityQueue<process>(10,new Comparator<process>()
		    {
		        public int compare(process p1,process p2)
		        {
		            return p1.at-p2.at;
		        }

		    });
	static PriorityQueue<process> mq = new PriorityQueue<process>(10,new Comparator<process>()
		    {
		        public int compare(process p1,process p2)
		        {
		            return p1.bt-p2.bt;
		        }

		    });
	static PriorityQueue<process> rq = new PriorityQueue<process>(10,new Comparator<process>()
			    {
			        public int compare(process p1,process p2)
			        {
			            return p1.bt-p2.bt;
			        }

			    });
	static timer rt = new timer(0);
	
  public static void main(String[] args) {
		// TODO Auto-generated method stub
		 process A = new process(1,1,10,rt);
	     process B = new process(2,3,5,rt);
	     process C = new process(3,8,3,rt);
	     process D = new process(4,5,2,rt);
	     process E = new process(5,20,2,rt);
	     
	     pq.add(A);
	     pq.add(B);
	     pq.add(C);
         pq.add(D);
         pq.add(E);
	     System.out.println("All processes are added in PQ , timer starts at 0");
	    
	     while(true)
	        {
	            while(!pq.isEmpty() && pq.element().at <= rt.time)
	            {
	                System.out.println("Process added to pq at: "+pq.element().at+" , timer: "+rt.time);
	                mq.add(pq.poll());
	            }
	            while(!mq.isEmpty() && mq.element().at <= rt.time)
	            {
	                System.out.println("Process added to mq at: "+mq.element().at+" , timer: "+rt.time);
	                rq.add(mq.poll());
	            }
	            if(!rq.isEmpty())
	            {
	                process running  = rq.poll();
	                running.runprocess();
	            }
	            else
	                rt.time++;

	        }
	     
	}

}
