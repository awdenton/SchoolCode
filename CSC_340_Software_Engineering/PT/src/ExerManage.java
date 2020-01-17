import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExerManage {
    
    ArrayList<Exercise> exercises;
    int[][] plans;
    
    
    public ExerManage() throws FileNotFoundException{
        exercises = new ArrayList<Exercise>();
        plans = new int[4][7];
        
        Scanner scan = new Scanner(new File("exercises.txt"));        
        scan.useDelimiter(",|\\n");
        
        while(scan.hasNext("ED")){
            scan.next("ED");
            exercises.add(new Exercise(scan.next(), scan.nextInt(), scan.nextInt(), scan.next()));
        }
        
        scan = new Scanner(new File("exPlans.txt"));
        //scan.useDelimiter(",|\\n");
        while(scan.hasNext()){
            for(int r = 0; r < plans.length; r++){
                scan.next();
                for(int c = 0; c < plans[r].length; c++){
                    plans[r][c] = scan.nextInt();
                }
            }
        }
    }
    
    public String[] getNames(){
        String[] names = new String[exercises.size()];
        for(int i = 0; i < names.length; i++){
            names[i] = exercises.get(i).getName();
        }
        
        return names;
    }
    
    public void createExercise(String name, int reps, int cals){
        Exercise temp = new Exercise(name, reps, cals, "b");
        exercises.add(temp);
    }
    
    public void createPlan(int fm, int[] exers){
        for(int i = 0; i < 7; i++){
            plans[fm][i] = exers[i];
        }
    }
    
    public void writePlan(String fm, int[] exers){
        try(FileWriter fw = new FileWriter("exPlans.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)) {
            pw.println(fm);
            for(int i = 0; i < 7; i++)
                pw.println(exers[i]);
        } catch (IOException e) {}
    }
    
}