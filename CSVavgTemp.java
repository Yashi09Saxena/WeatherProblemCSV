
/**
 * Write a description of CSVavgTemp here.
 * Parsing weather CSV files to get interesting facts about average temperature with high humidity.
 * @author (Yashi Saxena) 
 * @version (02/08/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.io.File.*;
public class CSVavgTemp {
  public double averageTemperatureInFile (CSVParser parser)
  {
       double temp= 0.0;double avg= 0.0;
      for(CSVRecord currentRow: parser)
      {
           double temp1= Double.parseDouble(currentRow.get("TemperatureF"));
           temp= temp+temp1; 
           avg= avg+1;
      }
      double avgtemp= temp/avg;
      return avgtemp;
  }
  //The below function is used to test the average temperature in a day.
  public void testAverageTemperatureInFile()
  {
     FileResource fr = new FileResource();
     CSVParser parser = fr.getCSVParser();
     double csv =  averageTemperatureInFile (parser);
     System.out.println(" Average temperature in file is " +csv);
  }
  
  public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
  {
       double temp= 0.0;double avg= 0.0;
      for(CSVRecord currentRow: parser)
      {
          if(Integer.parseInt(currentRow.get("Humidity"))>=value)
          {
           double temp1= Double.parseDouble(currentRow.get("TemperatureF"));
           temp= temp+temp1; 
           avg= avg+1;
          }
      }
      double avgtemp= temp/avg;
      return avgtemp;
  }
  
  //The below function is used to test the average temperature in a high humidity day.
  public void testAverageTemperatureWithHighHumidityInFile()
  {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double AvgTemp =  averageTemperatureWithHighHumidityInFile(parser,80);
    if(AvgTemp==0.0)
    {
         System.out.println("No temperatures with that humidity");
    }
    else
    {
         System.out.println(" Average temperature when high Humidity is " +AvgTemp);  
    }
    
  }
}
