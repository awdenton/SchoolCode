
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is my own work: Aaron Denton
 * @author Aaron
 */
public class CastleDefense {
    private double[][] grid;
    
    public static void main(String[] args) throws FileNotFoundException {
        //declare a new ArrayList to hold double values from the input file
        ArrayList <Double> list = new ArrayList<>();
        //Declare a new scanner to read the input file
        Scanner fileIn = new Scanner(new File("data.in"));
        
        //Copy the contents of the input file into the ArrayList
        while(fileIn.hasNext()) {
            double x = fileIn.nextDouble();
            list.add(x);
        }
        
        //Determine the "squareness" of the grid. Assume at least 1 row.
        int rows = 1;
        //Looks for the largest even divisor of list.size() that is less than or
        //equal to the square root of list.size(). We don't need to check past
        //the square root because past that point we would only be finding the
        //multiplicands of the divisors found before the square root.
        for(int i = 1; i <= Math.sqrt(list.size()); i++) {
            if(list.size() % i == 0){
                rows = i;
            }
        }
        
        //Convert the ArrayList to an array of doubles
        //Initialize a new double array to the size of the ArrayList
        double[] primArr = new double[list.size()];
        //Copy contents of ArrayList to the double array
        for(int i = 0; i < primArr.length; i++){
            primArr[i] = list.get(i);
        }
        
        //Create a new CastleDefense object using the number of rows we found,
        //the array length divided by number of rows for number of columns,
        //and finally the double array
        CastleDefense castle = new CastleDefense(rows, primArr.length / rows, primArr);
        //Display the weakest section of the Castle wall
        castle.findWeakest();
    }
    
    /**
     * CastleDefense constructor. Initializes the grid with numRows and numCols
     * and then places the contents of the ArrayList into the grid, moving back
     * and forth along the grid rows.
     * @param numRows should be at least 1
     * @param numCols should be at least 1
     * @param list  should have at least 1 value
     */
    public CastleDefense (int numRows, int numCols, double[] scan) {
        int count = 0;
        //initialize the grid array
        grid = new double[numRows][numCols];
        //copy the contents of ArrayList into the grid array
        for(int r = 0; r < grid.length; r++){
            //determine which direction to fill the row from. The first
            //if statement fills from the left
            if(r % 2 == 0){
                for(int c = 0; c < grid[0].length; c++) {
                    //places the first value of the ArrayList into the current
                    //position in the grid
                    grid[r][c] = scan[count];
                    count++;
                }
            }
            //The second if statement fills from the right
            else if(r % 2 == 1){
                for(int c = grid[0].length - 1; c >=0; c--){
                    grid[r][c] = scan[count];
                    count++;
                }
            }
        }
    }
    
    /**
     * Prints the contents of the the grid array to the screen
     * @return 
     */
    public String toString(){
        //Initialize the return String
        String result = "";
        //Cycle through the grid and assign it to the String
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                //At the end of each value add a tab to increase readability
                result += grid[r][c] + "\t";
            }
            //At the end of the row add a line break
            result += "\n";
        }
        return result;
    }
    
    /**
     * Finds the average of the values of a given 2 X 2 in the grid array
     * @param startRow
     * @param endRow
     * @param startCol
     * @param endCol
     * @return 
     */
    public double getAverage(int startRow, int endRow, int startCol, int endCol) {
        //Initialize the return double
        double average = 0;
        //Add the four values together
        average += grid[startRow][startCol] + grid[startRow][endCol] +
                   grid[endRow][startCol] + grid[endRow][endCol];
        //Divide by four to find the average
        average /= 4;
        return average;
    }
    
    /**
     * Finds the point in the grid array that has the lowest average value,
     * prints the location in the grid as well as the average value
     */
    public void findWeakest() {
        //Initialize the return values, the x and y of the spot on the grid
        //as well as the average, set to the average of the top left corner
        int weakRow = 0;
        int weakCol = 0;
        double average = this.getAverage(0, 1, 0, 1);
        //Look through the grid array for smaller averages
        for (int r = 0; r < grid.length - 1; r++){
            for(int c = 0; c < grid[0].length - 1; c++){
                if(this.getAverage(r, r + 1, c, c + 1) < average) {
                    //If a smaller average is found, update the average
                    //and note its position in the grid array
                    average = this.getAverage(r, r + 1, c, c +1);
                    weakRow = r;
                    weakCol = c;
                }
            }
        }
        System.out.println(this.toString());
        //Display the average and the position
        System.out.println("The weakest 2 X 2 on the castle begins at (" + 
                weakRow + ", " + weakCol + ") with an average strength of "
                + average);
    }
    
}