/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

/**
 *
 * @author Kamrul
 */
public class Semaphore {
    
  private int initialValue = 0;
  private int bound = 0;

  public Semaphore(int initialValue,int upperBound){
    this.bound = upperBound;						// limit
    this.initialValue = initialValue;
  }

  public synchronized void up() throws InterruptedException{
    while(this.initialValue == bound){
        System.out.println(Thread.currentThread().getName()+" is waiting:");
        wait();
    }
    this.initialValue++;
    this.notify();
  }

  public synchronized void down() throws InterruptedException{
    while(this.initialValue == 0){
         System.out.println(Thread.currentThread().getName()+" is waiting:");
         wait();
    }
    this.initialValue--;
    this.notify();
  }
}
