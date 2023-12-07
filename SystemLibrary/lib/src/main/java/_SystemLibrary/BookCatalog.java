package _SystemLibrary;

import java.util.ArrayList;
import java.util.List;

public class BookCatalog {
    private static List<Book> catalog = new ArrayList<>();

    static {
        // Initialize the catalog with sample books
        catalog.add(new Book("Book1", "Author1", 5));
        catalog.add(new Book("Book2", "Author2", 3));
        // Add more books as needed
    }

    public static List<Book> getCatalog() {
        return catalog;
    }
}
