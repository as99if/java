package producer_consumer_with_semaphore;
/*Asif Ahmed*/
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {
	
	Semaphore mutex = null;
	Semaphore fillCount = null;
	Semaphore emptyCount = null;
		
	Queue<Integer> buffer = null;
	
	Random rand = new Random();
	int item;
	
	public Producer(Semaphore mutex, Semaphore emptyCount, Semaphore fillCount, Queue<Integer> buffer){
		this.mutex = mutex;
		this.emptyCount = emptyCount;
		this.fillCount = fillCount;
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while(true){
			
			try {
				
				item = rand.nextInt(20)+1;
				
				if(emptyCount.availablePermits() == 0)		// when buffer is full
					System.out.println(Thread.currentThread().getName() + " is waiting");
				else{
					emptyCount.acquire();
					mutex.acquire();
					
					/*put into buffer*/
					buffer.add(item);
					System.out.println(Thread.currentThread().getName() + " produced " + item);
										
					mutex.release();
					fillCount.release();
				}
				Thread.sleep(rand.nextInt(30) + 1);
				
			} catch (InterruptedException ex) {
				// TODO: handle exception
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		// TODO Auto-generated method stub
	}
	
	
}
