
/**
 * Write a description of CSVColdest here.
 * This code's function testColdestInManyDays() is use to find coldest day with hour in a year.
 * @author (Yashi Saxena) 
 * @version (31/07/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.io.File.*;
public class CSVColdest {
 
    public CSVRecord commonForTwo(CSVRecord currentRow, CSVRecord lowestSoFar)//;low temperture refactored
  {
     if(lowestSoFar==null)
     {
         lowestSoFar= currentRow;
     }
     else
     {
         double currentTemp= Double.parseDouble(currentRow.get("TemperatureF"));
         double lowestTemp= Double.parseDouble(lowestSoFar.get("TemperatureF"));
         if(currentTemp<lowestTemp)
         {
             lowestSoFar = currentRow;
         }
     }
     return lowestSoFar;
  }  
 
  public CSVRecord ColdestHourinFile(CSVParser parser)
  {
      CSVRecord lowestSoFar = null;
      for(CSVRecord currentRow: parser)
      {
          String temp = currentRow.get("TemperatureF");
          if ( temp.equals("-9999") )
           {
               continue;
           }
          lowestSoFar= commonForTwo(currentRow,lowestSoFar);
      }
      return lowestSoFar;
  }
 //This code's function testColdestInDays() is use to find Coldest hour with timings in a Day.
  public void testColdestInDay()
  {
    String relativefile  = "C://Users//Admin//Desktop//Yashi saxena prog//WeatherData//nc_weather//2014//weather-2014-05-01.csv";
    FileResource  fr = new FileResource(relativefile);
    CSVRecord lowest = ColdestHourinFile(fr.getCSVParser());
    System.out.println(" Coldest temperature was " +lowest.get("TemperatureF") +" on " +lowest.get("DateUTC"));
  }
  
  public CSVRecord ColdestInManyDays()
  {
     CSVRecord lowestSoFar = null;
     DirectoryResource dr = new DirectoryResource();
     for(File f:dr.selectedFiles())
      {
          FileResource fr = new FileResource(f);
          CSVRecord currentRow= ColdestHourinFile(fr.getCSVParser());
          lowestSoFar= commonForTwo(currentRow,lowestSoFar);
      }
     return lowestSoFar;
  }
  //This code's function testColdestInManyDays() is use to find Coldest day with hour in a year.
  public void testColdestInManyDays()
  {
    CSVRecord lowest = ColdestInManyDays();
    System.out.println(" Coldest temperature was " +lowest.get("TemperatureF") +" on " +lowest.get("DateUTC"));
  }
  
  public File fileWithColdestTemperature()
  {
    CSVRecord lowestSoFar = null; File file= null;
    DirectoryResource dr = new DirectoryResource();
    for(File f:dr.selectedFiles())
    {
       FileResource fr = new FileResource(f);
       CSVRecord currentRow = ColdestHourinFile(fr.getCSVParser());
       lowestSoFar= commonForTwo(currentRow,lowestSoFar);
       if(lowestSoFar==currentRow)
       {
       file= f;
       }
    }
    return file ;
  }
  //This Function is used to get the file name with coldest day in many days. 
  public void testfileWithColdestTemperature()
  {
      File f= fileWithColdestTemperature();//return complete path name.
      String file = f.getName();//To get specific file name. 
      System.out.println("Coldest day was in the file " +file); //print file name.
      FileResource fr= new FileResource(f);//perfect FileResoure with full path.
      CSVRecord coldest = ColdestHourinFile(fr.getCSVParser());
      System.out.println("Coldest temperature on that day was " +coldest.get("TemperatureF"));
      System.out.println("All the temperature on that day were :");
      CSVParser parser = fr.getCSVParser();
       for(CSVRecord record : parser)
      {
         System.out.println(record.get("DateUTC")+ " " +record.get("TemperatureF"));
      }
  }
}
