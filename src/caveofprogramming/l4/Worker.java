/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class Worker {
    
    private Random random = new Random();
    
    Object lock1  = new Object();
    Object lock2  = new Object();
    
    private List<Integer> list1 = new ArrayList<Integer> ();
    private List<Integer> list2 = new ArrayList<Integer> ();
    
    public void stage1(){
        synchronized(lock1){
            try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
            list1.add(random.nextInt());
        }
        
        
    }
    
    public void stage2(){
        synchronized(lock2){
            try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
            list2.add(random.nextInt());
            
        }
        
        
        
    }
    
    public void  process(){
        for (int i = 0; i < 1000; i++) {
            stage1();
            stage2();
        }
    }
    
    public void main() {
        System.out.println("Starting ...");
        
        long starttime = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                process();
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                process();
            }
        });
        
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        long endtime = System.currentTimeMillis();
        
        System.out.println("total time taken " + (endtime-starttime));
        
        System.out.println("List 1 size :" + list1.size() +  ", List 2 size :" + list2.size() );
        
        
        
    }
    
}
