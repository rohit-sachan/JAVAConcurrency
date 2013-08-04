/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l8;

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
    
    
    public static void main(String[] args) {
        final Processor pr = new Processor();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    pr.producer();
                }catch(InterruptedException e){
                    
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    pr.producer2();
                }catch(InterruptedException e){
                    
                }
            }
        });
        
        
        Thread t3 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    pr.consumer();
                }catch(InterruptedException e){
                    
                }
            }
        });
        
        
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
