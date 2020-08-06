import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class CountryDisplayer{

  // Creates a new ArrayList of countries and a new ArrayList of
  // indicators. 
  private List<Country> countries = new ArrayList<Country>();
  private ArrayList<String> indicators = new ArrayList<String>();

  // This is the first constructor that takes 3 parameters, a
  // fileName, an indicator, and whether it should be sorted from
  // leastToGreatest or greatestToLeast.
  public CountryDisplayer(String fileName, String indicator, String sortingMethod){
    indicators.add("name");
    indicators.add("CO2Emissions");
    indicators.add("AccessToElectricity");
    indicators.add("RenewableEnergy");
    indicators.add("ProtectedAreas");
    indicators.add("PopulationGrowth");
    indicators.add("PopulationTotal");
    indicators.add("UrbanPopulationGrowth");

    if(!indicators.contains(indicator)){
      System.out.println("Enter a valid indicator");
      System.exit(1);
    }
    if(!sortingMethod.equals("leastToGreatest") && !sortingMethod.equals("greatestToLeast")){
      System.out.println("Enter a valid sorting method.");
      System.exit(1);
    }
    
    // calls readFile, a method that reads the file accordingly. 
    readFile(fileName);
    // Using the indicator passed as a parameter, it finds the 
    // index of the indicator from the ArrayList indicators. 
    int index = indicators.indexOf(indicator);
    sort(index);
    displayText(sortingMethod);

  }

  // This is the constructor with 4 String parameters that indicate
  // the fileName, one indicator and a sorting method for that 
  // indicator, and a second indicator, all to be displayed on 
  // a BarChart. 
  public CountryDisplayer(String fileName, String indicator, String sortingMethod, String indicator2){
    indicators.add("name");
    indicators.add("CO2Emissions");
    indicators.add("AccessToElectricity");
    indicators.add("RenewableEnergy");
    indicators.add("ProtectedAreas");
    indicators.add("PopulationGrowth");
    indicators.add("PopulationTotal");
    indicators.add("UrbanPopulationGrowth");

    if(!indicators.contains(indicator) || !indicators.contains(indicator2)){
      System.out.println("Enter a valid indicator");
      System.exit(1);
    }
    if(!sortingMethod.equals("leastToGreatest") && !sortingMethod.equals("greatestToLeast")){
      System.out.println("Enter a valid sorting method.");
      System.exit(1);
    }
    
    readFile(fileName);
    int index1 = indicators.indexOf(indicator);
    int index2 = indicators.indexOf(indicator2);
    sort(index1);
    displayGraph(indicator, indicator2, index1, index2, sortingMethod);

  }

  // This method reads the File and creates the Country objects
  // and stores them in the instance variable ArrayList countries.
  // It's called in both constructors. 
  public void readFile(String fileName){
     File file = new File(fileName);
    Scanner scanner = null;

    try{
      scanner = new Scanner(file);
    } catch (FileNotFoundException e){
      System.err.println(e);
      System.exit(1);
    }
    
    scanner.nextLine();
    while (scanner.hasNextLine()){
      Country c = new Country();
      String line = scanner.nextLine();
      String[] countryLine = line.split(",");
      for (int i = 0; i < countryLine.length; i++){
        if (i == 0){
          c.setName(countryLine[i]);
        }
        else{
          c.setDouble(i, Double.parseDouble(countryLine[i]));
        }
          
      }
      countries.add(c);
    }
  }

  // Using an index, this method implements selectionSort to order
  // the countries list. 
  public void sort(int index){
    int min;
    Country temp;
    for (int i = 0; i < countries.size(); i++){
      min = i;
        for (int j = i+1; j < countries.size(); j++){
          if (countries.get(j).getDouble(index) < countries.get(min).getDouble(index)){
            min = j;
          }
        }
        temp = countries.get(i);
        countries.set(i, countries.get(min));
        countries.set(min, temp);
      }
  }

  // This method uses the displayCountry() method in country to 
  // properly display each country. Also, based on the sorting
  // method, it will display the countries in ascending or
  // descending order.
  public void displayText(String sortingMethod){
    if(sortingMethod.equals("leastToGreatest")){
      for (int i = 0; i < countries.size(); i++){
        System.out.println(countries.get(i).displayCountry().toString().replace("]","").replace("[",""));
      }
    }
    else if(sortingMethod.equals("greatestToLeast")){
      for (int i = countries.size() - 1; i > 0; i--){
        System.out.println(countries.get(i).displayCountry().toString().replace("]","").replace("[",""));
      }
    }
  }

  // This method displays the graph according to 5 parameters:
  // the indicator that the sorting method is used on, a second 
  // indicator that is displayed in comparison on the graph, 
  // the index of both indicators in the Country, and a sorting
  // method. 
  public void displayGraph(String indicator1, String indicator2, int index, int index2, String sortingMethod){
    String chartTitle = "";
    BarChart bc = new BarChart("", "", "");
    // If there are more than ten countries...
    if (countries.size() >= 10){
      if (sortingMethod.equals("greatestToLeast")){
        chartTitle = "Top " + indicator1;
        bc = new BarChart(chartTitle, "Country", "Value");
        for (int i = countries.size() - 1; i > countries.size() - 11; i--){
          Country curCountry = countries.get(i);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index), indicator1);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index2), indicator2);
        }
      }
      else{
        chartTitle = "Lowest " + indicator1;
        bc = new BarChart(chartTitle, "Country", "Value");
        for (int i = 0; i < 10; i++){
          Country curCountry = countries.get(i);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index), indicator1);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index2), indicator2);
        }
      }
    }
    // If there are less than 10 countries.  
    else{
      if (sortingMethod.equals("greatestToLeast")){
        chartTitle = "Top " + indicator1;
        bc = new BarChart(chartTitle, "Country", "Value");
        for (int i = countries.size() - 1; i > 0; i--){
          Country curCountry = countries.get(i);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index), indicator1);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index2), indicator2);
        }
      }
      else{
        chartTitle = "Lowest " + indicator1;
        bc = new BarChart(chartTitle, "Country", "Value");
        for (int i = 0; i < countries.size(); i++){
          Country curCountry = countries.get(i);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index), indicator1);
          bc.addValue(curCountry.getName(), curCountry.getDouble(index2), indicator2);
        }
      }
    }
    bc.displayChart();
  }


  public static void main(String[] args){
    // If 3 arguments are passed. 
    if (args.length == 3){
      CountryDisplayer cd = new CountryDisplayer(args[0], args[1], args[2]);
     }
    // If 4 arguments are passed.
    else if (args.length == 4){
      CountryDisplayer cd = new CountryDisplayer(args[0], args[1], args[2], args[3]);
    }
    // Allows the user to type in arguments. 
    if (args.length == 0){
      System.out.println("No arguments were passed.");
      System.exit(1);
    }
    
  }

}