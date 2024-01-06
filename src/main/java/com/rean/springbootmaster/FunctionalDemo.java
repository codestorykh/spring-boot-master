package com.rean.springbootmaster;

import java.util.Arrays;
import java.util.List;

public class FunctionalDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Functional programming pipeline
        int result = numbers.stream()
                .filter(FunctionalDemo::isEven)
                .map(FunctionalDemo::doubleNumber)
                .reduce(0, Integer::sum);
        System.out.println(result);
    }
    // Predicate function to check if a number is even
    private static boolean isEven(int number) {
        return number % 2 == 0;
    }
    // Function to double a number
    private static int doubleNumber(int number) {
        return number * 2;
    }
}
