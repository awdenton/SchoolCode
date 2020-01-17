/*
* This is mr own work: Aaron Denton is cool
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AlphaFinder {
    
    public static void main(String[] args) throws FileNotFoundException{
        
        //Setup scanner to read in the data
        Scanner fileIn = new Scanner(new File("test.in"));
        //read the first line determining the number of tests
        int tests = fileIn.nextInt();
        System.out.println("ALPHAS OUTPUT");
        //iterate through the file, testing the strings for Alpha status
        for(int i = 0; i < tests; i++){
            if(isAlpha(fileIn.next()))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        System.out.println("END OF OUTPUT");
        
    }
    
    public static boolean isAlpha(String s){
        //if the string has no Y or L in the minimum positions, then there is no
        //Charley at the beginning and we can return false
        if(s.indexOf('Y') < 1 && s.indexOf('L') < 4)
            return false;
        //initialize split point to the last Y
        int split = s.lastIndexOf('Y');
        //check if there is an L after the Y, update split point to the last L
        if(s.lastIndexOf('L') > split)
            split = s.lastIndexOf('L');
        //look for a Charley starting at the beginning of the string, running
        //to one past the split point, then a Bravo running past the split
        //point to the end.
        return(isCharley(s.substring(0,split+1)) && isBravo(s.substring(split+1)));
    }
    
    public static boolean isBravo(String s){
        //make sure the string is long enough
        if(s.length() < 3) return false;
        //make sure the first character is a B or R
        if(s.charAt(0) != 'B' && s.charAt(0) != 'R') return false;
        //make sure the second character is an A
        if(s.charAt(1) != 'A') return false;
        
        //find the first character that ISNT an A
        int i = 1;
        for( ; i < s.length() && s.charAt(i)=='A'; i++){}
        
        //if no non-A's are found, return false
        if(i == s.length()) return false;
        //if a V is at the end, return true
        if(i == (s.length()-1) && s.charAt(i) == 'V') return true;
        //for any other character, start the cycle over again with a substring
        //beginning at i
        else return isBravo(s.substring(i));
    }
    
    public static boolean isCharley(String s){
        //check if s is a two character Charley
        if(s.equals("CY")) return true;
        //make sure the string is long enough
        if(s.length() < 5) return false;
        //make sure first character is a C
        if(s.charAt(0) != 'C') return false;
        //make sure the string ends with an L
        if(s.lastIndexOf('L') != s.length()-1) return false;
        //if next character is not an H, and there is room for a Brave
        //look for a Bravo finished with an L
        if(s.charAt(1) != 'H' && s.lastIndexOf('L') >= 4) return isBravo(s.substring(1, s.lastIndexOf('L')));
        //if next character is an H, and there is room for a Charley
        //look for a Charley finished with an L
        if(s.charAt(1) == 'H' && s.lastIndexOf('L') >= 4) return isCharley(s.substring(2, s.lastIndexOf('L')));
        
        //all other cases fail
        return false;
    }
    
}