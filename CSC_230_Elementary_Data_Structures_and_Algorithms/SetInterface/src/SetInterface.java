/**
 * 
 * This is my own work: Aaron Denton
 *           ¯\_(ツ)_/¯
 *             10--27
 * 
 * @param <T>
 */

public interface SetInterface<T> {
    public boolean addItem(T item);
    public boolean contains(T item);
    public int getSize();
    public boolean remove(T item);
    public T remove();
    public T[] toArray();
    @Override
    public String toString(); 
    public SetInterface<T> union(SetInterface<T> rhs);
    public SetInterface<T> intersection(SetInterface<T> rhs);
    public SetInterface<T> difference(SetInterface<T> rhs);
    public SetInterface<T> symmDifference(SetInterface<T> rhs);
}


/**
 * Implements the set interface as an array
 * @author Aaron
 * @param <T> 
 */
class ArraySet<T> implements SetInterface<T>{
    
    private T[]arr;
    private int numItems;
    
    public ArraySet() {
        arr = (T[])new Object[10];
        numItems = 0;
    }

    /**
     * Returns a string containing the contents of the array
     * @return 
     */
    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < numItems; i++){
            result += arr[i] + " ";
        }
        return result;
    }
        
    /**
     * Adds item to the ArraySet.
     * @param item
     */
    @Override
    public boolean addItem(T item) {
        //Make sure the Set does not already contain the item
        if(!this.contains(item)) {
            //Make sure there is enough room for the item. If there isn't
            //resize the array
            if(numItems == arr.length)
                this.ensureCap();
            //Add the item to the end and increment numItems
            arr[numItems++] = item;
            //return true
            return true;
        }
        //if item was already in set, return false
        return false;
    }
    
    /**
     * Returns true if the ArraySet contains an item, false if not
     * @param item
     * @return 
     */
    @Override
    public boolean contains(T item) {
        //iterate through the array and look for the item
        for(int i = 0; i < numItems; i++){
            //if it is found, return true
            if(item.equals(arr[i]))
                return true;
        }
        return false;
    }

    /**
     * Returns then current number of items in the ArraySet
     * @return 
     */
    @Override
    public int getSize() {
        return this.numItems;
    }

    /**
     * Removes a specific item from the ArraySet if it exists
     * @param item
     * @return 
     */
    @Override
    public boolean remove(T item) {
        //find where the item is in the array
        int index = this.findIndex(item);
        //if it is not in the array, return false
        if(index == -1)
            return false;
        //otherwise, move the last item in the array to the removed items
        //spot, decrement numItems and return true
        else{
            arr[index] = arr[--numItems];
            return true;
        }
    }
    
    /**
     * Returns the last item in the array, decrements numItems
     */
    @Override
    public T remove() {
        if(numItems == 0)
            return null;
        return this.arr[--numItems];
    }

    
    /**
     * Returns a new array containing the data from the ArrayList
     */
    @Override
    public T[] toArray() {
        T[] result = (T[])new Object[numItems];
        for(int i = 0; i < numItems; i++)
            result[i] = arr[i];
        return result;
    }

    /**
     * Provides the union of two SetInterfaces, returned as an ArraySet
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> union(SetInterface<T> rhs) {
        //Declare return SetInterface
        SetInterface<T> answer = new ArraySet();
        //Add the items from the calling ArraySet to the return Set
        for (int i = 0; i < this.numItems; i++){
            answer.addItem(arr[i]);
        }
        //Convert the other set to an array in case it isnt and to 
        //ease iteration
        T[] other = rhs.toArray();
        //Add the items from RHS to return Set
        for (int j = 0; j < rhs.getSize(); j++){
            answer.addItem(other[j]);
        }
        //Return the answer
        return answer;        
    }

    /**
     * Returns intersection of two SetInterfaces, returned as an ArraySet
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> intersection(SetInterface<T> rhs) {
        //Declare return SetInterface
        SetInterface<T> result = new ArraySet();
        //If the item is in both sets, add it to the result
        //Iterate through the calling Set
        for (int i = 0; i < numItems; i++){
            //if the item is also in RHS, add it to the result
            if(rhs.contains(arr[i]))
                result.addItem(arr[i]);
        }
        return result;
    }

    /**
     * Returns the difference of two SetInterfaces, or everything in the
     * calling Set without items also in the parameter set
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> difference(SetInterface<T> rhs) {
        //create return SetInterface
        SetInterface<T> result = new ArraySet();
        //Look through the calling set
        for(int i = 0; i < numItems; i++){
            //if the item is NOT also in the param set, add it to result
            if(!rhs.contains(arr[i]))
                result.addItem(arr[i]);
        }
        return result;
    }

    /**
     * Returns the symmetric difference of two sets, or the Exclusive Or, meaning
     * items that are only in one set, not both sets
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> symmDifference(SetInterface<T> rhs) {
        //to find the symmetric difference, return the union of the
        //calling and param Set's differences
        return this.difference(rhs).union(rhs.difference(this));
    }
    
    /**
     * Increases the size of the array
     */
    private void ensureCap(){
        T[] temp = (T[])new Object[arr.length * 2];
        for(int i = 0; i < numItems; i++){
            temp[i] = this.arr[i];
        }
        this.arr = temp;
    }
    
    /**
     * Finds index of requested item in arr. If item is not found, returns -1
     * @param item
     * @return 
     */
    private int findIndex(T item){
        for(int i = 0; i < numItems; i++){
            if(arr[i].equals(item))
                return i;
        }
        return -1;
    }
    
}


