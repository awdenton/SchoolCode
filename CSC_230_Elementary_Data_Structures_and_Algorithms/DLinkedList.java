
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is my own work: Aaron Denton
 *
 * @author awdenton
 */
public class DLinkedList<T extends Comparable<T>> {

    public static void main(String[] args) throws FileNotFoundException {

        DLinkedList<String> lst1 = new DLinkedList<>();
        DLinkedList<String> lst2 = new DLinkedList<>();

        Scanner darkly = new Scanner(new File("text1.in"));
        String str;

        while (darkly.hasNext()) {
            str = darkly.next();
            str = cleanUp(str);
            lst1.insertOrderUnique(str);
        }
        darkly.close();

        darkly = new Scanner(new File("text2.in"));
        while (darkly.hasNext()) {
            str = darkly.next();
            str = cleanUp(str);
            lst2.insertOrderUnique(str);
        }

        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);

        DLinkedList combined = lst1.merge(lst2);

        System.out.println("\nAFTER MERGE");
        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        System.out.println("\n" + combined);
    }

    /**
     * ASSIGNED
     *
     * @param str
     * @return str in all lower case with LEADING and TRAILING non-alpha chars
     * removed
     */
    public static String cleanUp(String str) {
        //first, make the string lowercase
        str = str.toLowerCase();

        //check if the last character is not a letter
        if (!(str.charAt(str.length() - 1) >= 'a' && str.charAt(str.length() - 1) <= 'z')) //if it isnt, cut off the last character
        {
            str = str.substring(0, str.length() - 1);
        }
        //do the same check for the first character
        if (!(str.charAt(0) >= 'a' && str.charAt(0) <= 'z')) {
            str = str.substring(1);
        }

        return str;
    }

    //inner DNode class:  PROVIDED
    private class DNode {

        private DNode next, prev;
        private T data;

        private DNode(T val) {
            this.data = val;
            next = prev = this;
        }
    }

    //DLinkedList fields:  PROVIDED
    private DNode header;

    //create an empty list:  PROVIDED
    public DLinkedList() {
        header = new DNode(null);
    }

    /**
     * PROVIDED add
     *
     * @param item return ref to newly inserted node
     */
    public DNode add(T item) {
        //make a new node
        DNode newNode = new DNode(item);
        //update newNode
        newNode.prev = header;
        newNode.next = header.next;
        //update surrounding nodes
        header.next.prev = newNode;
        header.next = newNode;
        return newNode;
    }

    //PROVIDED
    public String toString() {
        String str = "[";
        DNode curr = header.next;
        while (curr != header) {
            str += curr.data + " ";
            curr = curr.next;
        }
        str = str.substring(0, str.length() - 1);
        return str + "]";
    }

    /**
     * ASSIGNED remove val from the list
     *
     * @param val
     * @return true if successful, false otherwise
     */
    public boolean remove(T val) {
        //create reference node to sort through list
        DNode ref = header.next;

        //look through the nodes for the item
        while (ref != header) {
            //look for the item
            if (ref.data.equals(val)) {
                //bypass the item, and link it to itself to let
                //garbage collection clean it up
                ref.next.prev = ref.prev;
                ref.prev.next = ref.next;
                
                ref.next = ref;
                ref.prev = ref;

                return true;
            }
            //update ref
            ref = ref.next;
        }
        //if item is not found, return false
        return false;
    }

    /**
     * ASSIGNED
     *
     * inserts the item in the appropriate place in the chain
     *
     * @param item
     */
    public void insertOrder(T item) {
        //add the item, and store the reference to the new node
        DNode newNode = add(item);
        //create a reference node to iterate through the other nodes
        DNode ref = newNode.next;

        //look through the nodes until you get back to the header, or find the
        //first one that comes after newNode
        while (ref != header && newNode.data.compareTo(ref.data) > 0) {
            ref = ref.next;
        }

        //bypass the new node
        newNode.prev.next = newNode.next;
        newNode.next.prev = newNode.prev;

        //put the newNode in position to the ref
        newNode.prev = ref.prev;
        newNode.next = ref;

        //update the ref links to newNode
        ref.prev.next = newNode;
        ref.prev = newNode;

    }

    /**
     * ASSIGNED If the item is not already in the chain, add it to the chain in
     * order
     *
     * @param item
     */
    public boolean insertOrderUnique(T item) {
        //create a reference node
        DNode ref = header.next;
        //move through the chain, making sure item isnt already in a node
        while (ref != header) {
            if (ref.data.equals(item)) //if item is already in a node, return false
            {
                return false;
            }
            ref = ref.next;
        }
        //if it is not found, insert the item, and return true
        insertOrder(item);
        return true;
    }

    /**
     * ASSIGNED PRE: this and rhs are sorted lists
     *
     * @param rhs
     * @return list that contains this and rhs merged into a sorted list POST:
     * returned list will not contain duplicates
     */
    public DLinkedList merge(DLinkedList rhs) {
        DLinkedList result = new DLinkedList();

        //create pointers for the DLinked lists
        DNode pointA = this.header.next;
        DNode pointB = rhs.header.next;

        //make a general pointer
        DNode curr;

        //iterate through both lists to add them to result
        while (pointA != this.header && pointB != rhs.header) {
            //if the items are the same, bypass the node and
            //let garbage collection clean it up
            if (pointA.data.compareTo(pointB.data) == 0) {
                curr = pointA;
                pointA = pointA.next;

                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;

                curr.prev = curr;
                curr.next = curr;
            }
            //if pointA is first, add it to the end of the chain
            if (pointA.data.compareTo(pointB.data) < 0) {
                curr = pointA;
                pointA = pointA.next;

                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;

                curr.prev = result.header.prev;
                curr.next = result.header;

                result.header.prev.next = curr;
                result.header.prev = curr;
            } //otherwise add pointB to the end of the chain
            else {
                curr = pointB;
                pointB = pointB.next;

                curr.next.prev = curr.prev;
                curr.prev.next = curr.next;

                curr.prev = result.header.prev;
                curr.next = result.header;

                result.header.prev.next = curr;
                result.header.prev = curr;
            }
        }

        //when one chain reaches the end, add any remaining values
        //onto the end of the chain
        while (pointA != this.header) {
            curr = pointA;
            pointA = pointA.next;

            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;

            curr.prev = result.header.prev;
            curr.next = result.header;

            result.header.prev.next = curr;
            result.header.prev = curr;
        }
        while (pointB != rhs.header) {
            curr = pointB;
            pointB = pointB.next;

            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;

            curr.prev = result.header.prev;
            curr.next = result.header;

            result.header.prev.next = curr;
            result.header.prev = curr;
        }

        return result;
    }

}
