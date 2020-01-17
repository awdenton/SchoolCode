public class Driver {

    public static void main(String[] args) {
        
        //SAMPLE test program.
        //Be sure to test your code fully.
        
        ArraySet<Integer> ints1 = new ArraySet<>();
        SetInterface<Integer> ints2 = new LinkedSet<>();
        SetInterface<String> beatles = new LinkedSet<>();
        SetInterface<String> kings = new LinkedSet<>();
        SetInterface<String> presidents = new ArraySet<>();

        ints1.addItem(1);
        ints1.addItem(2);
        ints1.addItem(3);
        ints1.addItem(4);
        ints1.addItem(5);
        
        ints2.addItem(2);
        ints2.addItem(3);
        ints2.addItem(4);
        ints2.addItem(6);
        ints2.addItem(8);
        
        beatles.addItem("Paul");
        beatles.addItem("Ringo");
        beatles.addItem("John");
        beatles.addItem("George");
        
        kings.addItem("Edward");
        kings.addItem("William");
        kings.addItem("Henry");
        kings.addItem("George");
        
        presidents.addItem("George");
        presidents.addItem("John");
        presidents.addItem("William");
        presidents.addItem("Millard");
        
        System.out.println("Ints1 = " + ints1);
        System.out.println("Ints2 = " + ints2);
        System.out.println("Beatles = " + beatles);
        System.out.println("Kings = " + kings);
        System.out.println("Presidents = " + presidents);
        System.out.println();
        
        ints1.remove(2);
        ints2.remove(3);
        System.out.println(ints1);
        System.out.println(ints2);
                
        System.out.println("Ints1 union Ints2 = " + ints1.union(ints2));
        System.out.println("Ints2 - Ints1 = " + ints2.difference(ints1));
        System.out.println("Beatles diff Beatles = " + beatles.difference(beatles));
        System.out.println("Beatles union Kings = " + beatles.union(kings));
        System.out.println("Kings union Presidents = " + kings.union(presidents));
        System.out.println("Beatles intersect Kings = " + beatles.intersection(kings));
        System.out.println("Presidents - Kings = " + presidents.difference(kings));
        System.out.println("Kings - Presidents = " + kings.difference(presidents));
        System.out.println("Presidents XOR Beatles = " + presidents.symmDifference(beatles));
        
    }
}