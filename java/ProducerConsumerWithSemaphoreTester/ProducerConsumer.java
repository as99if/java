package producer_consumer_with_semaphore;
/*Asif Ahmed*/
/*with tester*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;


public class ProducerConsumer {
	
	public static final int N = 10;
	public static int i = 0;
	
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore fillCount = new Semaphore(0);
	public static Semaphore emptyCount = new Semaphore(N);
	
	public static Semaphore mutexT = new Semaphore(1);
	public static Semaphore fillCountT = new Semaphore(0);
	public static Semaphore emptyCountT = new Semaphore(N);
	
	
	public static Queue<Integer> buffer = new LinkedList<Integer>();
	public static Queue<Integer> bufferT = new LinkedList<Integer>();

	
	 public static void main(String[] args) {
	       
         	System.out.println("Producer Consumer with Tester");
	        
	        Producer producer1=new Producer(mutex,emptyCount, fillCount, buffer);
	        Producer producer2=new Producer(mutex,emptyCount, fillCount, buffer);
	        
	        Tester tester1 = new Tester(mutex,emptyCount, fillCount, buffer, mutexT,emptyCountT, fillCountT, bufferT);
	        Tester tester2 = new Tester(mutex,emptyCount, fillCount, buffer, mutexT,emptyCountT, fillCountT, bufferT);
	        
	        Consumer consumer1=new Consumer(mutexT,emptyCountT, fillCountT, bufferT);
	        Consumer consumer2=new Consumer(mutexT,emptyCountT, fillCountT, bufferT);
	        Consumer consumer3=new Consumer(mutexT,emptyCountT, fillCountT, bufferT);
	        
	        //Tester tester1 = new Tester(buffer, testFlag);
	        
	        
	        new Thread(producer1,"Producer 1").start();
	        	                
	        new Thread(producer2,"Producer 2").start();
	        
	        new Thread(tester1,"Tester 1").start();
	        
	        new Thread(tester2,"Tester 2").start();
	        
	        new Thread(consumer1,"Consumer 1").start();
	        
	        new Thread(consumer2,"Consumer 2").start();
	        
	        new Thread(consumer3,"Consumer 3").start();
	        
     
	        
	    }
	    
	
	
	
	
	
	
	
	
}
