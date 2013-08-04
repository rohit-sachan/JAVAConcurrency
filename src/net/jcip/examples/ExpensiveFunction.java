/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jcip.examples;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rohit
 */
public class ExpensiveFunction
        implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
        try {
            // after deep thought...
            System.out.println("Computing " + arg);    
            Thread.sleep(1000);
              
        } catch (InterruptedException ex) {
            Logger.getLogger(ExpensiveFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new BigInteger(arg);
    }
}
