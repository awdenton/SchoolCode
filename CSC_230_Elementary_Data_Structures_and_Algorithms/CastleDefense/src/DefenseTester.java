
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is my own work: Aaron Denton
 * @author Aaron
 */
public class DefenseTester {
    private double[][] grid;
    
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList <Double> list = new ArrayList<>();
        Scanner fileIn = new Scanner(new File("data.in"));
        
        while(fileIn.hasNext()) {
            double x = fileIn.nextDouble();
            list.add(x);
        }
        
        int rows = 1;
        for(int i = 1; i <= Math.sqrt(list.size()); i++) {
            if(list.size() % i == 0){
                rows = i;
            }
        }
        
        double[] primArr = new double[list.size()];
        for(int i = 0; i < primArr.length; i++){
            primArr[i] = list.get(i);
        }
        
        DefenseTester castle = new DefenseTester(rows, primArr.length / rows, primArr);
        System.out.println("Defense Tester");
        System.out.println("NumRows: " + rows + "\tNumCols: " + primArr.length / rows);
        castle.findWeakest();
    }
    
    public DefenseTester (int numRows, int numCols, double[] scan) {
        int count = 0;
        grid = new double[numRows][numCols];
        for(int r = 0; r < grid.length; r++){
            if(r % 2 == 0){
                for(int c = 0; c < grid[0].length; c++) {
                    grid[r][c] = scan[count];
                    count++;
                }
            }
            else if(r % 2 == 1){
                for(int c = grid[0].length - 1; c >= 0; c--){
                    grid[r][c] = scan[count];
                    count++;
                }
            }
        }
    }
    
    public String toString(){
        String result = "";
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                result += grid[r][c] + "\t";
            }
            result += "\n";
        }
        return result;
    }

    public double getAverage(int startRow, int endRow, int startCol, int endCol) {
        double average = 0;
        average += grid[startRow][startCol] + grid[startRow][endCol] +
                   grid[endRow][startCol] + grid[endRow][endCol];
        average /= 4;
        return average;
    }
    
    public void findWeakest() {
        int weakRow = 0;
        int weakCol = 0;
        double average = this.getAverage(0, 1, 0, 1);
        for (int r = 0; r < grid.length - 1; r++){
            for(int c = 0; c < grid[0].length - 1; c++){
                if(this.getAverage(r, r + 1, c, c +1) < average) {
                    average = this.getAverage(r, r + 1, c, c +1);
                    weakRow = r;
                    weakCol = c;
                }
            }
        }
        System.out.println(this.toString());
        System.out.println("The weakest 2 X 2 on the castle begins at (" + 
                weakRow + ", " + weakCol + ") with an average strength of "
                + average);
    }
    
}