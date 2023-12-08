// File: _Library/Library.java

package _Library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a simple library management system.
 */
public class Library {

    /**
     * Main method to run the library management application.
     */
    public static void main(String[] args) {
        displayCatalog();
        List<Book> selectedBooks = selectBooks();
        if (selectedBooks != null) {
            calculateLateFees(selectedBooks);
            confirmCheckout(selectedBooks);
            processReturns(selectedBooks);
            // Additional logic for checkout or return process can be added here
        } else {
            System.out.println("Checkout process canceled.");
        }
    }


	static void calculateLateFees(List<Book> selectedBooks) {
        double totalLateFees = 0;

        System.out.println("\nLate Fees Calculation:");

        for (Book book : selectedBooks) {
            // Assuming a late fee of $1 per day for each overdue book
            int daysOverdue = calculateDaysOverdue(book.getDueDate());
            if (daysOverdue > 0) {
                double lateFee = daysOverdue * 1.0; // $1 per day
                totalLateFees += lateFee;
                System.out.println(book.getTitle() + " - Late Fee: $" + lateFee);
            }
        }

        System.out.println("Total Late Fees: $" + totalLateFees);
    }

    
    /**
     * Calculates the number of days a book is overdue based on the due date.
     *
     * @param string The due date of the book.
     * @return The number of days overdue. Returns 0 if not overdue.
     */
    private static int calculateDaysOverdue(String dueDateStr) {
        try {
            // Parse the input string to LocalDate
            LocalDate dueDate = LocalDate.parse(dueDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            LocalDate currentDate = LocalDate.now();
            int daysOverdue = dueDate.isBefore(currentDate) ? (int) dueDate.until(currentDate).getDays() : 0;

            // Ensure the result is non-negative
            return Math.max(0, daysOverdue);
        } catch (Exception e) {
            // Handle invalid input gracefully
            System.out.println("Invalid due date format. Unable to calculate overdue days.");
            return 0;
        }
    }

    
	private static void confirmCheckout(List<Book> selectedBooks) {
        System.out.println("\nConfirm Checkout:");

        for (Book book : selectedBooks) {
            System.out.println("Title: " + book.getTitle() +
                    " | Author: " + book.getAuthor() +
                    " | Quantity: " + book.getQuantity() +
                    " | Due Date: " + book.getDueDate());
        }

        // Add additional confirmation logic if needed
        System.out.println("Do you want to proceed with the checkout? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("yes")) {
            System.out.println("Checkout process canceled.");
            // Add any cleanup logic if necessary
            System.exit(0);
        }

        System.out.println("Checkout successful! Thank you for using the library.");
    }
    
    private static void processReturns(List<Book> selectedBooks) {
        System.out.println("\nProcess Returns:");

        for (Book book : selectedBooks) {
            System.out.println("Enter details for returned book '" + book.getTitle() + "':");
            int returnedQuantity = validateQuantity(new Scanner(System.in));
            if (returnedQuantity != -1) {
                int availableQuantity = book.getQuantity() + returnedQuantity;
                book.setQuantity(availableQuantity);
                System.out.println("Book returned successfully! Available quantity: " + availableQuantity);
            } else {
                System.out.println("Invalid quantity. Return process canceled.");
            }
        }
    }

    /**
     * Displays the catalog of available books.
     */
    private static void displayCatalog() {
        System.out.println("Library Catalog:");
        for (Book book : BookCatalog.getCatalog()) {
            System.out.println(book);
        }
    }

    /**
     * Allows users to select books for checkout.
     *
     * @return List of selected books or null if the checkout process is canceled.
     */
    private static List<Book> selectBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select books for checkout:");
        List<Book> catalog = BookCatalog.getCatalog();

        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ". " + catalog.get(i));
        }

        System.out.println("Enter the book numbers you want to checkout (comma-separated):");
        String input = scanner.nextLine();
        String[] bookNumbers = input.split(",");

        List<Book> selectedBooks = new ArrayList<>();

        if (bookNumbers.length > 10) {
            System.out.println("Error: You cannot checkout more than 10 books in a single transaction.");
            return null;
        }

        // Add logic to convert book numbers to actual Book objects
        for (String bookNumber : bookNumbers) {
            try {
                int index = Integer.parseInt(bookNumber.trim()) - 1;

                int quantity = validateQuantity(scanner);
                if (quantity != -1 && isBookAvailable(catalog.get(index), quantity)) {
                    Book selectedBook = catalog.get(index);
                    selectedBook.setQuantity(quantity);
                    selectedBooks.add(selectedBook);
                } else {
                    System.out.println("Invalid selection. Please re-enter the selection.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid book numbers.");
                return null;
            }
        }

        return selectedBooks;
    }

    /**
     * Validates the quantity entered for a book.
     *
     * @param scanner The Scanner object for user input.
     * @return Valid quantity or -1 if the input is invalid.
     */
    private static int validateQuantity(Scanner scanner) {
        System.out.println("Enter the quantity for the book:");
        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity > 0) {
                return quantity;
            } else {
                System.out.println("Invalid quantity. Please re-enter the quantity.");
                return -1; // Invalid quantity
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return -1; // Invalid input, not a valid integer
        }
    }

    /**
     * Checks if a specified quantity of a book is available in the catalog.
     *
     * @param selectedBook The selected book.
     * @param quantity     The desired quantity.
     * @return True if the requested quantity is available, false otherwise.
     */
    private static boolean isBookAvailable(Book selectedBook, int quantity) {
        return selectedBook.getQuantity() >= quantity;
    }

}