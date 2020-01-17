package mealp;

public class Exercise {
    
    private String name;
    private int reps;
    private int cals;
    private String[] rating = new String[4];
    private String[] completed = new String[4];
    
    public Exercise(){
        name = "";
        reps = -1;
        cals = -1;
        for(int i = 0; i < 4; i++) {
            rating[i] = "";
            completed[i] = "false";
        }
    }
    
    public Exercise(String n, int r, int c, String[] rt, String[] cmp){
        name = n;
        reps = r;
        cals = c;
        for(int i = 0; i < 4; i++) {
            rating[i] = rt[i];
            completed[i] = cmp[i];
        }
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
    
    public String getRating(int ind){
        return rating[ind];
    }
    
    public String getCompletion(int ind){
        return completed[ind];
    }
    
    public void setRating(String rt, int ind){
        rating[ind] = rt;
    }
    
    public void setCompletion(String c, int ind){
        completed[ind] = c;
    }
    
    public String toString(){
        String result = name + "," + reps + "," + cals;
        for(int i = 0; i < 4; i++){
            result += "," + rating[i] + "," + completed[i];
        }
        result += "\n";
        return result;
    }    
}