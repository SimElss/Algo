import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Algo3 {
    public static void main(String[] args){
        ArrayList<String> bagList = new ArrayList<String>();
        bagList = readFile();

        int itRepeat = Integer.parseInt(bagList.get(0));        
        bagList.remove(bagList.get(0));

        String[] actualLine = bagList.get(0).split("");
        
        int indice = 0;
        
        
        for(int i = 0; i < itRepeat; i++){
            
            
            
            float[][] matrice = recupInfo(bagList, indice);
            float[][] matriceTri = tri(matrice);
            int optimalList = Optimal(matriceTri);

            System.out.println(optimalList);
            
            indice = Integer.parseInt(actualLine[0]) + 1;
            actualLine = bagList.get(indice).split(" ");
        }

    }

    public static int Optimal(float[][] bag){
        float value = 0;
        float limit = 0;

        for(int i = 1; i < bag.length; i++){
            limit = limit + bag[i][1];
            value = value + bag[i][0];
            if(limit > bag[0][1]){
                value = value - bag[i][0];
                limit = limit - bag[i][1];
            }
            
        }
        
        return (int)value;

    }

    public static float[][] tri(float bag [][]){

        for(int i = 1; i < bag.length; i++){
            int max = i;
            
            for(int j = i + 1; j < bag.length; j++)
                if(bag[j][2] > bag[max][2]){
                    max = j;
                }
            float temp = bag[i][0];
            float temp1 = bag[i][1];
            float temp2 = bag[i][2];

            bag[i][0] = bag[max][0];
            bag[i][1] = bag[max][1];
            bag[i][2] = bag[max][2];

            bag[max][0] = temp;
            bag[max][1] = temp1;
            bag[max][2] = temp2;
        }
        return bag;
    }

    public static float[][] recupInfo(ArrayList<String> arrayList, int indice){
        String[] array = arrayList.get(indice).split(" ");
        int m = 1 + Integer.parseInt(array[0]);
        int heightlimit = Integer.parseInt(array[1]);
        int j = indice;

        

        float[][] matrice = new float[m][3];

        matrice[0][0] = m - 1;
        matrice[0][1] = heightlimit;

        for (int i=1; i < m; i++){
            j++;

            String[] actualLine = arrayList.get(j).split(" ");

            float value = Integer.parseInt(actualLine[0]);
            float height = Integer.parseInt(actualLine[1]);
            float nb = value / height;

            matrice[i][0] = value;
            matrice[i][1] = height;
            matrice[i][2] = nb;
        }

        return matrice;

    }
    
    public static ArrayList<String> readFile() {
        try {
          File myPlantFile = new File("text.txt");
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
