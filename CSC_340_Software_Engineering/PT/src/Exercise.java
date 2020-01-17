import java.io.*;
import java.nio.file.*;

public class Exercise {
    
    private String name;
    private int reps;
    private int cals;
    private String rating;
    
    public Exercise(){
        name = "";
        reps = -1;
        cals = -1;
        rating = "";
    }
    
    public Exercise(String n, int r, int c, String rt){
        name = n;
        reps = r;
        cals = c;
        rating = rt;
    }
    
    public void writeExercise() {
        try(FileWriter fw = new FileWriter("exercises.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)) {
            pw.println("ED," + toString());
        } catch (IOException e) { }
    }
    
    public String getName(){
        return name;
    }
    
    public int getReps(){
        return reps;
    }
    
    public int getCals(){
        return cals;
    }
    
    public String getRating(){
        return rating;
    }
    
    public void setRating(String rt){
        rating = rt;
    }
    
    public String toString(){
        return name + "," + reps + "," + cals + "," + rating;
    }
    
}