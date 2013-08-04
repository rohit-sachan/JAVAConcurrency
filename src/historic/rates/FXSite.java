package historic.rates;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FileUtils;

public class FXSite {
    public static String datadir = "C:\\q\\data\\";
    /**
     * http://www.forexite.com/free_forex_quotes/2012/12/151212.zip
     * @param yyyy
     * @param mm
     * @param dd
     * @throws IOException 
     */
    
    
    public static void getPrice(String yyyy, String mm, String dd) throws IOException {
      
    final URL url = 
      new URL("http://www.forexite.com/free_forex_quotes/" + yyyy+ "/"+mm + "/"+ dd+mm+yyyy.substring(2, 4)+ ".zip");
        
    File file = new File(datadir+dd+mm+yyyy.substring(2, 4)+ ".zip");
    if(file.exists()){
        file.delete();
    }
    org.apache.commons.io.FileUtils.copyURLToFile(url, file);
    
    System.out.println("Downloading for " + url + " to File :" +  file.getAbsolutePath());
    FileInputStream fis = new FileInputStream(file);
    File foutxt = new File(datadir+dd+mm+yyyy.substring(2, 4)+ ".txt");
    if(foutxt.exists()){
       foutxt.delete(); 
    }
    FileOutputStream fos = new FileOutputStream(foutxt);
    
    BufferedOutputStream bos = new BufferedOutputStream(fos ,1000);
    
    ZipInputStream zin  = new ZipInputStream(fis);
    ZipEntry entry = null;
    while((entry = zin.getNextEntry()) != null){
    int count = 0;
    byte data[] = new byte[5000];
    while ((count = zin.read(data,0,5000)) != -1)
     {
      bos.write(data,0,count);
     }
    }
    zin.close();
    fis.close();
    System.out.println("Deleting " + file);
    org.apache.commons.io.FileUtils.deleteQuietly(file);
  }

  static void createFile(String yyyy, String mm, String dd, String ccyp, BufferedWriter writer) throws FileNotFoundException, IOException {
        File fin = new File(datadir+dd+mm+yyyy.substring(2, 4)+ ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(fin));
        String line = null;
        int i = 1;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\,");
            if(i ==1 ) {
                ++i;
                continue;
            }
            if(!parts[0].equals(ccyp)){continue;}
            try{
            StringBuffer ntfmtStr = new StringBuffer();
            ntfmtStr.append(parts[1]).append(" ").append(parts[2]).append(";");
            ntfmtStr.append(parts[3]).append(";");
            ntfmtStr.append(parts[4]).append(";");
            ntfmtStr.append(parts[5]).append(";");
            ntfmtStr.append(parts[6]).append(";");
            ntfmtStr.append("0");
            writer.write(ntfmtStr.toString());
            writer.newLine();
            }catch(Exception e){
                System.out.println("Error in reading file " + fin.getAbsolutePath() + " for line " + line + " exception");
                System.out.println(e.getMessage());
            }
        }
    }
}
