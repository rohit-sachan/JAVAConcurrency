/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l2;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */

class Processor extends Thread{
    
    boolean running = true;
    
    public void run(){
        while(running){
            System.out.println("Hello !");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void shutdown(){
        running = false;
    }
    
}
public class App {
    
    public static void main(String[] args) {
        
        Processor proc1 = new Processor();
        proc1.start();
        
        System.out.println("Press Enter to stop");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        
        proc1.shutdown();
    }
    
}
