/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import net.jcip.examples.ExpensiveFunction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rohit
 */
public class FutureTaskDemo {
    
    public FutureTaskDemo() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
     public class MyComputation implements Callable<Double> {
         
        @Override
        public Double call() throws Exception {
            Thread.sleep(1000);
            return 2500d;
        }
        
    }
    
    @Test
    public void  TestM1(){
        Callable<Double> myComputation = new MyComputation();
        FutureTask<Double>  futureTask = new FutureTask<Double>(myComputation);
        
    
    }
    
}
