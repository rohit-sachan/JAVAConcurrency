
package pcjvm.prime;

public class SequentialPrimeFinder extends AbstractPrimeFinder {
  public int countPrimes(final int number) {
    return countPrimesInRange(1, number);
  }
  
  public static void main(final String[] args) { 
    new SequentialPrimeFinder().timeAndCompute(100000);
  }
}
