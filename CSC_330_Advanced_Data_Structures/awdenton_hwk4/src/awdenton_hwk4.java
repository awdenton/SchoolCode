import java.util.*;

public class awdenton_hwk4{
    
    public static void main(String[] args) {
        Map<String,Double> accts = new TreeMap<>();
        
        accts.put("John Doe",3123.5);
        accts.put("Jane Doe",1200.0);
        accts.put("Susan Doe",3124.7);
        accts.put("Holly Doe",-97.1);
        accts.put("Bill Doe",-12.0);
        
        printMap(accts);
        
        accts.replace("John Doe", accts.get("John Doe") + 1000);
        accts.replace("Susan Doe", accts.get("Susan Doe") - 2000);
        
        accts.remove("Bill Doe");
        accts.remove("Holly Doe");
        
        accts.put("Sally Doe", 15.0);
        
        printMap(accts);
        
    }
    
    public static <KeyType,ValueType> void printMap(Map<KeyType,ValueType> m){        
        for(Map.Entry<KeyType,ValueType> pair : m.entrySet()){
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
        System.out.println("");
    }
    
}