/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l12;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class Connection {
    
    private static Connection instance = new Connection();
    
    private int count = 0;

    private Semaphore sem = new Semaphore(10, true);
    
    private Connection() {
    }
    
    
    public static Connection getInstance(){
        return instance;
    }
    
    public void Connect(){
        try {
            sem.acquire();
            DoConnect();
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            sem.release();
        }
        
    }
    
    public void DoConnect(){
        
        
        synchronized(this){
            count++;
            System.out.println("Current Connections " + count );
        }
        
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        
        synchronized(this){
            count--;
        }
        
    }
    
}
