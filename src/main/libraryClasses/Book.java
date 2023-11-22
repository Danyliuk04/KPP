package main.libraryClasses;

import java.io.Serializable;

public class Book implements Serializable {
    String author;
    String name;
    int published;

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author +
                "', name='" + name +
                "', published=" + published +
                '}';
    }
    public Book() {
    }

    public Book(String name, String author, int published) {
        this.author = author;
        this.name = name;
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }
}