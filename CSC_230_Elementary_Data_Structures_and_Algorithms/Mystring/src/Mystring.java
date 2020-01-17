/*
*   This is my own work: Aaron Denton
*   Creates a String like object
*/

public class Mystring {
    private char[] strChars;
    private int curr_length;
        
    /**
    *   Null constructor
    */
    public Mystring() {
        strChars = null;
        curr_length = 0;
    }
    
    /**  Creates a new Mystring using a String parameter
     * 
     *   @param Mystring s 
     */
    public Mystring(String s) {
        strChars = new char[s.length()];
        for (int i = 0; i < s.length(); i++)
            strChars[i] = s.charAt(i);
        curr_length = s.length();
    }
    
    /**  Creates a new Mystring as a copy of another Mystring
     * 
     *   @param Mystring s 
     */
    public Mystring(Mystring s) {
        this.curr_length = s.curr_length;
        strChars = new char[s.curr_length];
        for (int i = 0; i < s.curr_length; i++)
            this.strChars[i] = s.strChars[i];
    }
    
    /**  Returns the current length of the calling object
     * 
     *   @return curr_length
     */
    public int length() {
        return this.curr_length;
    }
    
    /**  Increases the size of the calling objects strChar
     */
    private void ensureCapacity() {
        //If the calling objects strChar was left null, this first if
        //statement initializes it to 1
        if (this.strChars == null)
            strChars = new char[1];
        //Otherwise it checks to see if the length of strChars equals
        //curr_length, and if it does then creates a new char array double the
        //size of strChars and copies the contents of strChars into it
        else if (this.curr_length == this.strChars.length){
            //Increases the size of strChar. I'm leaving the + 1 on this
            //statement because in the case that a Mystring is initialized
            //as new Mystring("") then strChars.length = 0 and 2 * 0 is still 0
            char[] temp = new char [this.curr_length * 2 + 1];
            for (int i = 0; i < this.curr_length; i++)
                temp[i] = this.strChars[i];
            //Finally we update the reference on strChars to the newly created
            //char array
            this.strChars = temp;
        }
    }
        
    /**Returns a string representation of strChars
     * 
     * @return String
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < this.curr_length; i++)
            result += this.strChars[i];
        return result;
    }
    
    /**
     * Concatenates two Mystring objects
     * @param s
     * @return Mystring result
     */
    public Mystring concat (Mystring s) {
        //Create a new null Mystring to return
        Mystring result = new Mystring();
        //Set its curr_length to the sum of the calling objects and parameters
        //curr_lengths
        result.curr_length = this.curr_length + s.curr_length;
        //Initialize strChars to an array the size of result.curr_length
        result.strChars = new char[result.curr_length];
        //Copies the contents of the calling objects strChars into the
        //beginning of the results strChars
        for (int i = 0; i < this.curr_length; i++)
            result.strChars[i] = this.strChars[i];
        //Copies the contents of the parameters strChars into the results
        //strChars beginning after the final index of the calling object
        for (int j = 0; j < s.curr_length; j++)
            result.strChars[j + this.curr_length] = s.strChars[j];
        return result;        
    }
    
    /**
     * Concatenates a string onto a Mystring object
     * @param s
     * @return Mystring result
     */
    public Mystring concat (String s) {
        Mystring result = new Mystring();
        result.curr_length = this.curr_length + s.length();
        result.strChars = new char[result.curr_length];
        for (int i = 0; i < this.curr_length; i++)
            result.strChars[i] = this.strChars[i];
        for (int j = 0; j < s.length(); j++)
            result.strChars[j + this.curr_length] = s.charAt(j);
        return result;
    }
    
    /**
     * Compares two Mystrings to see if they are equivalent to each other
     * @param s
     * @return boolean true if equal false if not
     */
    public boolean equals(Mystring s) {    
        //If the two Mystrings are of different lengths, go ahead
        //and return false
        if (this.curr_length != s.curr_length)
            return false;
        //Compares the elements of the two strChars against each other. If
        //there are any differences, returns false
        for (int i = 0; i < s.curr_length; i++)
            if (this.strChars[i] != s.strChars[i])
                return false;
        //If no differences are found between the two objects, return true
        return true;
    }
    
