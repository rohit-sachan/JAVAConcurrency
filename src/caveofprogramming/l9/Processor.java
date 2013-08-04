/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l9;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author rohit
 */
public class Processor {
    
    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int limit = 10;
    private Object lock =  new Object();
    
    public  void producer () throws InterruptedException{
        int value = 0;
        while (true){
            synchronized(lock){
                while(list.size() == limit){
                    lock.wait();
                }
                list.add(value++);
                System.out.println("Putting value: " + value);
                lock.notify();
            }
        }
    }
    
    public void consumer () throws InterruptedException{
        
    while (true){
            synchronized(lock){
                while(list.size() == 0){
                    lock.wait();
                }
            System.out.println("List Size is : " + list.size());
            int i = list.removeFirst();
            System.out.println("top value is : " + i);
            lock.notify();
            }
            Thread.sleep(1000);
        }    
    }
}
