package producer_consumer_with_semaphore;
/*Asif Ahmed*/
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {
	
	Semaphore mutex1 = null;

	Semaphore mutex2 = null;
	Semaphore fillCount1 = null;
	Semaphore emptyCount1 = null;
	Semaphore fillCount2 = null;
	Semaphore emptyCount2 = null;
		
	
	Queue<Integer> buffer1 = null;
	Queue<Integer> buffer2 = null;
	
	
	Random rand = new Random();
	
	
	public Producer(Semaphore mutex1, Semaphore mutex2, Semaphore emptyCount1, Semaphore emptyCount2, 
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
	
	private static int item;
	@Override
	public void run() {
		while(true){
			try {
				System.out.println(Thread.currentThread().getName() + " Producer turn " + ProducerConsumer.pturn);
				if(ProducerConsumer.pturn % 2 == 0){
									
					item = rand.nextInt(20)+1;
					
					if(emptyCount2.availablePermits() == 0)
						System.out.println(Thread.currentThread().getName() + " is waiting");
					
					emptyCount2.acquire();
					mutex2.acquire();
					
					/*put into buffer*/
					
					
					buffer2.add(item);
					ProducerConsumer.pturn++;
					System.out.println(Thread.currentThread().getName() + " produced " + item + " in Buffer2");
						
					
					mutex2.release();
					fillCount2.release();
					
					
				}
				else {
					
					item = rand.nextInt(20)+1;
					//System.out.println(Thread.currentThread().getName() + " Producer ID " + this.id);
					if(emptyCount1.availablePermits() == 0)
						System.out.println(Thread.currentThread().getName() + " is waiting");
					
					emptyCount1.acquire();
					mutex1.acquire();
					
					/*put into buffer*/
					
					
					buffer1.add(item);
					ProducerConsumer.pturn++;
					System.out.println(Thread.currentThread().getName() + " produced " + item + " in Buffer1");
						
					
					mutex1.release();
					fillCount1.release();
					
					
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
