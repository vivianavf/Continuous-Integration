// File: _Library/LibraryTest.java

package _Library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;


/**
 * JUnit tests for the Library class.
 */
public class LibraryTest {

    @Test
    public void testSelectBooksValidInput() {
        // Redirect System.in for providing input during testing
        System.setIn(new ByteArrayInputStream("1,2\n2\n".getBytes()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Library.main(null);

        // Restore System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

        // Check the output for successful book selection
        assertEquals("Library Catalog:\n" +
                "Book{title='Book1', author='Author1', quantity=5}\n" +
                "Book{title='Book2', author='Author2', quantity=3}\n" +
                "Book{title='Book3', author='Author3', quantity=8}\n" +
                "Select books for checkout:\n" +
                "1. Book1 - Author1 - Quantity: 5\n" +
                "2. Book2 - Author2 - Quantity: 3\n" +
                "3. Book3 - Author3 - Quantity: 8\n" +
                "Enter the book numbers you want to checkout (comma-separated):\n" +
                "Enter the quantity for the book:\n" +
                "Enter the quantity for the book:\n" +
                "Book1 - Due Date: " + LocalDate.now().plusDays(14) + "\n" +
                "Book2 - Due Date: " + LocalDate.now().plusDays(14) + "\n" +
                "Total Late Fees: $0.0\n" +
                "Confirm Checkout:\n" +
                "Title: Book1 | Author: Author1 | Quantity: 5 | Due Date: " + LocalDate.now().plusDays(14) + "\n" +
                "Title: Book2 | Author: Author2 | Quantity: 3 | Due Date: " + LocalDate.now().plusDays(14) + "\n" +
                "Do you want to proceed with the checkout? (yes/no)\n" +
                "Checkout successful! Thank you for using the library.\n", outputStream.toString());
    }

    @Test
    public void testCalculateLateFees() {
        List<Book> selectedBooks = Arrays.asList(
                new Book("Book1", "Author1", 5),
                new Book("Book2", "Author2", 3)
        );

        // Set due dates to past dates for testing late fees
        selectedBooks.get(0).setDueDate(LocalDate.now().minusDays(2));
        selectedBooks.get(1).setDueDate(LocalDate.now().minusDays(5));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Library.calculateLateFees(selectedBooks);

        // Restore System.out
        System.setOut(System.out);

        // Check the output for correct late fee calculation
        assertEquals("Late Fees Calculation:\n" +
                "Book1 - Late Fee: $2.0\n" +
                "Book2 - Late Fee: $5.0\n" +
                "Total Late Fees: $7.0\n", outputStream.toString());
    }

    // Add more test cases for other methods as needed...

}
