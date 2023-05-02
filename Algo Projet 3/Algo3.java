import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Algo3 {
    public static void main(String[] args){
        // create an ArrayList to store the contents of the file
        ArrayList<String> bagList = new ArrayList<String>();
        bagList = readFile();

        // get the number of test cases and remove it from the ArrayList
        int itRepeat = Integer.parseInt(bagList.get(0));        
        bagList.remove(bagList.get(0));

        // split the first line of the first test case to get its values
        String[] actualLine = bagList.get(0).split("");
        int indice = 0;
        
        // iterate through each test case
        for(int i = 0; i < itRepeat; i++){            
            float[][] matrice = recupInfo(bagList, indice);
            float[][] matriceTri = tri(matrice);
            int optimalList = Optimal(matriceTri);

            System.out.println(optimalList);
            
            // update the index to start from the next test case
            indice = Integer.parseInt(actualLine[0]) + 1;
            actualLine = bagList.get(indice).split(" ");
        }

    }
    /**
     * Calculates the optimal list of items to include in the bag
     * @param bag the matrix containing the information for the items
     * @return the total value of the optimal list of items to include in the bag
     */
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
    /**
     * This method sorts a float matrix in descending order based on the third column of the matrix. 
     * @param bag the float matrix to be sorted
     * @return the sorted float matrix
     * @throws IllegalArgumentException if the input matrix is null or empty
     */
    public static float[][] tri(float bag [][]){
        // loop through each row of the array, starting with the second row
        for(int i = 1; i < bag.length; i++){
            int max = i;
            // loop through the remaining rows to find the row with the highest value in the third column
            for(int j = i + 1; j < bag.length; j++)
                if(bag[j][2] > bag[max][2]){
                    max = j;
                }
            // swap the current row with the row containing the maximum value
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

        // return the sorted array 
        return bag;
    }

    /**
     *  This method extracts information from a specific line in the input ArrayList and returns it as a matrix.
     * @param arrayList the ArrayList containing the input lines
     * @param indice the index of the line to extract the information from
     * @return a float matrix containing the extracted information
     * @throws IllegalArgumentException if the input ArrayList or the index is null or invalid
     */
    public static float[][] recupInfo(ArrayList<String> arrayList, int indice) throws IllegalArgumentException{
        
        // Check for invalid inputs
        if (arrayList == null || arrayList.isEmpty() || indice < 0 || indice >= arrayList.size()) {
            throw new IllegalArgumentException("Invalid inputs");
        }
        
        // Parse the first line to get the number of items and the height limit of the bag.
        String[] array = arrayList.get(indice).split(" ");
        int m = 1 + Integer.parseInt(array[0]);
        int heightlimit = Integer.parseInt(array[1]);
        int j = indice;

        
        // Initialize the matrix to store the information.
        float[][] matrix = new float[m][3];
        matrix[0][0] = m - 1;
        matrix[0][1] = heightlimit;

        // Loop over the remaining lines to get the value and height of each item, and calculate their ratio.
        for (int i=1; i < m; i++){
            j++;
            try {
                String[] actualLine = arrayList.get(j).split(" ");
                float value = Integer.parseInt(actualLine[0]);
                float height = Integer.parseInt(actualLine[1]);
                float nb = value / height;
    
                matrix[i][0] = value;
                matrix[i][1] = height;
                matrix[i][2] = nb;
            } catch (Exception e) {
                // In case of an exception, print an error message indicating the line number and the error message.
                System.out.println("Error while processing line " + j + ": " + e.getMessage());
            }
        }

        // Return the extracted information as a matrix
        return matrix;

    }
    /**
     * Reads the input file and stores each line in an ArrayList. 
     * @return The ArrayList containing each line of plant data from the input file.
     */
    //@ requires champ.txt exists;
    //@ ensures \result != null;
    public static ArrayList<String> readFile() {
        try {
            File myPlantFile = new File("text.txt");
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
