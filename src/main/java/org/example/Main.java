package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text:");
        String inputText = scanner.nextLine();
        if (Text.containsMatchingWords(inputText)) {
            try {
                String outputText = Text.capitalizeLastLetterOfWords(inputText);
                System.out.println("Resulting text:");
                System.out.println(outputText);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else {
            System.out.println("Error: " + "The text does not contain the required tokens to replace.");
        }
    }
}