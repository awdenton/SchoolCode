package mealp;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sophie
 */
public class FamilyMember{
        public String name;
        public String username;
        public double startingWeight;
        public double currentWeight;
        public double weightGoal;
        //public ArrayList<Double> weightHistory = new ArrayList<Double>();  
        public String exercisePrefs;     
        public String foodPrefs;         
        public boolean personalTrainer;
        public boolean mealPlanner;

        void setName(String str){   this.name = str;}
        String getName(){   return this.name;}

        void setUsername(String str){   this.username = str;}
        String getUsername(){   return this.username;}

        void setStartWeight(String str){
            Double num = Double.parseDouble(str);
            this.startingWeight = num;
        }
        double getStartWeight(){    return this.startingWeight;}
        
        void setCurrentWeight(String str){
            Double num = Double.parseDouble(str);
            this.currentWeight = num;
        }
        double getCurrentWeight(){  return this.currentWeight;}

        void setWeightGoal(String str){
            Double goal = Double.parseDouble(str);
            this.weightGoal = goal;
        }
        double getWeightGoal(){ return this.weightGoal;}

        void setFoodPrefs(String prefs){    this.foodPrefs = prefs;}
        String getFoodPrefs(){  return this.foodPrefs;}
        
        void setExPrefs(String prefs){    this.exercisePrefs = prefs;}
        String getExPrefs(){    return this.exercisePrefs;}  
        
        void setPersonalTrainer(){  this.personalTrainer = true;}
        boolean isPersonalTrainer(){    return this.personalTrainer;}

        void setMealPlanner(){  this.mealPlanner = true;}
        boolean isMealPlanner(){ return this.mealPlanner;}

        String getAttributes(){
            return this.getName()+","+this.getUsername()+","+
                this.isMealPlanner()+","+this.isPersonalTrainer()
                +","+this.getStartWeight()+","+this.getCurrentWeight()
                +","+this.getWeightGoal()+","+this.getExPrefs()
                +","+this.getFoodPrefs();
        }
        
        void FamilyMember(){
            this.name = "";
            this.username = "";
            this.currentWeight = 0.0;
            this.weightGoal = 0.0;
            this.exercisePrefs = null;
            this.foodPrefs = null;
            this.personalTrainer = false;
            this.mealPlanner = false;
        }
        
        /**
        * when the user enters their updated weight every week.
        * called when button to save weight is clicked on enterProgress panel
        * @param newWeight
        */
       void updateWeight(double newWeight){
           //currentWeight is assigned as newWeight 
           //newWeight is added to weightHistory[]
           String weight = Double.toString(newWeight);
           this.setCurrentWeight(weight);

       }

       /**
        * 
        */
       void updateGoals(double goal){
           //update weightGoal
       }

       /**
        * 
        */
       void updatePref(){

       }

        public void main(){
            FamilyMember go = new FamilyMember();
        }
    }
