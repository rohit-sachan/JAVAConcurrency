/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l8;

import java.util.Scanner;

/**
 *
 * @author rohit
 */
public class Processor {
    
    public  void producer () throws InterruptedException{
        synchronized(this){
            System.out.println("Producer thread running");
            wait();
            System.out.println("Producer resumed");
            
        }
    }
    
    public  void producer2 () throws InterruptedException{
        synchronized(this){
            System.out.println("Producer2 thread running");
            wait();
            System.out.println("Producer2 resumed");
            
        }
    }
    
    public void consumer () throws InterruptedException{
        
//        Scanner scan = new Scanner(System.in);
        Thread.sleep(100);
        synchronized(this){
        System.out.println("Waiting for Return Key");
//        scan.nextLine();
        System.out.println("Return pressed");
        notify();
        Thread.sleep(200);
        }
    
    }
}
