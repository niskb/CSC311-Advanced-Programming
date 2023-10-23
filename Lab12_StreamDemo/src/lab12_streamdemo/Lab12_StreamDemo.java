/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab12_streamdemo;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Brian
 */
public class Lab12_StreamDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] values = {3, 4, 1, 5, 20, 1, 3, 3, 4, 6};
        List<Integer> list = new ArrayList<>();
        for (int item : values) {
            list.add(item);
        }
        System.out.println(list);
        Set<Integer> set = new HashSet(list);
        System.out.println(set);
        int sum = 0;
        int count = 0;
        double average = 0;
        for (int item : set) {

            if ((item > 3) && (item % 2 == 0)) {
                sum += item;
                count++;
            }
        }
        average = (double) sum / count;
        System.out.println("The average of distinct even numbers that are greater than 3: " + average);
        System.out.println("Java Streams (There are 3 ways to do this application):");
        System.out.println("The average of distinct even numbers that are greater than 3: " + IntStream.of(values)
                .distinct()
                .filter(e -> (e > 3) && (e % 2 == 0)) // Predicate functional interface implements test(T t)
                .average()
                .getAsDouble());
        System.out.println("The average of distinct even numbers that are greater than 3: " + Arrays.stream(values)
                .distinct()
                .filter(e -> (e > 3) && (e % 2 == 0))
                .average()
                .getAsDouble());

        Arrays.stream(values)
                .forEach(e -> System.out.print(e + " ")); // Consumer implements accept
        System.out.println("");

        System.out.println(Arrays.stream(values)
                .noneMatch(e -> (e > 20)));

        IntSummaryStatistics stat = Arrays.stream(values).summaryStatistics();
        System.out.println("Count: " + stat.getCount());
        System.out.println("Max: " + stat.getMax());
        System.out.println("Average: " + stat.getAverage());
        System.out.println("Sum: " + stat.getSum());

        String[] names = {"John", "Peter", "Susan", "kim", "Jen", "George", "Alan", "Stacy", "Michelle", "john"};
        System.out.println("Total character count for all names: " + Stream.of(names)
                .mapToInt(e -> (e.length()))
                .sum());

        Stream.of(names).sorted()
                .forEach(e -> System.out.print(e + " "));
        System.out.println();

        Stream.of(names).sorted((e1, e2) -> e1.compareToIgnoreCase(e2))
                .forEach(e -> System.out.print(e + " "));
        System.out.println();
    }

}
