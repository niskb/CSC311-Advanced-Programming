/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab9_collectioniteratordemo;

import java.util.*;

/**
 *
 * @author Brian
 */
public class Lab9_CollectionIteratorDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] array1 = new String[]{
            "red", "green", "blue", "red", "white", "black", "pink", "green"
        };

        List<String> list1 = new ArrayList<>();
        for (String item : array1) {
            list1.add(item);
        }
        System.out.println(list1);

        for (String item : list1) {
            System.out.print(item + " ");
        }
        System.out.println();

        Iterator<String> iterator = list1.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        System.out.println("Binary search of red before sorting:");
        int index = Collections.binarySearch(list1, "red");
        System.out.println("Index of red = " + index);

        System.out.print("Sort list1 in natural ordering: ");
        Collections.sort(list1);
        System.out.println(list1);

        System.out.print("Inner Class: Sort list1 with a customized comparator (by length): ");
        Collections.sort(list1, new StringComparator());
        System.out.println(list1);

        System.out.print("Lambda Expression / Anonymous Method: Reverse by length the order: ");
        Collections.sort(list1, (o1, o2) -> {
            return o2.length() - o1.length();
        });
        System.out.println(list1);

        System.out.println("Binary search of red after sorting:");
        index = Collections.binarySearch(list1, "red", (o1, o2)
                -> {
            return o2.length() - o1.length();
        });
        System.out.println("Index of red = " + index);
        
        Set<String> hashSet = new HashSet<>();
        for(String item : list1) {
            hashSet.add(item);
        }
        System.out.print("HashSet content: ");
        System.out.println(hashSet);
        
        SortedSet<String> treeSet = new TreeSet<>();
        for(String item : list1) {
            treeSet.add(item);
        }
        System.out.print("TreeSet content: ");
        System.out.println(treeSet);
        
        System.out.println(treeSet.first());
        System.out.println(treeSet.headSet("green"));
        System.out.println(treeSet.tailSet("green"));
        
        System.out.println("Add a duplicate: " + treeSet.add("red"));

        String[] array2 = new String[]{"white", "black"};
        List<String> list2 = new LinkedList(); // always specify a type of a bounded generic object
        System.out.println(list2);

        addToList(array1, list1);
        addToList(array2, list2);

        printList(list1);
        printList(list2);

        iterator = list1.iterator();
        while (iterator.hasNext()) {
            if (list2.contains(iterator.next())) {
                iterator.remove();
            }
        }
        printList(list1);

        ListIterator<String> iterator2 = (ListIterator<String>) list2.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
//            if(iterator2.hasPrevious()) {
//                System.out.println(iterator2.previous());
//            }
        }

    }

    public static <T> void addToList(T[] array, Collection<T> list) {
        for (T item : array) {
            list.add(item);
        }
    }

    public static <T> void printList(Collection<T> list) {
//        Iterator<T> iterator = list.iterator();
//        while(iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }
//        System.out.println();
        for (T item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

}

class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }

}
