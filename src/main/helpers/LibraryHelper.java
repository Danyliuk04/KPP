package main.helpers;

import main.libraryClasses.Book;
import main.libraryClasses.Library;
import main.libraryClasses.Ticket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LibraryHelper {

    public static ArrayList<Book> readBooksFromFile(String filePath){
        ArrayList<Book> books = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line ->{
                String[] parts = line.split(", ");
                books.add(new Book(parts[0], parts[1], Integer.parseInt(parts[2])));
            });

            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return books;
        }
    }

    public static ArrayList<Book> sortBooksByYear(Library library, Order order){
        switch (order) {
            case ASC -> {
                return library.getBooks().stream().sorted(Comparator.comparing(Book::getPublished)).collect(Collectors.toCollection(ArrayList::new));
            }
            case DESC -> {
                return library.getBooks().stream().sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toCollection(ArrayList::new));
            }
            default -> {
                return library.getBooks();
            }
        }
    }

}
