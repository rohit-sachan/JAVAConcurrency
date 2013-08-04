/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class Runner {
    
    int count = 0;
    private void increment(){
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(5);
                count++;
            } catch (InterruptedException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Lock lock = new ReentrantLock();
    
    private Condition cond  = lock.newCondition();
    
    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        cond.await();
        System.out.println("Woken up !");
        try {
            increment();
        } finally {
            lock.unlock();
        }
        
    }
    
    public void secondThread() throws InterruptedException {
        Thread.sleep(100);
        lock.lock();
        System.out.println("Press Return");
        new Scanner(System.in).nextLine();
        System.out.println("Got Return Key");
        cond.signal();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }
    
    public void finalThread() {
        System.out.println("Count  : "  + count);
    }
    
}
