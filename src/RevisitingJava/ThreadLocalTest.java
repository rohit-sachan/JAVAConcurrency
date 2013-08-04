/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RevisitingJava;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author
 */
final class ThreadLocalTest {

    public static void main(String args[]) throws IOException {
      
        String str = new String ("ABBcBBA");
        char[] chararray = str.toCharArray();
        System.out.println(istPalindrom(chararray));
    }
    
    
    public static boolean istPalindrom(char[] word){
    int i = 0;
    int j = word.length - 1;
    while (j > i) {
        if (word[i] != word[j]) {
            return false;
        }
        ++i;
        --j;
    }
    return true;
}
    
    /*
     * Thread safe format method because every thread will use its own DateFormat
     */
    public static String threadSafeFormat(Date date){
        DateFormat formatter = PerThreadFormatter.getDateFormatter();
        return formatter.format(date);
    }
    
}


/*
 * Thread Safe implementation of SimpleDateFormat
 * Each Thread will get its own instance of SimpleDateFormat which will not be shared between other threads. *
 */
class PerThreadFormatter {

    private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>() {

        /*
         * initialValue() is called
         */
        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println("Creating SimpleDateFormat for Thread : " + Thread.currentThread().getName());
            return new SimpleDateFormat("dd/MM/yyyy");
        }
    };

    /*
     * Every time there is a call for DateFormat, ThreadLocal will return calling
     * Thread's copy of SimpleDateFormat
     */
    public static DateFormat getDateFormatter() {
        return dateFormatHolder.get();
    }
}

class Task extends Thread {
    
    @Override
    public void run() {
        for(int i=0; i<2; i++){
            System.out.println("Thread: " + Thread.currentThread().getName() + " Formatted Date: " + ThreadLocalTest.threadSafeFormat(new Date()) );
        }       
    }
}

