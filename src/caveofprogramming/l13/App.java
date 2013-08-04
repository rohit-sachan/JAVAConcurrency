/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l13;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class App {
    
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        Future<Integer> f =  ex.submit(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
//                Random r = new Random(100);
                int d = 10000;
                System.out.println("Start...");
                Thread.sleep(d);
                System.out.println("Done");
                return d;
            }
        });
        
        ex.shutdown();
        try {
            ex.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("Result is " + f.get());
        } catch (InterruptedException ex1) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (ExecutionException ex1) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex1);
        }
        
    }   
    
    
}
