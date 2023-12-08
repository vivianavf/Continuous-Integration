// File: _Library/BookCatalogTest.java

package _Library;

import java.util.ArrayList;
import java.util.List;

public class BookCatalog {
    private static List<Book> catalog = new ArrayList<>();

    static {
        // Populate the catalog with sample books
        catalog.add(new Book("Book1", "Author1", 5));
        catalog.add(new Book("Book2", "Author2", 3));
        catalog.add(new Book("Book3", "Author3", 8));
        // Add more books as needed
    }

    public static List<Book> getCatalog() {
        return catalog;
    }
}