/**
 * Implements the set interface as a linked list
 * @author Aaron
 * @param <T> 
 */
class LinkedSet<T> implements SetInterface<T>{
    
    private Node first;
    private int numItems;
    
    /**
     * Defines the Node class to be used in the linked list
     */
    private class Node{
        T value;
        Node next;
        
        public Node(T val){
            this.value = val;
            this.next = null;
        }
        
        public Node(T val, Node ref){
            this.value = val;
            this.next = ref;
        }
    }
    
    public LinkedSet(){
        first = null;
        numItems = 0;        
    }

    /**
     * Returns a string displaying the values of the nodes in the list
     * @return 
     */
    @Override
    public String toString(){
        String result = "";
        Node n = first;
        while(n != null){
            result += n.value + " ";
            n = n.next;
        }
        return result;
    }
    
    /**
     * Adds an item to the linked list
     * @param item
     * @return 
     */
    @Override
    public boolean addItem(T item) {
        //Make sure the item is not already in the set
        if(!this.contains(item)){
            //create a new node with the item, and the current first node
            Node n = new Node(item, first);
            //update first node reference
            first = n;
            //increment numItems
            numItems++;
            return true;
        }
        //if item is already in the set return false
        return false;
    }
    
    /**
     * Looks for an item in the set
     * @param item
     * @return 
     */
    @Override
    public boolean contains(T item) {
        Node n = first;
        //walk through the linked list untill you reach the end
        while(n != null) {
            //see if the item exists in the set, if it does return true
            if(n.value.equals(item))
                return true;
            //move to the next node
            n = n.next;
        }
        //if not found return false
        return false;
    }
    
    /**
     * Returns the size of the linked list
     * @return 
     */
    @Override
    public int getSize() {
        return numItems;
    }
    
    /**
     * Removes a specific item from the set
     * @param item
     * @return 
     */
    @Override
    public boolean remove(T item) {
        //find the node with the value
        Node n = getRefTo(item);
        //make sure it actually is in the set
        if(n == null)
            return false;
        //reassign the value from the first node to the removed node
        n.value = first.value;
        //take out the first node
        remove();
        return true;
    }

    /**
     * Returns a reference to the value in the first node, then remove it
     * @return 
     */
    @Override
    public T remove() {
        //make sure there is something in the set
        if (first == null)
            return null;
        //make a reference to the data in the first node, then
        //update the reference to the second node
        T val = first.value;
        first = first.next;
        //return the data
        numItems--;
        return val;
    }

    /**
     * Convert the linked list to an array
     * @return 
     */
    @Override
    public T[] toArray() {
        //create the return array
        T[] result = (T[])new Object[this.getSize()];
        //make a count variable to move through the array and a reference
        //to the first node
        int index = 0;
        Node n = first;
        //copy the node data to the array
        while(n != null){
            result[index++] = n.value;
            n = n.next;
        }
        return result;
    }

    /**
     * Creates a union of two set interfaces, returns a linked list
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> union(SetInterface<T> rhs) {
        //create return SetInterface
        SetInterface<T> result = new LinkedSet();
        //add the items from the calling set to the result set
        Node n = first;
        while(n != null){
            result.addItem(n.value);
            n = n.next;
        }
        
        //convert rhs to an array so we know what we're dealing with
        T[] rhsArr = rhs.toArray();
        //add the items from rhsArr to result
        for(int i = 0; i < rhsArr.length; i++){
            result.addItem(rhsArr[i]);
        }
        
        return result;
    }

    /**
     * Returns the intersection of two SetInterfaces, returns a LinkedList
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> intersection(SetInterface<T> rhs) {
        //create the return SetInterface
        SetInterface<T> result = new LinkedSet();
        Node n = first;
        //move through the modes
        while(n != null){
            //check if the item is also in rhs
            if(rhs.contains(n.value))
                result.addItem(n.value);
            n = n.next;
        }
        return result;
    }

    /**
     * Returns the difference of two SetInterfaces as a Linked List
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> difference(SetInterface<T> rhs) {
        SetInterface<T> result = new LinkedSet();
        Node n = first;
        while(n != null){
            //make sure the item is not also in rhs
            if(!rhs.contains(n.value))
                result.addItem(n.value);
            n = n.next;
        }
        return result;
    }

    /**
     * Returns the exclusive or of two SetInterfaces as a Linked List
     * @param rhs
     * @return 
     */
    @Override
    public SetInterface<T> symmDifference(SetInterface<T> rhs) {
        return this.difference(rhs).union(rhs.difference(this));
    }
    
    /**
     * Returns a reference to a specific node with a given data value
     * If item is not found returns null
     * @param item
     * @return 
     */
    private Node getRefTo(T item){
        Node n = first;
        while(n != null && !n.value.equals(item))
            n = n.next;
        return n;
    }
}