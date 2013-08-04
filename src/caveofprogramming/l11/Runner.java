/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l11;

import java.util.Random;
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
    
    Account ac1 = new Account();
    Account ac2 = new Account();
    
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    
//    private Condition cond  = lock.newCondition();
    
    private void aquireLock(Lock l1, Lock l2){
        while(true){
            boolean firstLock = false;
            boolean secondLock = false;
            try {
                firstLock = l1.tryLock();
                secondLock = l2.tryLock();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if(firstLock && secondLock){
                    return;
                }
                if(firstLock){
                    l1.unlock();
                }
                if(secondLock){
                    l2.unlock();
                }
            }
        }
    }
    
    public void firstThread() throws InterruptedException {
        
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            try{
            aquireLock(lock1, lock2);
            Account.transfer(ac1, ac2, ran.nextInt());
            }finally{
                lock1.unlock();
                lock2.unlock();
            }
            
        }
        
    }
    
    public void secondThread() throws InterruptedException {
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            try{
                aquireLock(lock2, lock1);
            Account.transfer(ac2, ac1, ran.nextInt());
            }finally{
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    
    public void finalThread() {
        System.out.println("Account 1 balance : " + ac1.getbalance());
        System.out.println("Account 2 balance : " + ac2.getbalance());
        System.out.println("Total balance : " + (ac1.getbalance() + ac2.getbalance()));
    }
    
}
