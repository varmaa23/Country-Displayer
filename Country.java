import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Country{

  // Here are all the instance variables that store each component 
  // of each country. 
  private String name;
  private Double CO2Emissions;
  private Double AccessToElectricity;
  private Double RenewableEnergy;
  private Double ProtectedAreas;
  private Double PopulationGrowth;
  private Double PopulationTotal;
  private Double UrbanPopulationGrowth;

  // Getter methods. The getName() method returns a String (the
  // name) and the getDouble(int index) method returns the Double
  // indicator values. 
  public String getName(){
    return name;
  }

  public double getDouble(int index){
    if (index == 1){
      return CO2Emissions; 
    }
    else if (index == 2){
      return AccessToElectricity;
    }
    else if (index == 3){
      return RenewableEnergy;
    }
    else if (index == 4){
      return ProtectedAreas;
    }
    else if (index == 5){
      return PopulationGrowth;
    }
    else if (index == 6){
      return PopulationTotal;
    }
    else {
      return UrbanPopulationGrowth;
    }
  }

  // Setter methods. The setName(String newName) sets name to a 
  // new String value and the setDouble(int index, Double 
  // newValue) assigns the newValue to the corresponding country's
  // indicator.
  public void setName(String newName){
    name = newName;
  }

  public void setDouble(int index, Double newValue){
    if (index == 1){
      CO2Emissions = newValue; 
    }
    else if (index == 2){
      AccessToElectricity = newValue;
    }
    else if (index == 3){
      RenewableEnergy = newValue;
    }
    else if (index == 4){
      ProtectedAreas = newValue;
    }
    else if (index == 5){
      PopulationGrowth = newValue;
    }
    else if (index == 6){
      PopulationTotal = newValue;
    }
    else if (index == 7){
      UrbanPopulationGrowth = newValue;
    }
 
  }

  // This method allows displayText() in CountryDisplayer to 
  // properly represent a Country by returning an ArrayList
  // countryDisplay of Strings, where each represents
  // a Country object.
  public ArrayList displayCountry(){
    ArrayList<String> countryDisplay = new ArrayList<String>();
    countryDisplay.add(name);
    for(int i = 1; i < 8; i++){
      countryDisplay.add(Double.toString(getDouble(i)));
    }
    return countryDisplay;
  }

}


