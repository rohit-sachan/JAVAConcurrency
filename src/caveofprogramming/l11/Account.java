/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caveofprogramming.l11;

/**
 *
 * @author rohit
 */
public class Account {
    
    private int balance = 10000;
    
    public void deposit(int amount){
        balance = balance + amount;
    }
    
    public void withdraw(int amount){
        balance = balance - amount;
    }
    
    public int getbalance(){
        return  balance;
    }
    
    public static void transfer(Account ac1, Account ac2, int balance){
        ac1.withdraw(balance);
        ac2.deposit(balance);
    }
}
