package producer_consumer_with_semaphore;
/*Asif Ahmed*/
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester implements Runnable {
	

	Semaphore mutex = null;
	Semaphore fillCount = null;
	Semaphore emptyCount = null;
	
	Semaphore mutexT = null;
	Semaphore fillCountT = null;
	Semaphore emptyCountT = null;
	
	Queue<Integer> buffer = null;
	Queue<Integer> bufferT = null;
	
	Random rand = new Random();
	int item;
	
	public Tester(Semaphore mutex, Semaphore emptyCount, Semaphore fillCount, Queue<Integer> buffer, 
			Semaphore mutexT, Semaphore emptyCountT, Semaphore fillCountT, Queue<Integer> bufferT){
		this.mutex = mutex;
		this.emptyCount = emptyCount;
		this.fillCount = fillCount;
		this.buffer = buffer;
		
		this.mutexT = mutexT;
		this.emptyCountT = emptyCountT;
		this.fillCountT = fillCountT;
		this.bufferT = bufferT;
		
	}
	
	@Override
	public void run() {
		while(true){
			
			try {
				
				if(fillCount.availablePermits() == 0)		// when buffer is empty
					System.out.println(Thread.currentThread().getName() + " is waiting");
				else{
					fillCount.acquire();
					mutex.acquire();			// locking buffer
					item = buffer.remove();
					System.out.println(Thread.currentThread().getName()+" testing....: " + item);
					mutex.release();
					emptyCount.release();
				}
				Thread.sleep(rand.nextInt(30) + 1);
				
				
				
				if(emptyCount.availablePermits() == 0)		// when buffrT is full
					System.out.println(Thread.currentThread().getName() + " is waiting");
				else{
					emptyCountT.acquire();
					mutexT.acquire();			// locking bufferT
					bufferT.add(item);
					System.out.println(Thread.currentThread().getName()+" tested: " + item);
					mutexT.release();
					fillCountT.release();
				}			
				Thread.sleep(rand.nextInt(30) + 1);
				
			} catch (InterruptedException ex) {
				// TODO: handle exception
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
}
