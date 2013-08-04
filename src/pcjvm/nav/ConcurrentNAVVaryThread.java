
package pcjvm.nav;

import java.io.BufferedReader;
import java.io.FileReader;
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

public class ConcurrentNAVVaryThread extends AbstractNAV {
  final int poolSize;
  
  public ConcurrentNAVVaryThread(int thePoolSize) {
    poolSize = thePoolSize;  
  }
  
  public double computeNetAssetValue(final Map<String, Integer> stocks) 
      throws ExecutionException, InterruptedException {
      
    List<Callable<Double>> partitions = new ArrayList<Callable<Double>>();
    for(String ticker : stocks.keySet()) {
      final String theTickerSymbol = ticker;
      partitions.add(new Callable<Double>() {
        public Double call() throws Exception {
          return stocks.get(theTickerSymbol).intValue() * 
            YahooFinance.getPrice(theTickerSymbol);
        }        
      });
    }
    
    
    ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);    
    List<Future<Double>> valueOfStocks = executorPool.invokeAll(
      partitions, 10000, TimeUnit.SECONDS);
    executorPool.shutdown();
      
    double netAssetValue = 0.0; 
    for(Future<Double> valueOfAStock : valueOfStocks) { 
      netAssetValue += valueOfAStock.get(); 
    }
    return netAssetValue;   
  } 

  public void timeAndComputeValue() 
    throws ExecutionException, IOException, InterruptedException { 
    final long start = System.nanoTime();
    
    Map<String, Integer> stocks = readTickers();
    double nav = computeNetAssetValue(stocks);    
    
    final long end = System.nanoTime();

    System.out.println(poolSize + " " + (end - start)/1.0e9);
    
    final String value = new DecimalFormat("INR ##,##0.00").format(nav);
    System.out.println("Your net asset value is " + value);
  }

  public static void main(final String[] args) throws Exception {
    new ConcurrentNAVVaryThread(5).timeAndComputeValue();
  }
}
