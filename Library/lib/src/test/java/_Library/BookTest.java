// File: _Library/BookTest.java

package _Library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the Book class.
 */
public class BookTest {

    @Test
    public void testBookToString() {
        Book book = new Book("TestBook", "TestAuthor", 10);
        assertEquals("Book{title='TestBook', author='TestAuthor', quantity=10}", book.toString());
    }

    // Add more test cases for other methods as needed...

}
