/*
package com.rean.springbootmaster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JavaParallelTest {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,1, 2,9};

        Set<Integer> set = new HashSet<>();

        // Sequential
        */
/*for (int number : numbers) {
            System.out.println(number);
        }*//*


        // Parallel
        Arrays.stream(numbers).parallel().forEach(System.out::println);
        Arrays.stream(numbers).parallel().forEach(set::add);
        System.out.println("Parallel end");
        System.out.println(set.size());

        System.out.println(set);
    }

}
*/
