package ptc;

import java.util.LinkedList;
import java.util.Queue;

public class PTCMainClass
{
	static int N =5;
    public static Semaphore mutex =new Semaphore(1,1);
    public static Semaphore mutex1 =new Semaphore(1,1);
    public static Semaphore empty =new Semaphore(N,N);
    public static Semaphore empty1 =new Semaphore(N,N);
    public static Semaphore full =new Semaphore(0,N);
    public static Semaphore full1 =new Semaphore(0,N);
    public static Queue<Integer> buffer = new LinkedList<Integer>();
    public static Queue<Integer> testedbuffer = new LinkedList<Integer>();


    public static void main(String[] args)
    {
    	Producer producer1=new Producer(mutex,empty,full,buffer);
        Producer producer2=new Producer(mutex,empty,full,buffer);
   
        Consumer consumer1=new Consumer(mutex1,empty1,full1,testedbuffer);
        Consumer consumer2=new Consumer(mutex1,empty1,full1,testedbuffer);
        
        Tester tester1=new Tester(mutex,empty,full,buffer,mutex1,empty1,full1,testedbuffer);
        Tester tester2=new Tester(mutex,empty,full,buffer,mutex1,empty1,full1,testedbuffer);
   
   
        new Thread(producer1,"Producer 1").start();
        
        new Thread(tester1,"Tester 1").start();
   
        new Thread(consumer1,"Consumer 1").start();
   
        new Thread(producer2,"Producer 2").start();
        
        new Thread(tester2,"Tester 2").start();
          
        new Thread(consumer2,"Consumer 2").start();
   }
}
