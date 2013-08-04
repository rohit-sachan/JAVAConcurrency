/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class App {
    
    public static void main(String[] args) {
        
        Connection.getInstance().Connect();
        
        ExecutorService es = Executors.newCachedThreadPool();
        
        for (int i = 0; i < 100; i++) {
            es.submit(new Runnable() {

                @Override
                public void run() {
                    Connection.getInstance().Connect();
                }
            });
        }
        
        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