    /**
     * Compares two Mystrings. If the calling Mystring comes first alphabetically
     * returns -1. If it comes last alphabetically returns 1. If they are the
     * same returns 0.
     * @param s
     * @return integer
     */
    public int compareTo(Mystring s) {
        //Compares the elements of the two arrays against each other, using the
        //AND statement to avoid an OutOfBounds error on either array
        for (int i = 0; i < this.strChars.length && i < s.strChars.length; i++) {
            //If the value of the index i of the calling objects strChars is
            //less than the parameters strChars, meaning it comes first
            //alphabetically, return -1
            if (this.strChars[i] < s.strChars[i])
                return -1;
            //It the value is greater, meaning it comes later alpabetically,
            //then return 1
            else if (this.strChars[i] > s.strChars[i])
                return 1;
        }        
        //If no differences are found in the for loop, compare the sizes of
        //the two objects. If the calling object is shorter, return -1. If it
        //is longer then return 1. e.g. aa.compareTo(aaa) would return -1
        if ( this.curr_length < s.curr_length)
            return -1;
        else if (this.curr_length > s.curr_length)
            return 1;
        
        //If no differences at all are found between the two objects, return 0
        return 0;
    }
    
    /**Returns the char at a given index of strChars. WARNING! Calling an 
     * index greater than strChars.length - 1 will cause a crash
     * @param x
     * @return char at x
     */
    public char get(int x) {
        return this.strChars[x];
    }
    
    /**Returns a Mystring object with all chars converted to uppercase letters
     * 
     * @return Mystring
     */
    public Mystring toUpper() {
        Mystring result = new Mystring(this);
        for (int i = 0; i < this.curr_length; i++)
            //If statement checks for lowercase letters
            if (result.strChars[i] >= 'a' && result.strChars[i] <= 'z')
                //If one is found, converts it to uppercase
                result.strChars[i] -= 32;
        return result;
    }
    
    /**Returns a Mystring object with all chars converted to lowercase letters
     * 
     * @return 
     */
    public Mystring toLower() {
        Mystring result = new Mystring(this);
        for (int i = 0; i < this.curr_length; i++)
            //If statement checks for uppercase letters
            if (result.strChars[i] >= 'A' && result.strChars[i] <= 'Z')
                //If one is found, converts it to lowercase
                result.strChars[i] += 32;
        return result;
    }
    
    /**Returns the index of the first occurence of a given
     * Mystring in the calling my string
     * 
     * @param s
     * @return 
     */
    public int indexOf(Mystring s) {
        //Begins checking at the beginning of strChars, and goes through to
        //the last index the parameter could logically begin at
        for (int i = 0; i <= this.curr_length - s.curr_length; i++)
            //Using the substring and equals methods, compares each block
            //of the calling object to the parameter until a match is found,
            //at which point the current index is returned
            if(this.substring(i, i + s.curr_length).equals(s))
                return i;
        //If the parameter is not found in the calling object, returns -1
        return -1;
    }
    
    /**Very similar to indexOf, only it checks from the
     * back of the object instead of the front
     * 
     * @param s
     * @return 
     */
    public int lastIndexOf(Mystring s) {
        for (int i = this.curr_length - s.curr_length; i >= 0; i--)
            if(this.substring(i, i + s.curr_length).equals(s))
                return i;
        return -1;
    }
    
    /**Returns a chunk of the calling object beginning with index x of
     * strChars and continuing to the end of the array
     * 
     * @param x
     * @return 
     */
    public Mystring substring(int x) {
        Mystring sub = new Mystring();
        //Defines the length of the returned Mystring
        sub.curr_length = this.curr_length - x;
        //Intializes the array on the returned Mystring
        sub.strChars = new char[sub.curr_length];
        //Copies the relevant content from the calling object
        //to the returned Mystring
        for (int i = 0; i < sub.curr_length; i++)
            sub.strChars[i] = this.strChars[i+x];
        return sub;
    }
    
    /**Returns a chunk of the calling object beginning with index x of
     * strChars and going to, but not including, the end index y
     * 
     * @param x
     * @param y
     * @return 
     */
    public Mystring substring(int x, int y) {
        Mystring sub = new Mystring();
        sub.curr_length = y - x;
        sub.strChars = new char[sub.curr_length];
        for (int i = 0; i < sub.curr_length; i++)
            sub.strChars[i] = this.strChars[i+x];
        return sub;
    }
}