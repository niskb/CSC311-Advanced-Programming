/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab8_generics;

/**
 *
 * @author Brian
 */
public class Lab8_GenericMethodDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.printf("The maximum of %d, %d, and %d is %d.\n", 23, 16, 29, maximum(23, 16, 29));
        System.out.printf("The maximum of %s, %s, and %s is %s.\n", "Hello", "hello", "hi", maximum("Hello", "hello", "hi"));
    }

    public static <T extends Comparable> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }
    
//    private static int maximum(int x, int y, int z) {
//        int max = x;
//        if (y > max) {
//            max = y;
//        }
//        if (z > max) {
//            max = z;
//        }
//        return max;
//    }
//
//    private static String maximum(String x, String y, String z) {
//        String max = x;
//        if (y.compareTo(max) > 0) {
//            max = y;
//        }
//        if (z.compareTo(max) > 0) {
//            max = z;
//        }
//        return max;
//    }

}
