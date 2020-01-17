
public class FibTopBot {
    
    public int[] list;
    
    public static void main(String[] args) {
        FibTopBot test = new FibTopBot(50);
        
        System.out.println(test.fibDyn(9, test.list));
        
        System.out.println(test.listStr());
        
        System.out.println(test.fibDyn(7, test.list));
    }
    
    FibTopBot(int n){
        list = new int[n];
        
        for(int i = 0; i < list.length; i++){
            list[i] = -1;
        }
    }
    
    public int fibDyn(int n, int[] fibList){
        int fibValue;
        
        if(fibList[n] >= 0)
            return fibList[n];
        
        if(n <= 1)
            fibValue = n;
        else
            fibValue = fibDyn(n-1, fibList) + fibDyn(n-2, fibList);
        
        fibList[n] = fibValue;
        return fibValue;
    }
    
    public String listStr(){
        String result = "";
        for(int i = 0; i < list.length; i++){
            result += list[i] + " ";
        }
        return result;
    }    
}