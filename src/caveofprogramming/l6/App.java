/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l6;

import caveofprogramming.l5.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author rohit
 */

class Processor implements Runnable{
    
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        
        this.latch = latch;
    
    }

    @Override
    public void run() {
        try {
            System.out.println("Started ...!");
            
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        latch.countDown();
    }
    
}


public class App {
    
    public static void main(String[] args) {
        
        ExecutorService exec =  Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            exec.submit(new Processor(latch));
        }
        exec.shutdown();
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("All tasks submitted");
        
     
    }
    
}
