package producer_consumer_with_semaphore;
/*Asif Ahmed*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;


public class ProducerConsumer {
	
	public static final int N = 15;
	public static int pturn = 1;
	public static int cturn = 1;
	
	public static Semaphore mutex1 = new Semaphore(1);
	public static Semaphore mutex2 = new Semaphore(1);
	public static Semaphore fillCount1 = new Semaphore(0);
	public static Semaphore fillCount2 = new Semaphore(0);
	public static Semaphore emptyCount1 = new Semaphore(N);
	public static Semaphore emptyCount2 = new Semaphore(N);
	
	
	public static Queue<Integer> buffer1 = new LinkedList<Integer>();
	public static Queue<Integer> buffer2 = new LinkedList<Integer>();
	
	
	public static void main(String[] args) {
	     
	        Producer producer1 = new Producer(mutex1, mutex2, emptyCount1, emptyCount2, fillCount1, fillCount2, buffer1, buffer2);
	        
	        Producer producer2 = new Producer(mutex1, mutex2, emptyCount1, emptyCount2, fillCount1, fillCount2, buffer1, buffer2);
	       
	        
	        Consumer consumer1 = new Consumer(mutex1, mutex2,emptyCount1, emptyCount2, fillCount1, fillCount2, buffer1, buffer2);
	        Consumer consumer2 = new Consumer(mutex1, mutex2,emptyCount1, emptyCount2, fillCount1, fillCount2, buffer1, buffer2);
	        Consumer consumer3 = new Consumer(mutex1, mutex2,emptyCount1, emptyCount2, fillCount1, fillCount2, buffer1, buffer2);
	        
	        
	        new Thread(producer1,"Producer 1").start();
	        
	        new Thread(consumer1,"Consumer 1").start();
	        
	        new Thread(producer2,"Producer 2").start();
	               
	        new Thread(consumer2,"Consumer 2").start();
	        
	        new Thread(consumer3,"Consumer 3").start();
	        
     
	        
	    }
	    
	
	
	
	
	
	
	
	
}
