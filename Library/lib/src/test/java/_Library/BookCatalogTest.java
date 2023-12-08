// File: _Library/BookCatalogTest.java

package _Library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the BookCatalog class.
 */
public class BookCatalogTest {

    @Test
    public void testGetCatalog() {
        List<Book> catalog = BookCatalog.getCatalog();
        assertEquals(3, catalog.size()); // Assuming there are initially 3 books in the catalog
    }


}
