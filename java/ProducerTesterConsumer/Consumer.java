package ptc;

import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable{

    Semaphore mutex1 = null;
    Semaphore empty1 = null;
    Semaphore full1 = null;
    Queue<Integer> testedbuffer=null;
    Random rand=new Random();
    
    public Consumer(Semaphore mutex,Semaphore empty,Semaphore full,Queue<Integer> testedbuffer)
    {
        this.mutex1=mutex;
        this.empty1=empty;
        this.full1=full;
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
              full1.down();
              mutex1.down();
              item=testedbuffer.remove();
              System.out.println(Thread.currentThread().getName()+" consumed: " + item);
              mutex1.up();
              empty1.up();
              Thread.sleep(rand.nextInt(10) + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
        }        
    }
    
}
