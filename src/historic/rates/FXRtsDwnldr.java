
package historic.rates;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pcjvm.nav.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.apache.commons.lang3.time.DateUtils;

public class FXRtsDwnldr {
  final int poolSize;
  public static final int  lastDays = 30;
  // specific to Ninja trader settings
  String [] ccypairs = {"EURUSD",
"GBPUSD",
"USDCHF",
"USDJPY",
"EURGBP",
"EURCHF",
"EURJPY",
"GBPCHF",
"GBPJPY",
"CHFJPY",
"USDCAD",
"EURCAD",
"AUDUSD",
"AUDJPY",
"NZDUSD",
"NZDJPY",
"XAUUSD",
"XAGUSD"
};
  String priceType = "Last";
          
//  Calendar 
  
  public FXRtsDwnldr(int thePoolSize) {
    poolSize = thePoolSize;  
  }
  
  
  public void downloadRates() throws IOException{
      Date today = Calendar.getInstance().getTime();
      for(int i= 1 ; i < lastDays ; i++){
          Date fetchDate = DateUtils.addDays(today, -i);
          Calendar cal =   Calendar.getInstance(); 
          cal.setTime(fetchDate);
          String dayOfMonth  = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
          if(dayOfMonth.length()==1){
              dayOfMonth = "0"+dayOfMonth;
          }
          String month = Integer.toString(cal.get(Calendar.MONTH)+1);
          if(month.length()==1){
              month = "0"+month;
          }
          String year = Integer.toString(cal.get(Calendar.YEAR));
          FXSite.getPrice(year,month,dayOfMonth);
      }
      System.out.println("All rated downloaded till date today - " + lastDays + " days");  
      
      consolidateFiles();
  }
 
  public static void main(final String[] args) throws Exception {
    FXRtsDwnldr rtsDwndldr = new FXRtsDwnldr(5) ;
    rtsDwndldr.downloadRates();
    
  }

    private void consolidateFiles()  {
        Date today = Calendar.getInstance().getTime();
        for(String ccyp :  ccypairs){
        File ccyfile = new File(FXSite.datadir+"$"+ccyp+"."+priceType+"."+"txt");
        System.out.println("Creating/Replacing file " + ccyfile.getAbsolutePath());  
        if(ccyfile.exists()){
            ccyfile.delete();
        }
        BufferedWriter writer = null;
            try {
                writer = new BufferedWriter( new FileWriter( ccyfile));
            } catch (IOException ex) {
                Logger.getLogger(FXRtsDwnldr.class.getName()).log(Level.SEVERE, null, ex);
                
            }
          for(int i= 0 ; i <= lastDays ; i++){
              Date fetchDate = DateUtils.addDays(today, -lastDays+i);
              Calendar cal =   Calendar.getInstance(); 
              cal.setTime(fetchDate);
              String dayOfMonth  = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
              if(dayOfMonth.length()==1){
                  dayOfMonth = "0"+dayOfMonth;
              }
              String month = Integer.toString(cal.get(Calendar.MONTH)+1);
              if(month.length()==1){
                  month = "0"+month;
              }
              String year = Integer.toString(cal.get(Calendar.YEAR));
                try {
                    FXSite.createFile(year,month,dayOfMonth,ccyp,writer);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXRtsDwnldr.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FXRtsDwnldr.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FXRtsDwnldr.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      System.out.println("All rated consolidated till date today - " + lastDays + " days");  
    }
}
