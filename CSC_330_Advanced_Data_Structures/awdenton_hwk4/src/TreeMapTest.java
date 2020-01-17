import java.util.*;

public class TreeMapTest{
    
    public static <KeyType,ValueType> void printMap(String msg, Map< KeyType,ValueType > m){
        System.out.println(msg + ":");
        Set<Map.Entry<KeyType,ValueType>> entries = m.entrySet();
        
        for(Map.Entry<KeyType,ValueType> thisPair : entries ){
            System.out.print(thisPair.getKey() + ": ");
            System.out.println(thisPair.getValue());
        }
    }
    
    public static void main(String[] args) {
        Map<String,String> phone1 = new TreeMap<String,String>();
        
        phone1.put("John Doe","212-555-1212");
        phone1.put("Jane Doe","312-555-1212");
        phone1.put("Holly Doe","213-555-1212");
        phone1.put("Susan Doe","617-555-1212");
        phone1.put("Jane Doe","unlisted");
        
        System.out.println("phone1.get(\"JaneDoe\"): " + phone1.get("Jane Doe"));
        System.out.println("\nThe map is: ");
        printMap("phone1", phone1);
    }
}