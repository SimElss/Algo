import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class naif {

  public static void main(String[] args) {
    // Read the file and store data in a list
    ArrayList<String> plantList = new ArrayList<String>();
    plantList = readFile();

    // Get the number of lines of plants to process
    int nbLinePlant = Integer.parseInt(plantList.get(0));
    plantList.remove(plantList.get(0));
    
    // Process each line of plants and output the dominant plant
    for (int i = 0; i < nbLinePlant; i++) {
      String[] plants = plantList.get(i * 2 + 1).split(" ");
      int n = Integer.parseInt(plantList.get(i * 2));
      int maxCount = 0;
      String maxPlant = "null";
      for (String plant : plants) {
        int count = 0;
        for (String p : plants) {
          if (plant.equals(p)) {
            count++;
          }
        }
        if (count > n / 2 && count > maxCount) {
          maxCount = count;
          maxPlant = plant;
        }
      }
      System.out.println(maxPlant);
    }
  }

  /**
   * Reads the input file and stores each line in an ArrayList.
   * @return The ArrayList containing each line of plant data from the input file.
   */
  //@ requires champs.txt exists;
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