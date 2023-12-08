package _SystemLibrary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LibraryManagement {
	
	public static void main(String[] args) {
        displayCatalog();
        List<Book> selectedBooks = selectBooks();
        calculateDueDates(selectedBooks);
        calculateLateFees(selectedBooks);
    }
	
	private static void displayCatalog() {
        System.out.println("Library Catalog:");
        for (Book book : BookCatalog.getCatalog()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - Available: " + book.getQuantity());
        }
    }

	static List<Book> selectBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select books for checkout:");
        List<Book> catalog = BookCatalog.getCatalog();

        for (int i = 0; i < catalog.size(); i++) {
            Book book = catalog.get(i);
            System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor());
        }

        System.out.println("Enter the book numbers you want to checkout (comma-separated):");
        String input = scanner.nextLine();
        String[] bookNumbers = input.split(",");
        
        List<Book> selectedBooks = new ArrayList<>();
        
     // Add logic to convert book numbers to actual Book objects
        for (String bookNumber : bookNumbers) {
            int index = Integer.parseInt(bookNumber.trim()) - 1;

            // Validate quantity before adding to the selected books
            int quantity = validateQuantity(scanner);
            if (quantity != -1) {
                Book selectedBook = catalog.get(index);
                selectedBook.setQuantity(quantity);
                selectedBooks.add(selectedBook);
            } else {
                System.out.println("Invalid quantity. Please re-enter the quantity.");
                // Optionally, you can choose to re-prompt for the same book or exit the selection process
                break;
            }
        }

        return selectedBooks;
    }
	
	static int validateQuantity(Scanner scanner) {
        System.out.println("Enter the quantity for the book:");
        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity > 0) {
                return quantity;
            } else {
                return -1; // Invalid quantity
            }
        } catch (NumberFormatException e) {
            return -1; // Invalid input, not a valid integer
        }
    }
	
	private static boolean isBookAvailable(Book selectedBook, int quantity) {
        return selectedBook.getQuantity() >= quantity;
    }
	
	static void calculateDueDates(List<Book> selectedBooks) {
        LocalDate currentDate = LocalDate.now();

        System.out.println("\nDue Dates for Checked-out Books:");

        for (Book book : selectedBooks) {
            LocalDate dueDate = currentDate.plusDays(14); // Assigning a standard loan period of 14 days
            System.out.println(book.getTitle() + " - Due Date: " + dueDate);
            // Optionally, you can set the dueDate in the Book object for further processing
        }
    }
	
	private static void calculateLateFees(List<Book> selectedBooks) {
        double totalLateFees = 0;

        LocalDate currentDate = LocalDate.now();

        System.out.println("\nLate Fees for Checked-out Books:");

        for (Book book : selectedBooks) {
            LocalDate dueDate = currentDate.plusDays(14); // Assigning a standard loan period of 14 days

            // Calculate late fees if the book is overdue
            if (currentDate.isAfter(dueDate)) {
                long daysOverdue = currentDate.toEpochDay() - dueDate.toEpochDay();
                double lateFee = daysOverdue * 1.0; // Apply a late fee of $1 per day
                totalLateFees += lateFee;

                System.out.println(book.getTitle() + " - Late Fee: $" + lateFee);
            }
        }

        System.out.println("Total Late Fees for the user's account: $" + totalLateFees);
    }

	
	
}