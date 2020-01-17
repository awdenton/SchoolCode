package mealp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sophie
 */
public class FamilyMemberManager {
    
    foodInfo aboutFood;
    mealInfo aboutMeals; 
    
    public ArrayList<FamilyMember> users = new ArrayList<FamilyMember>();
    public FamilyMember currentUser;
    //public int currentUser;
    public int current;         //index for pointing to user arr
    
    public double[][] foodRatingArr = new double[3][10];
    public double[] ratingArr = new double[10];
    
    /**
     * default constructor
     */
    void FamilyMemberManager() throws FileNotFoundException{
        aboutFood = new foodInfo();
        aboutFood.getFoods();               //might be part of problem
        aboutMeals = new mealInfo();
    }
    
    /**
     * takes updated weekly weight from EnterProgressPanel
     * and writes the information to a CSV file in accordance with
     * which family member is currently logged in.
     */
    void recordProgress(String newWeight){
        //get the one value from the textField...
        //add to the weightHistory[] (?)
        //write the new weight to the file...append to a certain thing...
        currentUser.setCurrentWeight(newWeight);
        //updateUsers();
    }
    
    /**
     * returns the weight gained/lost as a string of the currentUser.
     * @return 
     */
    String getWeightChange(){
        if(currentUser.getCurrentWeight() > currentUser.getStartWeight()){
            double ans = (currentUser.getCurrentWeight() - currentUser.getStartWeight());
            return Double.toString(ans);
        }
        else{
            double ans = (currentUser.getStartWeight() - currentUser.getCurrentWeight());
            return Double.toString(ans);
        }
    }
    
    String[] getFoodList(){
        //return aboutFood.getFoods();            //NULL POINTER EXCEPTION
        String[] arr = {"Apple","Banana","Pizza","Pasta",
            "Chicken","Mashed Potato","Deep Friend Twinkie",
            "Brussel Sprouts","Coffe","Juice"};
        return arr;
    }  
    
    void setFoodRatings(){
        
        
    }
            
    
    
    /**
     * takes parameters to access correct exercise 
     * @return 
     */
    String showExercisePlan(){
        String exPlan = "";
        //exPlan = exerciseList.get();
        return exPlan;
    }
    
    /**
     * 
     * @return 
     */
    String showMealPlan(){
        String mealPlan = "";
        
        return mealPlan;
    }
    
    /**
     * 
     * @return 
     */
    String giveFeedback(){
        String feedback = "";
        return feedback;
    }
    
    /**
     * takes information from ratings entered on the rate exercise panel
     * and writes it to the CSV file.
     */
    void updateExerciseRating(){
        //take in data from GUI textFields 
        //write that data to a CSV file
    }
    
    /**
     * takes information from ratings entered on the rate meal panel
     * and writes it to the CSV file.
     */
    void updateMealRating(){
        //take in data from GUI textFields and drop down menus
        //write that data to a CSV file
    }
    
    /**
     * reads CSV file with list of all users registered in format
     * NAME,USERNAME,MP,PT,
     * @throws FileNotFoundException 
     */
    public void importFile() throws FileNotFoundException{
        Scanner in = new Scanner(new File("//Users//Sophie//NetBeansProjects//projectTest2//src//mealp//users.csv"));
        while(in.hasNextLine()){
            FamilyMember newFam = new FamilyMember();
            String line = in.nextLine();
            String[] famArr = line.split(",");
            
            newFam.setName(famArr[0]);
            newFam.setUsername(famArr[1]);
            if(famArr[2].compareTo("true") == 0){
                newFam.setMealPlanner();
            }
            if(famArr[3].compareTo("true") == 0){
                newFam.setPersonalTrainer();
            }
            newFam.setStartWeight(famArr[4]);
            newFam.setCurrentWeight(famArr[5]);
            newFam.setWeightGoal(famArr[6]);
            newFam.setExPrefs(famArr[7]);
            newFam.setFoodPrefs(famArr[8]);
            
            users.add(newFam);
        }
    }
    
    /**
     * writes the users data to the same file it reads from 
     */
    public void saveUsers() throws IOException{
        File myfile = new File("//Users//Sophie//NetBeansProjects//projectTest2//src//users.csv");
        myfile.delete();
        FileWriter f = new FileWriter(myfile);
        
        for(int i = 0; i < users.size(); i++){
           f.write(users.get(i).getAttributes()+"\n");
        }
        f.close();
    }
    
    public void saveFoodRatings() throws IOException{
        File myfile = new File("//Users//Sophie//NetBeansProjects//projectTest2//src//mealp//rate.csv");
        myfile.delete();
        FileWriter f = new FileWriter(myfile);
        for(int i = 0; i < users.size(); i++){
            for(int j = 0; j < 10; j++){
                System.out.println(foodRatingArr[i][j]);
                f.write(foodRatingArr[i][j]+","); //
            }
        f.write("\n");
        }
    f.close();
    }
    
    public void saveExRatings() throws IOException{
        File myfile = new File("//Users//Sophie//NetBeansProjects//projectTest2//src//mealp//exrate.csv");
        myfile.delete();
        FileWriter f = new FileWriter(myfile);
        for(int i = 0; i < users.size(); i++){
            
        }
    }
    
    /**
     * receives a username as a parameter and 
     * returns the matching user.
     * @param str
     * @return 
     */
    FamilyMember getFamilyMember(String str){
        FamilyMember temp = null;
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getUsername().compareTo(str) == 0){
                temp = users.get(i);
                i = users.size();
            }
        }
        return temp;
    }
    
    /**
     * method to check whether or not the username is a valid login...
     * @param user
     * @return 
     */
    boolean checkLogin(String str){
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getUsername().compareTo(str) == 0){
                currentUser = users.get(i);
                current = i;
                return true;
            }
        }
        return false;
    }
  
    public static void main(String[] args) {
        //instantiate the class soph
        FamilyMemberManager man = new FamilyMemberManager();
        FamilyMember myFam = new FamilyMember();
        
    }
}
