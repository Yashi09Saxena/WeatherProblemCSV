
/**
 * Write a description of CSVlowHumid here.
 * Parsing weather CSV files to get interesting facts about Humidity.
 * @author (Yashi Saxena) 
 * @version (02/08/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.io.File.*;
public class CSVlowHumid {
  public CSVRecord commonForTwo(CSVRecord currentRow, CSVRecord lowestSoFar)//;low Humidity refactored
  {
     if(lowestSoFar==null)
     {
         lowestSoFar= currentRow;
     }
     else
     {
         double currentHumid= Double.parseDouble(currentRow.get("Humidity"));
         double lowestHumid= Double.parseDouble(lowestSoFar.get("Humidity"));
         if(currentHumid<lowestHumid)
         {
             lowestSoFar = currentRow;
         }
     }
     return lowestSoFar;
  }  
  
  public CSVRecord lowestHumidityInFile(CSVParser parser)
  {
      CSVRecord lowestSoFar = null;
      for(CSVRecord currentRow: parser)
      {
          String humid = currentRow.get("Humidity");
          if ( humid.equals("N/A") )
           {
               continue;
           }
          lowestSoFar= commonForTwo(currentRow,lowestSoFar);
      }
      return lowestSoFar;
  }
  //The below funtion is used to get the low humidity value in a day with date and time.
  public void testlowestHumidityInFile()
  {
     FileResource fr = new FileResource();
     CSVParser parser = fr.getCSVParser();
     CSVRecord csv = lowestHumidityInFile(parser);
     System.out.println(" Lowest Hummidity was " +csv.get("Humidity") +" at " +csv.get("DateUTC"));
  }
  
  public CSVRecord lowestHumidityInManyFiles()
  {
     CSVRecord lowestSoFar = null;
     DirectoryResource dr = new DirectoryResource();
     for(File f:dr.selectedFiles())
      {
          FileResource fr = new FileResource(f);
          CSVRecord currentRow= lowestHumidityInFile(fr.getCSVParser());
          lowestSoFar= commonForTwo(currentRow,lowestSoFar);
      }
     return lowestSoFar;
  }
  //The below function tells us lowest humidity value with date and time in a year.
  public void testlowestHumidityInManyFiles()
  {
    CSVRecord lowest = lowestHumidityInManyFiles();
    System.out.println(" Lowest Humidity was " +lowest.get("Humidity") +" at " +lowest.get("DateUTC"));
  }
}
 