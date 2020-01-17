
import java.util.PriorityQueue;

public class HuffmanTree{
    
    public int[] frequencies = {10, 15, 12, 3, 4, 13, 1};
    public String[] characters = {"a", "e", "i", "s", "t", "sp", "nl"};
    public HuffNode[] nodeArray = new HuffNode[7];
    public HuffNode root;
    public PriorityQueue<HuffNode> nodeQueue = new PriorityQueue<>();
    
    public static void main(String[] args) {
        HuffmanTree test = new HuffmanTree();
        
        test.printTree(test.root, 0);
        
        String teaCode = test.getCode("tea");
        System.out.println("Encoding for 'tea' : " + teaCode);
        System.out.println("Decoding of " + teaCode+ " : " + test.getChar(teaCode));
    }
    
    /*
     * Huffman Node class, has fields for a character and how often it occurs,
     * and pointers for its parent and children as needed
     */
    public class HuffNode implements Comparable<HuffNode> {
        public HuffNode parent, lchild, rchild;
        public int freq;
        public String data;
        
        public HuffNode(int f, String d, HuffNode prt, HuffNode lc, HuffNode rc){
            freq = f;
            data = d;
            parent = prt;
            lchild = lc;
            rchild = rc;
        }
        
        @Override
        /*
         * Returns an integer indicating if the calling node is less than;
         * greater than, or equal to the rhs node
         */
        public int compareTo(HuffNode rhs) {
            return freq - rhs.freq;
        }
    }
    
    /*
     * Constructor for Huffman Tree. Assuming all data has been entered
     * correctly, simply calls createTree
     */
    public HuffmanTree(){
        this.createTree();
    }
    
    /*
     * Finds the huffman representation of a string in 1's and 0's
     */
    public String getCode(String target){
        String result = "";
        HuffNode node = null;
        //Loop to process each letter of the target string
        for(int i = 0; i < target.length(); i++){
            //Look for the node that contains the target character in
            //the array of nodes
            for(int j = 0; j < nodeArray.length; j++){
                if(nodeArray[j].data.equals(target.substring(i,i+1)))
                    //Once found, set our node pointer to that HuffNode
                    node = nodeArray[j];
            }
            //If character is not found, return null
            if(node == null)
                return null;
            
            String temp = "";
            //Work your way back up the tree to build the code
            while(node != root){
                if(node == node.parent.lchild)
                    temp = "0".concat(temp);
                if(node == node.parent.rchild)
                    temp = "1".concat(temp);
                node = node.parent;
            }
            result += temp;
        }
        return result;
    }
    
    /*
     * Convert a binary string back into its alphanumeric form
    */
    public String getChar(String code){
        String result = "";
        HuffNode node = root;
        
        while(code.length() > 0){
            //Check if we are on a leaf
            //If we are, add its character to the result
            //and move back to the root of the tree
            if(!node.data.equals("")){
                result += node.data;
                node = root;
            }
            //if we aren't on a leaf, move left or right down the tree 
            //based on the first character in code, and removing the 
            //first character of 
            else if(code.charAt(0)=='0'){
                node = node.lchild;
                code = code.substring(1);
            }
            else if(code.charAt(0)=='1'){
                node = node.rchild;
                code = code.substring(1);
            }            
        }
        result += node.data;
        return result;
    }
    
    /*
     * Builds a Huffman tree using pre-defined values
     */
    private void createTree(){
        //Create the Huffman Nodes that will be used to build the tree
        for(int i = 0; i < frequencies.length; i++){
            HuffNode node = new HuffNode(frequencies[i], characters[i], null, null, null);
            nodeArray[i] = node;
            nodeQueue.add(node);
        }
        
        while(nodeQueue.size() > 1){
            HuffNode rightHN = nodeQueue.remove();
            HuffNode leftHN = nodeQueue.remove();
            HuffNode poppa = new HuffNode(leftHN.freq+rightHN.freq, "", null, leftHN, rightHN);
            leftHN.parent = poppa;
            rightHN.parent = poppa;
            nodeQueue.add(poppa);
        }
        
        root = nodeQueue.remove();        
    }
    
    /*
     * Prints the built tree showing depth and frequency 
     * of each node using pre-order traversal 
     */
    public void printTree(HuffNode base, int depth){
        //Display dashes to indicate the depth of current node
        for(int i = 0; i < depth; i++){
            System.out.print("- ");
        }
        //Display the frequency of the current node
        System.out.print(base.freq);
        
        //If the node has data, display the data
        if(!base.data.equals("")){
            System.out.print(" : " + base.data);
        }
        //Jump a line
        System.out.println("\n");
        
        //recursively print any left children
        if(base.lchild != null){
            printTree(base.lchild, depth + 1);
        }
        //recursively print any right children
        if(base.rchild != null){
            printTree(base.rchild, depth + 1);
        }
    }    
    
}