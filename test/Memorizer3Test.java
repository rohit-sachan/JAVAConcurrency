/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.Callable;
import net.jcip.examples.Memoizer3;
import java.math.BigInteger;
import net.jcip.examples.ExpensiveFunction;
import net.jcip.examples.Computable;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
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
public class Memorizer3Test {
    
    public Memorizer3Test() {
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
    
    public final ArrayList<String> args = new ArrayList();
    @Test
    public void test1(){
        args.add("ABC");
        args.add("DEF");
        args.add("XYZ");
        
        ExecutorService e = Executors.newFixedThreadPool(args.size());
        final Computable stringToNumber = new ExpensiveFunction();
        final Memoizer3 <String,BigInteger> cache3 = new Memoizer3(stringToNumber);
        
        for(final String str:  args) {
            
            e.submit(new Callable<BigInteger>(){
                @Override
                public BigInteger call() throws Exception {
                    return (BigInteger) cache3.compute(str);
                }
            });
            
        }
        
        
    }
}
