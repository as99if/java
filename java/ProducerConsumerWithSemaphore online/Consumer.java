package producer_consumer_with_semaphore;
/*Asif Ahmed*/
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Consumer implements Runnable{
	
	Semaphore mutex1 = null;

	Semaphore mutex2 = null;
	Semaphore fillCount1 = null;
	Semaphore emptyCount1 = null;
	Semaphore fillCount2 = null;
	Semaphore emptyCount2 = null;
		
	
	Queue<Integer> buffer1 = null;
	Queue<Integer> buffer2 = null;
	
	
	int id;
	
	Random rand = new Random();
	
	public Consumer(Semaphore mutex1, Semaphore mutex2, Semaphore emptyCount1, Semaphore emptyCount2, 
								Semaphore fillCount1, Semaphore fillCount2,  
								Queue<Integer> buffer1, Queue<Integer> buffer2){
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
		this.buffer2 = buffer2;
		this.emptyCount1 = emptyCount1;
		this.fillCount1 = fillCount1;
		
		
		this.buffer1 = buffer1;
		this.emptyCount2 = emptyCount2;
		this.fillCount2 = fillCount2;
			
		
		
	}

	
	
	@Override
	public void run() {
		int item;
		while(true){
			try {
				System.out.println(Thread.currentThread().getName() + " Producer turn " + ProducerConsumer.cturn);
				
				if(ProducerConsumer.cturn % 2 == 0){
					//System.out.println(Thread.currentThread().getName() + " Consumer ID " + this.id);
					
					if(fillCount2.availablePermits() == 0)
						System.out.println(Thread.currentThread().getName() + " is waiting");
					
					fillCount2.acquire();
					mutex2.acquire();
					
					/*push from buffer*/
					
					item = buffer2.poll();
					ProducerConsumer.cturn++;
					System.out.println(Thread.currentThread().getName() + " consumed " + item + " form Buffer2");
					
					mutex2.release();
					emptyCount2.release();
					
					
				}
				else {
					//System.out.println(Thread.currentThread().getName() + " Consumer ID " + this.id);
					
					if(fillCount1.availablePermits() == 0)
						System.out.println(Thread.currentThread().getName() + " is waiting");
					
					fillCount1.acquire();
					mutex1.acquire();
					
					/*push from buffer*/
					
					item = buffer1.poll();
					ProducerConsumer.cturn++;
					System.out.println(Thread.currentThread().getName() + " consumed " + item + " form Buffer1");
					
					mutex1.release();
					emptyCount1.release();
					
					
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
