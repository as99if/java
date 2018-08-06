package reader_writer;
/*Asif Ahmed*/
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Writer implements Runnable{
	
	Semaphore mutexReader = null;
	Semaphore mutex = null;
	
	Queue<Integer> buffer = null;
	Random rand=new Random();
	
	public Writer(Semaphore mutexReader, Semaphore mutex, Queue<Integer> buffer){
		this.mutexReader = mutexReader;
		this.mutex = mutex;
		this.buffer = buffer;
	}
	
	public void run(){
		int item;
		while(true){
			try{
				item = rand.nextInt(50) + 1;
				if(mutex.availablePermits() == 0)
					System.out.println(Thread.currentThread().getName() + " is waiting");
				
				mutex.acquire();			//down
				/*write*/
				buffer.add(item);
				System.out.println(Thread.currentThread().getName() + " has wrote " + item);
				
				/*end writing*/
				mutex.release();			//up
				
				
				Thread.sleep(rand.nextInt(10) + 1);
				
				if(buffer.size() >= 100)
					break;
			}catch(InterruptedException ex){
				Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	
}
