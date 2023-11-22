package main.helpers;

import main.libraryClasses.Administrator;
import main.libraryClasses.Book;
import main.libraryClasses.Library;
import main.libraryClasses.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class LibraryManager {
    private static Library library = new Library();

    public LibraryManager() {
        this.library = new Library(LibraryHelper.readBooksFromFile("./src/main/inputs/books.txt"));
        fillAdministrator();
        LibrarySerialize.serialize(library, "library.txt");
    }

    public void handleSortBooks(Scanner scanner) {
        System.out.println("You selected Option 1.");
        System.out.print("Enter the order for sorting (1 - ASC, 2 - DESC): ");
        int option1Input = scanner.nextInt();

        switch (option1Input) {
            case 1 -> printSortedBooks(Order.ASC);
            case 2 -> printSortedBooks(Order.DESC);
        }
    }

    public void handleReadersWithMoreBooks(Scanner scanner) {
        System.out.println("You selected Option 2.");
        System.out.print("Enter num of books for users to have: ");
        int option2Input = scanner.nextInt();

        for (String string : library.getReadersWhoTookMoreThan(option2Input)) {
            System.out.println(string);
        }
    }

    public void handleReadersByAuthor(Scanner scanner) {
        System.out.println("You selected Option 3.");
        System.out.print("Enter the author you are interested in: ");
        scanner.nextLine();
        String option3Input = scanner.nextLine();

        for (Ticket ticket : library.howManyReadersTookBookOfAuthor(option3Input)) {
            System.out.println(ticket.toString());
        }
    }

    public void handleBiggestNumberOfBooksTaken() {
        System.out.println("You selected Option 4.");
        System.out.println("The biggest num of books taken");
        System.out.println(library.theBiggestNumberOfBooksTaken());
    }

    public void handleMailingLists() {
        System.out.println("You selected Option 5.");
        HashMap<Integer, ArrayList<Ticket>> groups = library.getGroupsDividedByNumOfBooks(2);

        for (int group : groups.keySet()) {
            if (group == 0) {
                System.out.println("Hello, please, see the newcomers of our collection!");
                for (Ticket t : groups.get(group)) {
                    System.out.println(t.getName() + " " + t.getSurname() + " you may find interesting: " +
                            Administrator.getBooksNotReadByUser(t, library));
                    System.out.println();
                }
            } else if (group == 1) {
                System.out.println("\nPlease, be aware, that you must return the books!");
                for (Ticket t : groups.get(group)) {
                    for (Record record : Administrator.getRecordsByUser(t)) {
                        System.out.println("Attention, " + t.getName() + " " + t.getSurname() +
                                " you must return: " + record.getBook() + " by the " + record.getDateExpectedReturn());
                    }
                    System.out.println();
                }
            }
        }
    }

    public void handleOverdueBooks(Scanner scanner) {
        System.out.println("You selected Option 6.");
        System.out.print("Enter date to start from in format (yyyy-mm-dd): ");
        scanner.nextLine();
        String option6Input = scanner.nextLine();
        System.out.print("Enter the num of date to run through: ");
        int option6Input2 = scanner.nextInt();

        System.out.println("On the running date:");
        LocalDate dt = LocalDate.parse(option6Input, DateTimeFormatter.ISO_LOCAL_DATE);

        for (int i = 0; i < option6Input2; i++) {
            System.out.println(dt + ":");
            for (Record rec : Administrator.getRecordsOverdue(dt)) {
                System.out.println("Warning " + rec.getTicket().getSurname() + " " +
                        rec.getTicket().getName() + " your book " + rec.getBook().getName() +
                        " is overdue by " + Administrator.getOverdueDays(rec, dt) + " days.");
            }
            dt = dt.plusDays(1);
        }
    }

    private void printSortedBooks(Order order) {
        for (Book book : LibraryHelper.sortBooksByYear(library, order)) {
            System.out.println(book);
        }
    }


    public static void fillAdministrator() {
        Ticket t1 = new Ticket("Anton", "Bol", "Oleksandrovych", "bolAnt@gmail.com");
        library.registerTicket(t1);
        Administrator.registerTake(
                t1,
                library.getBooks().get(4),
                LocalDate.of(2023, 11, 21),
                LocalDate.of(2023, 12, 17)
        );

        Administrator.registerTake(
                t1,
                library.getBooks().get(6),
                LocalDate.of(2023, 11, 12),
                LocalDate.of(2023, 12, 14)
        );


        Ticket t2 = new Ticket("Andrii", "Steciv", "Ihorovych", "and_st@gmail.com");
        library.registerTicket(t2);
        Administrator.registerTake(
                t2,
                library.getBooks().get(3),
                LocalDate.of(2023, 11, 2),
                LocalDate.of(2023, 11, 28)
        );


        Ticket t3 = new Ticket("Maria", "Radomska", "Ivanivna", "mariaradomskaaa.com");
        library.registerTicket(t3);
        Administrator.registerTake(
                t3,
                library.getBooks().get(0),
                LocalDate.of(2023, 11, 12),
                LocalDate.of(2023, 12, 14)
        );


        Ticket t4 = new Ticket("Victoria", "Senkiv", "Yuriivna", "senkivv@gmail.com");
        library.registerTicket(t4);
        Administrator.registerTake(
                t4,
                library.getBooks().get(0),
                LocalDate.of(2023, 8, 28),
                LocalDate.of(2024, 1, 8)
        );
        Administrator.registerTake(
                t4,
                library.getBooks().get(3),
                LocalDate.of(2023, 10, 12),
                LocalDate.of(2023, 12, 14)
        );
        Administrator.registerTake(
                t4,
                library.getBooks().get(7),
                LocalDate.of(2023, 11, 8),
                LocalDate.of(2023, 12, 21)
        );
        Administrator.registerTake(
                t4,
                library.getBooks().get(9),
                LocalDate.of(2023, 11, 8),
                LocalDate.of(2024, 1, 7)
        );


        Ticket t5 = new Ticket("Alex", "Kocherga", "Tarasovych", "kocherga_al@gmail.com");
        library.registerTicket(t5);
        Administrator.registerTake(
                t5,
                library.getBooks().get(3),
                LocalDate.of(2023, 10, 30),
                LocalDate.of(2023, 12, 17)
        );
        Administrator.registerTake(
                t5,
                library.getBooks().get(5),
                LocalDate.of(2023, 11, 20),
                LocalDate.of(2023, 12, 22)
        );
        Administrator.registerTake(
                t5,
                library.getBooks().get(10),
                LocalDate.of(2023, 11, 20),
                LocalDate.of(2023, 12, 21)
        );


    }

}
