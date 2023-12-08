package _SystemLibrary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LibraryTest {
    @Test void someLibraryMethodReturnsTrue() {
        Library classUnderTest = new Library();
        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'");
    }
    
    @Test
    public void testSelectBooksValidInput() {
        // Mock user input
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n3\n".getBytes());
        System.setIn(in);

        // Redirect console output to capture the result
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Test the selectBooks method
        List<Book> selectedBooks = LibraryManagement.selectBooks();

        // Restore standard input/output
        System.setIn(System.in);
        System.setOut(System.out);

        assertNotNull(selectedBooks);
        assertEquals(3, selectedBooks.size());

    }
    
    @Test
    public void testSelectBooksInvalidInput() {
        // Mock user input with invalid book number
        ByteArrayInputStream in = new ByteArrayInputStream("invalid\n".getBytes());
        System.setIn(in);

        // Redirect console output to capture the result
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Test the selectBooks method with invalid input
        List<Book> selectedBooks = LibraryManagement.selectBooks();

        // Restore standard input/output
        System.setIn(System.in);
        System.setOut(System.out);

        assertNull(selectedBooks);
        assertTrue(out.toString().contains("Invalid input. Please enter valid book numbers."));
    }
    
    
    @Test
    public void testValidateQuantityValidInput() {
        // Mock user input with a valid quantity
        ByteArrayInputStream in = new ByteArrayInputStream("5\n".getBytes());
        System.setIn(in);

        // Redirect console output to capture the result
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Test the validateQuantity method
        int quantity = LibraryManagement.validateQuantity(new Scanner(System.in));

        // Restore standard input/output
        System.setIn(System.in);
        System.setOut(System.out);

        assertEquals(5, quantity);
    }
    
    @Test
    public void testValidateQuantityInvalidInput() {
        // Mock user input with an invalid quantity
        ByteArrayInputStream in = new ByteArrayInputStream("invalid\n".getBytes());
        System.setIn(in);

        // Redirect console output to capture the result
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Test the validateQuantity method with invalid input
        int quantity = LibraryManagement.validateQuantity(new Scanner(System.in));

        // Restore standard input/output
        System.setIn(System.in);
        System.setOut(System.out);

        assertEquals(-1, quantity);
        assertTrue(out.toString().contains("Invalid input. Please enter a valid integer."));
    }
    
    @Test
    public void testCalculateDueDates() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book1", "Author1", 5));
        books.add(new Book("Book2", "Author2", 3));

        // Redirect console output to capture the result
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        LibraryManagement.calculateDueDates(books);

        // Restore standard input/output
        System.setOut(System.out);

        // Add assertions based on your application logic
    }
    
}
