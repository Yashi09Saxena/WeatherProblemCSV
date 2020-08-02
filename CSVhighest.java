
/**
 * Write a description of CSVhighest here.
 * This code's function testHottestInManyDays() is use to find hottest day with hour in a year.
 * @author (Yashi Saxena) 
 * @version (31/07/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVhighest {
    
  public CSVRecord commonForTwo(CSVRecord currentRow, CSVRecord largestSoFar)//max temperture refactored
  {
     if(largestSoFar==null)
     {
         largestSoFar= currentRow;
     }
     else
     {
         double currentTemp= Double.parseDouble(currentRow.get("TemperatureF"));
         double largestTemp= Double.parseDouble(largestSoFar.get("TemperatureF"));
         if(currentTemp>largestTemp)
         {
             largestSoFar = currentRow;
         }
     }
     return largestSoFar;
  }

  public CSVRecord hottestHourinFile(CSVParser parser)
  {
      CSVRecord largestSoFar = null;
      for(CSVRecord currentRow: parser)
      {
          largestSoFar= commonForTwo(currentRow,largestSoFar);
      }
      return largestSoFar;
  }
    //The below function test Hottest Day in file with time.
  public void testHottestInDay()
  {
    String relativefile  = "C://Users//Admin//Desktop//Yashi saxena prog//WeatherData//nc_weather//2015//weather-2015-01-02.csv";
    FileResource  fr = new FileResource(relativefile);
    CSVRecord largest = hottestHourinFile(fr.getCSVParser());
    System.out.println(" Hottest temperature was " +largest.get("TemperatureF") +" on " +largest.get("DateUTC"));
  }
  
  public CSVRecord hottestInManyDays()
  {
      CSVRecord largestSoFar = null;
      DirectoryResource dr = new DirectoryResource();
      for(File f:dr.selectedFiles())
      {
          FileResource fr = new FileResource(f);
          CSVRecord currentRow= hottestHourinFile(fr.getCSVParser());
          largestSoFar= commonForTwo(currentRow,largestSoFar);
      }
     return largestSoFar;
  }
  //This code's function testHottestInManyDays() is use to find hottest day with hour in a year.
  public void testHottestInManyDays()
  {
    CSVRecord largest = hottestInManyDays();
    System.out.println(" Hottest temperature was " +largest.get("TemperatureF") +" on " +largest.get("DateUTC"));
  }
}
