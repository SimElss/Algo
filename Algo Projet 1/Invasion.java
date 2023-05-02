import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Invasion{

  public static void main(String[] args){
    // Read the file and store data in a list
    ArrayList<String> plantList = new ArrayList<String>();
    plantList = readFile();

    // Get the number of lines of plants to process
    int nbLinePlant = Integer.parseInt(plantList.get(0));
    plantList.remove(plantList.get(0));
    
    // Process each line of plants and output the dominant plant
    insertProc(plantList, nbLinePlant);
    
  }

  /**
   * Processes each line of plant data and outputs the dominant plant.
   * 
   * @param array      The ArrayList containing each line of plant data.
   * @param numberLine The number of lines of plant data to process.
   */
  //@ requires array != null && numberLine > 0 && numberLine <= array.size();
  //@ ensures true;
  public static void insertProc(ArrayList<String> array, int numberLine) {  
    int numberplant = 0;
    int listPlant = 1;

    // Process each line of plants
    for(int m = 0; m < numberLine ; m++ ){

      // Call the "tri" method to find the dominant plant in the line of plants
      divineAndconquer(array, 1, 0, numberplant, listPlant, 0, 0, false);

      // Move on to the next line of plants
      listPlant = listPlant + 2;
      numberplant = numberplant + 2;
    }
  }


  /**
   * Finds the dominant plant in a line of plant data.
   * 
   * @param array      The ArrayList containing the line of plant data.
   * @param i          The current index of the plant being processed.
   * @param j          The total number of plants in the line.
   * @param numberplant The index of the first line of plant data.
   * @param listPlant  The index of the current line of plant data.
   * @param test       The number of times the current plant color appears in the line.
   * @param nb         The index of the dominant plant in the line.
   * @param bool       A boolean indicating if a dominant plant has been found.
   */
  //@ requires array != null && i > 0 && i <= (j/2 + 1) && numberplant >= 0 && listPlant >= 0 && test >= 0 && nb >= 0;
  //@ ensures true;
  public static void divineAndconquer(ArrayList<String> array, int i, int j, int numberplant, int listPlant, int test, int nb, boolean bool){
    // Get the number of plants in the line
    j = Integer.parseInt(array.get(numberplant));

    // Split the line into an array of plant strings
    String[] arrayString = array.get(listPlant).split(" ");
    
    // If we haven't processed all the plants in the line yet, continue processing
    if(i <= (j/2 + 1)){

      // Count the number of times the current color appears in the line
      for(String plant : arrayString){
        if(arrayString[i-1].equals(plant)){
          test++;
        }
      }
      // Remember the plant if she's dominant
      if (test>j/2){
        bool = true;
        nb = i; 
      }

      test = 0;
      i++;
      // Recursively call the "tri" method to continue processing
      divineAndconquer(array, i, j, numberplant, listPlant, test, nb, bool);
    
    }else if(bool){
      // Output the dominant plant
      System.out.println(arrayString[i]);
    
    }else{
      // Output null if no dominant plant was found
      System.out.println("null");
    
    }

  }
  /**
   * Reads the input file and stores each line in an ArrayList.
   * @return The ArrayList containing each line of plant data from the input file.
   */
  //@ requires champ.txt exists;
  //@ ensures \result != null;
  public static ArrayList<String> readFile() {
      try {
        File myPlantFile = new File("champs.txt");
        Scanner myReader = new Scanner(myPlantFile);
        ArrayList<String> plantList = new ArrayList<String>();
        
        // Read each line of the input file and store it in an ArrayList
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          plantList.add(data); 
        }
        myReader.close();
        
        return plantList;

      } catch (FileNotFoundException e) {

        // If the input file can't be found, output an error message
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      return null;
    
  }

}