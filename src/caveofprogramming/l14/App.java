/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l14;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class App {
    
    public static void main(String[] args) {
        System.out.println("Starting ...");
        
        Thread t1  = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Random r = new Random();
                    Thread.sleep(1000);
                    for (double i = 0; i < 1E9; i++) {
                        try {
                            Thread.sleep(1);
                            if(Thread.currentThread().isInterrupted()){
                                System.out.println("Inturpted !");
                                break;
                            }
                            Math.sin(i);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        t1.start();
        t1.interrupt();
        try {
            t1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finished.");
    }
    
}
