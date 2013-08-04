/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RevisitingJava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */

public class FutureTaskExample {
    public static void main(String[] args) {
        MyCallable callable1 = new MyCallable(10000);
        MyCallable callable2 = new MyCallable(20000);
        
        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        while (true) {
            try {
            if(futureTask1.isDone() && futureTask2.isDone()){
                System.out.println("Done");
                //shut down executor service
                executor.shutdown();
                return;
            }
            if(!futureTask1.isDone()){
                    //wait indefinitely for future task to complete
                    System.out.println("FutureTask1 output="+futureTask1.get());
                }
            System.out.println("Waiting for FutureTask2 to complete");
            String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
            if(s !=null){
                System.out.println("FutureTask2 output="+s);
            }
        
            } catch (InterruptedException ex) {
                    Logger.getLogger(FutureTaskExample.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(FutureTaskExample.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TimeoutException ex) {
                    Logger.getLogger(FutureTaskExample.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
    }

}
