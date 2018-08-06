package producer_consumer_with_semaphore;
/*Asif Ahmed*/
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Consumer implements Runnable{
	
	Semaphore mutex = null;
	Semaphore fillCount = null;
	Semaphore emptyCount = null;
		
	Queue<Integer> buffer = null;
	
	Random rand = new Random();
	int item;
	
	public Consumer(Semaphore mutex, Semaphore emptyCount, Semaphore fillCount, Queue<Integer> buffer){
		this.mutex = mutex;
		this.emptyCount = emptyCount;
		this.fillCount = fillCount;
		this.buffer = buffer;
	}


	@Override
	public void run() {
		
		while(true){
			try
				
				if(fillCount.availablePermits() == 0)		// when bufferT is empty
					System.out.println(Thread.currentThread().getName() + " is waiting");
				else{
					fillCount.acquire();
					mutex.acquire();
					
					/*push from buffer*/
					item = buffer.poll();
					System.out.println(Thread.currentThread().getName() + " consumed " + item);
					
					mutex.release();
					emptyCount.release();
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
