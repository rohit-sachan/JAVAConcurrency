/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l5;

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
    
    private int id ;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        
        System.out.println("Starting ... " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Completing ... " + id);
        
    }
    
}


public class App {
    
    public static void main(String[] args) {
        
        ExecutorService exec =  Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 10; i++) {
            exec.submit(new Processor(i));
        }
        
        exec.shutdown();
        
        System.out.println("All tasks submitted");
        try {
            exec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
}
