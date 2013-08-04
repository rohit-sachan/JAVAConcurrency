/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l10;

/**
 *
 * @author rohit
 */
public class App {
    
    public static void main(String[] args) {
        final Runner r = new Runner();
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    r.firstThread();
                }catch(InterruptedException e){
                    
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    r.secondThread();
                }catch(InterruptedException e){
                    
                }
            }
        });
        
        
        
        Thread t3 = new Thread(new Runnable(){
            @Override
            public void run() {
                    r.finalThread();
            }
        });
        
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            
        }
        
        r.finalThread();
        
        
        
    }
    
}
