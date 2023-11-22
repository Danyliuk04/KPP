package main.helpers;

import main.libraryClasses.*;

import java.time.LocalDate;

public class Record {
    static int id = -1;
    Ticket ticket;
    Book book;
    LocalDate dateTaken;
    LocalDate dateExpectedReturn;
    LocalDate dateActualReturn = null;

    public Record(){};
    public Record(
            Ticket ticket,
            Book book,
            LocalDate dateTaken,
            LocalDate dateExpectedReturn)
    {
        this.ticket = ticket;
        this.book = book;
        this.dateTaken = dateTaken;
        this.dateExpectedReturn = dateExpectedReturn;
        id++;
    }

    public Ticket getTicket(){
        return ticket;
    }
    public Book getBook(){
        return book;
    }
    public LocalDate getDateExpectedReturn() {
        return dateExpectedReturn;
    }

    public void setDateActualReturn(LocalDate actualReturn){
        dateActualReturn = actualReturn;
    }



}
