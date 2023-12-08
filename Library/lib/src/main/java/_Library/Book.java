// File: _Library/Book.java

package _Library;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private int quantity;
    private String dueDate;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    // Getters and setters (not shown for brevity)

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                '}';
    }

	public void setQuantity(int quantity2) {
		this.quantity = quantity2;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDueDate() {
		// TODO Auto-generated method stub
		return this.dueDate;
	}

	public void setDueDate(LocalDate minusDays) {
		// TODO Auto-generated method stub
		
	}

	
}
