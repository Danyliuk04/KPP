package main;

import main.helpers.*;
import main.helpers.Record;
import main.libraryClasses.*;

import java.util.*;

public class Main {
    static ArrayList<Reader> readers = new ArrayList<>();
    static ArrayList<Ticket> tickets = new ArrayList<>();
    static ArrayList<Record> records = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManager libraryManager = new LibraryManager();

        int choice;
        do {
            printMenu();
            choice = getChoice(scanner);

            switch (choice) {
                case 1 -> libraryManager.handleSortBooks(scanner);
                case 2 -> libraryManager.handleReadersWithMoreBooks(scanner);
                case 3 -> libraryManager.handleReadersByAuthor(scanner);
                case 4 -> libraryManager.handleBiggestNumberOfBooksTaken();
                case 5 -> libraryManager.handleMailingLists();
                case 6 -> libraryManager.handleOverdueBooks(scanner);
                case 0 -> System.out.println("Exiting the application. Goodbye!");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);

    // Close the scanner
        scanner.close();
}

    private static void printMenu() {
        System.out.println("===== Console Menu =====");
        System.out.println("1. Sort books by year");
        System.out.println("2. List of people who took more than (n) books");
        System.out.println("3. How many users did grab a book from the same author?");
        System.out.println("4. The biggest number of books taken by user");
        System.out.println("5. Mailing lists for those, who have less than 2 books and those, who have more");
        System.out.println("6. Find overdue books by date");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }



}