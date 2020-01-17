
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTester {
    private double[][] grid;
    
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList <Double> list = new ArrayList<>();
        Scanner fileIn = new Scanner(new File("data.in"));
        
        while(fileIn.hasNext()) {
            double x = fileIn.nextDouble();
            list.add(x);
        }
        
        int rows = 0;
        for(int i = 1; i <= Math.sqrt(list.size()); i++) {
            if(list.size() % i == 0)
                rows = i;
        }
        
        double[] primArr = new double[list.size()];
        for(int i = 0; i < primArr.length; i++){
            primArr[i] = list.get(i);
        }
        
        System.out.println("ArrayListTester");
        ArrayListTester test = new ArrayListTester(rows, primArr.length / rows, primArr);
        System.out.println(test.toString());
    }
    
    public ArrayListTester (int numRows, int numCols, double[] scan) {
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
                for(int c = grid[0].length - 1; c >=0; c--){
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
}