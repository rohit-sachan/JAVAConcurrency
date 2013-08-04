/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RevisitingJava;

/**
 *
 * @author rohit
 */
import java.util.concurrent.Callable;
 
public class MyCallable implements Callable<String> {
 
    private long waitTime;
     
    public MyCallable(int timeInMillis){
        this.waitTime=timeInMillis;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }
 
}
