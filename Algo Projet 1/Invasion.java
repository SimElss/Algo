import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Invasion{
  public static void main(String[] args){
    ArrayList<String> plantList = new ArrayList<String>();
    plantList = readFile();
    int nbLinePlant = Integer.parseInt(plantList.get(0));
    plantList.remove(plantList.get(0));
    insertProc(plantList, nbLinePlant);
    
  }
  public static void insertProc(ArrayList<String> array, int numberLine) {  
    int numberplant = 0;
    int listPlant = 1;
    int m = 0;


    while(m < numberLine ){
      tri(array, 1, 0, numberplant, listPlant, 0, 0, false);

      listPlant = listPlant + 2;
      numberplant = numberplant + 2;
      m++;
    }
  }

  public static void tri(ArrayList<String> array, int i, int j, int numberplant, int listPlant, int test, int nb, boolean bool){
    j = Integer.parseInt(array.get(numberplant));

    String[] arrayString = array.get(listPlant).split(" ");
    if(i <= (j/2 + 1)){
      for(String couleur : arrayString){
        if(arrayString[i-1].equals(couleur)){
          test++;
        }
      }
      if (test>j/2){
        bool = true;
        nb = i; 
      }
      test = 0;
      i++;
      tri(array, i, j, numberplant, listPlant, test, nb, bool);
    }else if(bool){
      System.out.println(arrayString[i]);
    }else{
      System.out.println("null");
    }

  }

  public static ArrayList<String> readFile() {
      try {
        File myPlantFile = new File("champs.txt");
        Scanner myReader = new Scanner(myPlantFile);
        ArrayList<String> plantList = new ArrayList<String>();
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          plantList.add(data); 
        }
        myReader.close();
        
        return plantList;

      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      return null;
    
  }

}
