/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class awdenton_hwk1 {

    public static <T> boolean contains(T[] input, Check<T> c) {
        for (int i = 0; i < input.length; i++) {
            if (c.ok(input[i])) {
                return true;
            }
        }
        return false;
    }

    public static interface Check<T> {

        boolean ok(T item);
    }

    static public class Prime implements Check<Integer> {

        @Override
        public boolean ok(Integer item) {
            if (item < 2) {
                return false;
            }

            for (int i = 2; i <= Math.sqrt(item); i++) {
                if (item % i == 0) {
                    return false;
                }
            }

            return true;
        }

    }

    static public class PerfectSquare implements Check<Integer> {

        @Override
        public boolean ok(Integer item) {
            return item % Math.sqrt(item) == 0;
        }

    }

    static public class Negative implements Check<Integer> {

        @Override
        public boolean ok(Integer item) {
            return item < 0;
        }

    }

    public static void main(String[] args) {

        Integer[] input = {99, -47, 64, 2};

        boolean result1 = contains(input, new Negative());
        boolean result2 = contains(input, new PerfectSquare());
        boolean result3 = contains(input, new Prime());

        System.out.println("\n" + "Negative: " + result1 + "\n");
        System.out.println("Perfect Square: " + result2 + "\n");
        System.out.println("Prime: " + result3 + "\n");

    }

}
