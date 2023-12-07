package _SystemLibrary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagement {
    private static final int MAX_BOOKS_PER_CHECKOUT = 10;

    public static void main(String[] args) {
        displayCatalog();

        // Requirement 1: Book Selection and Checkout
        List<Book> selections = selectBooks();
        displayCheckoutConfirmation(selections);

        // Requirement 2: Quantity Validation
        int newQuantity = validateQuantity();
        if (newQuantity == -1) {
            System.out.println("Invalid quantity. Please enter a positive integer greater than zero.");
            return;
        }

        // Requirement 3: Due Date Calculation
        System.out.println("Due Date for new checkout: " + calculateDueDate());

        // Requirement 4: Late Fee Calculation
        double lateFees = calculateLateFees(BookCatalog.getCatalog());
        System.out.println("Total Late Fees for the entire catalog: $" + lateFees);

        // Requirement 5: Book Availability
        List<Book> unavailableBooks = new ArrayList<>();
        unavailableBooks.add(new Book("Book1", "Author1", BookCatalog.getCatalog().get(0).getQuantity() + 1));
        if (checkoutBooks(unavailableBooks) == -1) {
            System.out.println("Error: Unavailable book selected. Please re-select.");
        }

        // Requirement 6: Maximum Books per Checkout
        List<Book> selectionsExceedLimit = selectBooksExceedLimit();
        if (checkoutBooks(selectionsExceedLimit) == -1) {
            System.out.println("Error: Exceeded maximum books per checkout. Please adjust your selection.");
        }

        // Requirement 7: User Confirmation
        displayCheckoutConfirmation(selectionsExceedLimit);

        // Requirement 8: Return Process
        List<Book> returnedBooks = returnBooks();
        double returnLateFees = calculateLateFees(returnedBooks);
        System.out.println("Total Late Fees for returned books: $" + returnLateFees);

        // Requirement 9: Output
        displayCheckoutConfirmation(selectionsExceedLimit);
        System.out.println("Total Late Fees: $" + calculateLateFees(BookCatalog.getCatalog()));

        // Requirement 10: Error Handling
        int invalidInput = validateQuantity("invalid");
        if (invalidInput == -1) {
            System.out.println("Error: Invalid input detected.");
        }

        List<Book> canceledCheckout = checkoutBooks(new ArrayList<>());
        if (canceledCheckout == null) {
            System.out.println("Error: Checkout canceled.");
        }
    }

    private static void displayCatalog() {
        System.out.println("Library Catalog:");
        for (Book book : BookCatalog.getCatalog()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - Available: " + book.getQuantity());
        }
    }

    private static List<Book> selectBooks() {
        // Implement user input logic for book selection
        return null;
    }

    private static int validateQuantity() {
        // Implement user input logic for quantity validation
        return 0;
    }

    private static int validateQuantity(String input) {
        // Implement user input logic for quantity validation with a specified input
        return 0;
    }

    private static LocalDate calculateDueDate() {
        // Implement due date calculation logic
        return LocalDate.now().plusDays(14);
    }

    private static double calculateLateFees(List<Book> books) {
        // Implement late fee calculation logic
        return 0.0;
    }

    private static List<Book> checkoutBooks(List<Book> selections) {
        // Implement book checkout logic
        return null;
    }

    private static List<Book> selectBooksExceedLimit() {
        // Implement user input logic for selecting books that exceed the limit
        return null;
    }

    private static void displayCheckoutConfirmation(List<Book> selections) {
        // Implement logic to display checkout confirmation
    }

    private static List<Book> returnBooks() {
        // Implement book return logic
        return null;
    }
}
