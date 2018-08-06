package ptc;

import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester implements Runnable
{
	Semaphore mutex = null;
	Semaphore mutex1 = null;
    Semaphore empty = null;
    Semaphore empty1 = null;
    Semaphore full = null;
    Semaphore full1 = null;
    Queue<Integer> buffer=null;
    Queue<Integer> testedbuffer=null;
    Random rand = new Random();
    
    public Tester(Semaphore mutex,Semaphore empty,Semaphore full,Queue<Integer> buffer,Semaphore mutex1,Semaphore empty1,Semaphore full1,Queue<Integer> testedbuffer)
    {
        this.mutex=mutex;
        this.mutex1=mutex1;
        this.empty=empty;
        this.empty1=empty1;
        this.full=full;
        this.full1=full1;
        this.buffer=buffer;
        this.testedbuffer=testedbuffer;
    }

	@Override
	public void run() 
	{
		while(true)
        {
            int item;
            try
            {
              full.down();
              mutex.down();
              item=buffer.remove();
              System.out.println(Thread.currentThread().getName()+" testing....: " + item);
              mutex.up();
              empty.up();
              
              empty1.down();
              mutex1.down();
              testedbuffer.add(item);
              System.out.println(Thread.currentThread().getName()+" tested: " + item);
              mutex1.up();
              full1.up();
              Thread.sleep(rand.nextInt(10) + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
        } 
	}
}
