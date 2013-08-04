/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RevisitingJava;

/**
 *
 * @author rohit
 */
public class CallBackExample {

    public static void main(String[] args) {
        
        CallBackExample clbk = new CallBackExample();
        clbk.dostuff(new CallBackI() {
            @Override
            public String callingBack(String str) {
                System.out.println("Hello :" + str);
                return "CallBack Called for " +str;
            }
        }, "Rohit");
    }
    
    
    public void dostuff(CallBackI f , String str){
        System.out.println("doStuff Got Arg : " + str);
        f.callingBack(str);
    }
    
}

interface CallBackI{
    String callingBack(String str);
}


