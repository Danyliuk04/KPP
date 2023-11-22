package main.helpers;

import main.libraryClasses.Library;

import java.io.*;

public class LibrarySerialize {
    public static void serialize(Library library, String filePath) {
        serializeLibrary(library, filePath);
        System.out.println( deserializeLibrary(filePath).getBooks());
    }

    public static void serializeLibrary(Library library, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(library);
            System.out.println("Library serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Library deserializeLibrary(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Library library = (Library) ois.readObject();
            System.out.println("Library deserialized successfully.");
            return library;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}