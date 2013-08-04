/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author rohit
 */


public class App {
    
    static BlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(10);
    
    public static void main(String[] args) {
        
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    producer();
                }catch(InterruptedException e){
                    
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    consumer();
                }catch(InterruptedException e){
                    
                }
            }
        });
        
        
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void producer () throws InterruptedException{
        Random random  = new Random();
        while(true){
            try {
                q.put(random.nextInt(100));
//                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void consumer () throws InterruptedException{
        Random random  = new Random();
        while(true){
            try {
                Thread.sleep(100);
                if(random.nextInt(100)==0){
                    Integer value = q.take();
                    System.out.println("Taken " + value + " queue size is :" + q.size());
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
