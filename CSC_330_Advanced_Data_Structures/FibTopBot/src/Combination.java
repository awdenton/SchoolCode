
import java.util.ArrayList;

public class Combination{
    
    public static void main(String[] args) {
        System.out.println(commDynB(6, 3));
    }
    
    static int commDynB(int n, int k){
        int[][] commMat = new int[n+1][n+1];
        
        int i;
        
        commMat[0][0] = 1;
        
        for(i = 1; i <= n; i++){
            commMat[i][0] = 1;
            commMat[i][i] = 1;
            
            for(int j = 1; j < i; j++){
                commMat[i][j] = commMat[i-1][j-1] + commMat[i-1][j];
            }
        }
        
        String grid = "";
        for(int r = 0; r < commMat.length; r++){
            for(int c = 0; c < commMat[r].length; c++){
                grid += commMat[r][c] + "\t";
            }
            grid += "\n";
        }
        System.out.println(grid);        
        
        
        return commMat[n][k];
    }
}