/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab10_mapdemo;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Brian
 */
public class Lab10_MapDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Finding the frequency of a word in a String
        String text = "How are you, class? Good morning! Hope you enjoy the class. Have a good class! Have a good day!";
        Map<String, Integer> map = new TreeMap<>();

        String delimiter;
        delimiter = "[\\s+\\p{P}]+";
        String[] words = text.split(delimiter);

        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

        for (int j = 0; j < words.length; j++) {
            String key = words[j].toLowerCase();
            if (!(key.isBlank())) {
                if (!(map.containsKey(key))) {
                    map.put(key, 1);
                } else {
                    int value = map.get(key);
                    value++;
                    map.put(key, value);
                }
            }
        }

        System.out.println("\n1) Iterate the map using the keys:");
        for (String key : map.keySet()) {
            System.out.printf("%-10s%4d\n", key, map.get(key));
        }

        System.out.println("\n2) Iterate the map using the values:");
        for (int value : map.values()) {
            System.out.printf("%d\n", value);
        }

        System.out.println("\n3) Iterate the map using an iterator:");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.printf("%-10s%4d\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n4) Iterate the map using foreach loop:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.printf("%-10s%4d\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n5) Iterate the map using streams:");
        // Consumer Class Override accept()
        map.forEach((key, value) -> {
            System.out.printf("%-10s%4d\n", key, map.get(key));
        });
    }

}
