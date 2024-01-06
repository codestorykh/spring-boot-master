package com.rean.springbootmaster;

public class ProceduralDemo {

    public static void main(String[] args) {
        // Calling procedural-style functions to perform a task
        int result = addNumbers(7, 7);
        displayResult(result);
    }
    // Procedure to add two numbers
    public static int addNumbers(int a, int b) {
        return a + b;
    }
    // Procedure to display the result
    public static void displayResult(int result) {
        System.out.println("The result is: " + result);
    }
}