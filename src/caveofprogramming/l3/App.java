/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class App {
    
//    AtomicInteger count  = new AtomicInteger(0);
    volatile  int count;
    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }
    
    public void doWork(){
        
        Thread t1 =  new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
//                    count.getAndIncrement();
                    count++ ;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        Thread t2 =  new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
//                    count.getAndIncrement();
                    count++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        System.out.println("Count equal : " +  count);
    }
}
