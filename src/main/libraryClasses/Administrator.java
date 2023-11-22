package main.libraryClasses;

import main.helpers.LibraryHelper;
import main.helpers.Record;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Administrator {
    static ArrayList<Ticket> tickets = new ArrayList<>();
    static ArrayList<Record> records = new ArrayList<>();

    public static void setTickets(ArrayList<Ticket> tick){
        tickets = tick;
    }

    public static void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public static void setRecords(ArrayList<Record> rec){
        records = rec;
    }

    public static void addRecord(Record rec){
        records.add(rec);
    }

    public static void addRecord(Ticket ticket,
                                 Book book,
                                 LocalDate dateTaken,
                                 LocalDate dateExpectedReturn){
        records.add(new Record(ticket, book, dateTaken, dateExpectedReturn));
    }

    public static void registerTake(Ticket ticket, Book book, LocalDate dateTaken, LocalDate dateExpectedReturn){
        ticket.getReader().addTakenBook(book);
        tickets.add(ticket);
        records.add(new Record(
                ticket,
                book,
                dateTaken,
                dateExpectedReturn
        ));
    }

    public static ArrayList<Book> getBooksNotReadByUser(Ticket ticket, Library library){
        return library.getBooks()
                .stream()
                .filter(b -> !ticket.getReader().getTakenBooks().contains(b))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Record> getRecordsByUser(Ticket ticket){
        return records
                .stream()
                .filter(r -> r.getTicket() == ticket)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Record> getRecordsOverdue(LocalDate date){
        return records
                .stream()
                .filter(r -> date.isAfter(r.getDateExpectedReturn()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static long getOverdueDays(Record record, LocalDate date){
        Duration diff = Duration.between(record.getDateExpectedReturn().atStartOfDay(), date.atStartOfDay());
        return diff.toDays();
    }
}
