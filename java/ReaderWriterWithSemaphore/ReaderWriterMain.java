package reader_writer;
/*Asif Ahmed*/
import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;


public class ReaderWriterMain {

	public static int readerCount = 0;
	
	public static Semaphore mutexReader = new Semaphore(1);			
	public static Semaphore mutex = new Semaphore(1);			
	
	public static Queue<Integer> buffer = new LinkedList<Integer>();
	
	 public static void main(String[] args) {
	       
	        Writer writer1=new Writer(mutexReader, mutex, buffer);
	        Writer writer2=new Writer(mutexReader, mutex, buffer);
	        
	        Reader reader1=new Reader(mutexReader,mutex, buffer);
	        Reader reader2=new Reader(mutexReader, mutex, buffer);
	        Reader reader3=new Reader(mutexReader, mutex, buffer);
	        Reader reader4=new Reader(mutexReader, mutex, buffer);
	        
	        new Thread(writer1,"Writer 1").start();
	        
	        new Thread(reader1,"Reader 1").start();
	        
	        new Thread(writer2,"Writer 2").start();
	               
	        new Thread(reader2,"Reader 2").start();
	        	        
	        new Thread(reader3,"Reader 3").start();     

	        new Thread(reader4,"Reader 4").start();     
	        
	    }
	
}
