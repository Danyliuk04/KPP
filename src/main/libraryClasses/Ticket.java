package main.libraryClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {
    String name;
    String surname;
    String patronymic;
    String email;
    Reader reader;

    public Ticket(){}

    public Ticket(String name, String surname, String patronymic, String email){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        reader = new Reader();
    }
    public Ticket(String name, String surname, String patronymic, String email, Reader reader){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.reader = reader;
    }

    public Ticket (Ticket t){
        this.name = t.name;
        this.surname = t.surname;
        this.patronymic = t.patronymic;
        this.email = t.email;
        this.reader = t.reader;
    }

    public String getName(){
        return name;
    }
    public String getSurname(){return surname;}
    public String getPatronymic(){return patronymic;}
    public String getEmail(){return email;}
    public Reader getReader(){return reader;}
    public int getNumOfBooksByReader(){return reader.getTakenBooks().size();}



    @Override
    public String toString(){
        return "Reader{ " +
                name + " " +
                surname + " " +
                patronymic + ", books taken: "+
                reader.getTakenBooks().size() +
                " }";
    }


}
