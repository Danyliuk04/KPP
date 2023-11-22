package main.libraryClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Serializable {
    public static int maxBooks = 5;
    ArrayList<Book> taken;

    public Reader(){
        taken = new ArrayList<>();
    }

    public Reader(ArrayList<Book> taken){
        this.taken = new ArrayList<>(taken);
    }
    public int getMaxBooks(){
        return maxBooks;
    }
    public ArrayList<Book> getTakenBooks(){
        return taken;
    }
    public void setTakenBooks(ArrayList<Book> taken){
        this.taken = taken;
    }
    public void addTakenBook(Book book){
        taken.add(book);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "maxBooks='" + maxBooks +
                "', Books taken='" + taken +
                '}';
    }

}
