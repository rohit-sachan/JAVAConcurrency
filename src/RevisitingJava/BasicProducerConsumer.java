/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RevisitingJava;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class BasicProducerConsumer {
    
//    Lock lock = new ReentrantLock();
    Object lock =  new Object();
    LinkedList<Integer> list = new LinkedList<Integer>();
    Producer producer = new Producer();
    Consumer consumer = new Consumer();
    
    public class Producer implements Runnable{

        @Override
        public void run() {
            int i=0;
            while(true){
                synchronized(lock){
                    try {
                        while(list.size() == 10){
                            lock.wait();
                        }
                        list.add(++i);
                        System.out.println("Producing i : " + i);
                        lock.notify();
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BasicProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    
    public class Consumer implements Runnable {

        @Override
        public void run() {
            while(true){
                synchronized(lock){
                System.out.println("Waiting to receive...");
                try {
                        while(list.size() == 0){
                            lock.wait();
                        }
                        int i = list.removeFirst();
                        System.out.println("Received ... " + i);
                        lock.notify();
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BasicProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        BasicProducerConsumer system = new BasicProducerConsumer();
        Thread t1 = new Thread(system.producer);
        Thread t2 = new Thread(system.consumer);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BasicProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
