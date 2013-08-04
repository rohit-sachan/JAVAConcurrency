/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jcip.examples;

/**
 *
 * @author rohit
 */
public interface Computable <A,V> {
    V compute (A arg)throws InterruptedException;
}
