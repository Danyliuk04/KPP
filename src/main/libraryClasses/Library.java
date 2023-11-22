package main.libraryClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class Library implements Serializable {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Ticket> tickets = new ArrayList<>();

    public Library() {}
    public Library(ArrayList<Book> books){
        this.books = books;
    }
    public Library(ArrayList<Book> books, ArrayList<Ticket> tickets){
        this.books = books;
        this.tickets = tickets;
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

    public void setBooks(ArrayList<Book> books){
        this.books = books;
    }

    public void setTickets(ArrayList<Ticket> tickets){
        this.tickets = tickets;
    }

    public void registerTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public ArrayList<String> getReadersWhoTookMoreThan(int books_taken){
        ArrayList<String> res = new ArrayList<>();
        tickets.stream().filter(t -> t.getNumOfBooksByReader() >= 2).forEach(t -> res.add(t.getEmail()));
        return res;
    }

    public ArrayList<Ticket> howManyReadersTookBookOfAuthor(String author){
        ArrayList<Ticket> res = new ArrayList<>();
        res = tickets
                .stream()
                .filter(t -> t.getReader().getTakenBooks()
                        .stream()
                        .filter(book -> Objects.equals(book.getAuthor(), author))
                        .findFirst()
                        .isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
        return res;
    }

    public Ticket theBiggestNumberOfBooksTaken(){
//        Ticket res;
//        Optional<Ticket> ticket = tickets.stream().sorted(Comparator.comparing(Ticket::getNumOfBooksByReader).reversed()).findFirst();
//        Optional<Ticket> ticket = tickets.stream().max(Comparator.comparing(Ticket::getNumOfBooksByReader));
//        ticket.ifPresent(ticket1 -> res = ticket1);
        return tickets
                .stream()
                .max(Comparator.comparing(Ticket::getNumOfBooksByReader))
                .orElse(null);
    }

    public HashMap<Integer, ArrayList<Ticket>> getGroupsDividedByNumOfBooks(int num){
        HashMap<Integer, ArrayList<Ticket>> result = new HashMap<Integer, ArrayList<Ticket>>();

        result.put(0, tickets
                .stream()
                .filter(t -> t.getNumOfBooksByReader() < num)
                .collect(Collectors.toCollection(ArrayList::new)));

        result.put(1, tickets
                .stream()
                .filter(t -> t.getNumOfBooksByReader() >= num)
                .collect(Collectors.toCollection(ArrayList::new)));

        return result;
    }
}