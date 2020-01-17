//Project name: Graph with adjacency matrix
//File name: awdenton_hwk5.java
//Student name: Aaron Denton
//Date: 4/17/2016


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class awdenton_hwk5 {
    
    public static int[][] adjMatrix = new int[5][5];
    static Map<String,Integer> vertexMap = new HashMap<>();
    
    public static void main(String[] args) {        
        for(int r = 0; r < adjMatrix.length; r++){
            for(int c = 0; c < adjMatrix[r].length; c++){
                adjMatrix[r][c] = -1;
            }
        }
                
        vertexMap.put("A",0);
        vertexMap.put("B",1);
        vertexMap.put("C",2);
        vertexMap.put("D",3);
        vertexMap.put("E",4);
        
        addEdge("D", "C", 10);
        addEdge("A", "B", 12);
        addEdge("D", "B", 23);
        addEdge("A", "D", 87);
        addEdge("E", "D", 43);
        addEdge("B", "E", 11);
        addEdge("C", "A", 19);
        
        changeWeight("A","B",13);
        
        removeEdge("A","D");
        
        printGraphBFS("A");
    }
        
    public static void changeWeight(String a, String b, int w){
        adjMatrix[vertexMap.get(a)][vertexMap.get(b)] = w;
    }
    
    public static void addEdge(String a, String b, int w){
        adjMatrix[vertexMap.get(a)][vertexMap.get(b)] = w;
    }
    
    public static void removeEdge(String a, String b){
        adjMatrix[vertexMap.get(a)][vertexMap.get(b)] = -1;
    }
    
    public static void printGraphBFS(String root){
        Integer start = vertexMap.get(root);
        
        Queue<Integer> q = new LinkedList<Integer>();
        int[] seen = new int[adjMatrix.length];
        q.add(start);
        
        while(!q.isEmpty()){
            Integer i = q.remove();
            
            for(int c = 0; c < adjMatrix[i].length; c++){
                if(adjMatrix[i][c] != -1 && seen[i] != 1){
                    System.out.println(getKey(vertexMap, i) + " " + getKey(vertexMap, c) + " " + adjMatrix[i][c]);
                    q.add(c);
                }
            }
            seen[i] = 1;
        }
    }
    
    private static String getKey(Map hm, Integer val){
        for(Object x : hm.keySet()){
            if(hm.get(x).equals(val))
                return (String)x;
        }
        return null;
    }
}