package reader_writer;
/*Asif Ahmed*/
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


public class Reader implements Runnable {
	
	Semaphore mutexReader = null;
	Semaphore mutex = null;
	Queue<Integer> buffer = null;
	Random rand=new Random();
	
	public Reader(Semaphore mutexReader, Semaphore mutex, Queue<Integer> buffer){
		this.mutexReader = mutexReader;
		this.mutex = mutex;
		this.buffer = buffer;
	}
	
	public void run(){
		int item;
		while(true){
			try {
				/*mutex.acquire();
				ReaderWriterMain.readerCount++;
				if(ReaderWriterMain.readerCount == 1)
					mutexReader.acquire();
				mutex.release();
				*/
				/*if (mutexReader.availablePermits() == 0)
					System.out.println(Thread.currentThread().getName() + "is waiting");*/

				if(mutex.availablePermits() == 0)
					System.out.println(Thread.currentThread().getName() + " is waiting");
				mutexReader.acquire();						// permits reader	
				
				if(ReaderWriterMain.readerCount == 0)		// no reader
					mutex.acquire();					//  permits writer   
				
				ReaderWriterMain.readerCount++;
				mutex.release(); 					// up
				/*read*/
				item = buffer.size();
				System.out.println(Thread.currentThread().getName() + " reads and buffer size " + item);
				/*end of read*/
				mutex.acquire();					//down
				ReaderWriterMain.readerCount--;
				
				if(ReaderWriterMain.readerCount == 0)
					mutex.release();
				
				mutexReader.release();
				
				/*
				mutex.acquire();
				ReaderWriterMain.readerCount--;
				if(ReaderWriterMain.readerCount == 0)
					mutexReader.release();
				mutex.release();
				*/
				Thread.sleep(rand.nextInt(10)+1);
				

				if(buffer.size() >= 100)
					break;
				
			} catch (InterruptedException ex) {
					// TODO: handle exception
				Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	
	
}
