package mealp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComboBox;

public class ExerManage {
    
    ArrayList<Exercise> exercises;
    int[][] plans;
    
    
    public ExerManage() throws FileNotFoundException{
        exercises = new ArrayList();
        plans = new int[4][7];
        
        Scanner scan = new Scanner(new File("//Users//Sophie//NetBeansProjects//projectTest2//src//exercises.txt"));        
        scan.useDelimiter(",|\\n");
        
        String[] rts = new String[4];
        String[] cmps = new String[4];
        
        //Read exercises from data and place into an array
        while(scan.hasNext()){
            
            String n = scan.next();
            int r = scan.nextInt();
            int c = scan.nextInt();
            
            for(int k = 0; k < 4; k++) {
                rts[k] = scan.next();
                cmps[k] = scan.next();
            }
            exercises.add(new Exercise(n, r, c, rts, cmps));
        }
        
        //Read in exercise plans and store in an array
        scan = new Scanner(new File("//Users//Sophie//NetBeansProjects//projectTest2//src//exPlans.txt"));
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
        String[] blankRating = {"-", "-", "-", "-"};
        String[] blankCompletion = {"no", "no", "no", "no"};
        Exercise temp = new Exercise(name, reps, cals, blankRating, blankCompletion);
        exercises.add(temp);
    }
    
    public void createPlan(int fm, int[] exers){
        for(int i = 0; i < 7; i++){
            plans[fm][i] = exers[i];
        }
    }
    
    public String[] getPlan(int fm){
        int[] plan = new int[7];
        for(int i =0; i < 7; i++){
            plan[i] = plans[fm][i];
        }
        String[] exerNames = new String[7];
        for(int j = 0; j < 7; j++){
            exerNames[j] = exercises.get(plan[j]).getName();
        }
        return exerNames;
    }
    
    public void writeData(JComboBox fm){
        //re-write all exercise data
        try(FileWriter fw = new FileWriter("//Users//Sophie//NetBeansProjects//projectTest2//src//exercises.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)) {
            for(int i = 0; i < exercises.size(); i++)
                pw.print(exercises.get(i).toString());
            } catch (IOException e) { }
                
        //re-wrtie all exercsie plan data
        try(FileWriter fw = new FileWriter("//Users//Sophie//NetBeansProjects//projectTest2//src//exPlans.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)) {
            for(int r = 0; r < plans.length; r++){
                pw.println((String)fm.getItemAt(r));
                for(int c = 0; c < 7; c++){
                    pw.println(plans[r][c]);
                }
            }
            } catch (IOException e) { }
    }
}