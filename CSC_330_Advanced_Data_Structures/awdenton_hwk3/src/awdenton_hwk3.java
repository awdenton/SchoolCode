//This is my own work: Aaron Denton
import java.util.ArrayList;

public class awdenton_hwk3 {

    static ArrayList<Integer> set = new ArrayList<>();

    public static void main(String[] args) {
        set.add(2);
        set.add(5);
        set.add(3);
        set.add(12);
        set.add(8);
        set.add(10);

        System.out.println("Set: " + set.toString());
        System.out.println("Subset sum to 1000: " + subSum(set, 1000));
        System.out.println("Subset sum to 33: " + subSum(set, 33));
        System.out.println("Subset sum to 34: " + subSum(set, 34));
        System.out.println("Subset sum to 4: " + subSum(set, 4));
        System.out.println("Subset sum to 40: " + subSum(set, 40));
    }
    
    /* Method to solve the Subset Sum problem. Given a set of integers
    *  can you find a subset of that set that adds to some target integer
    * 
    *  For the purposes of this method, the empty set is equated to 0
    *
    *  @ArrayList<Integer> list - the set of integers taken into the method
    *  @int target - the target int to find a sum for
    *  Returns true if a subset if found, false otherwise
    */
    static boolean subSum(ArrayList<Integer> list, int target) {
        //find the sum of the entire set
        int maxSum = 0;
        for (Integer e : list) {
            maxSum += e;
        }
        //if the target exceeds this number, or is negative,
        //you can return false
        if (target > maxSum || target < 0) {
            return false;
        }

        //create a 2d array to hold result values, where number of columns
        //equals the number of integers between 0 and the target inclusive
        boolean sums[][] = new boolean[list.size()][target + 1];
        //initialize the base cases
        sums[0][0] = true;
        sums[0][list.get(0)] = true;

        //fill out the rest of the grid
        for (int i = 1; i < sums.length; i++) {
            for (int j = 0; j < sums[i].length; j++) {
                //if a column was true for the previous row,
                //make it true for current row
                //also make it true for that column + int value of that row
                if (sums[i - 1][j] == true) {
                    sums[i][j] = true;
                    if((j + list.get(i)) <= target)
                        sums[i][j + list.get(i)] = true;
                }
            }
        }
        //the bottom of the grid contains all possible sums, so we
        //return the truth value for the given column from the last row
        return sums[sums.length - 1][target];
    }
}