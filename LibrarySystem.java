import java.util.ArrayList;

/**
 * Class Book: Represents an individual book.
 * Attributes are private to ensure encapsulation.
 */
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    // Constructor to initialize all attributes
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false; // Initial status is always available
    }

    // Accessors (Getters)
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }

    // Methods for book transactions
    public void issueBook() {
        if (!isIssued) {
            isIssued = true;
            System.out.println("SUCCESS: '" + title + "' has been issued.");
        } else {
            System.out.println("FAILED: '" + title + "' is already issued to someone else.");
        }
    }

    public void returnBook() {
        if (isIssued) {
            isIssued = false;
            System.out.println("SUCCESS: '" + title + "' has been returned.");
        } else {
            System.out.println("NOTE: '" + title + "' was not marked as issued.");
        }
    }

    public void displayBookInfo() {
        String status = isIssued ? "[Issued]" : "[Available]";
        System.out.printf("%-15s | %-20s | %-15s | %s%n", isbn, title, author, status);
    }
}

/**
 * Class Library: Manages an array of Book objects.
 */
class Library {
    private Book[] books;
    private int bookCount;

    public Library() {
        // Fixed-size array as per requirements (max 10)
        this.books = new Book[10];
        this.bookCount = 0;
    }

    // Adds a Book object to the array
    public void addBook(Book b) {
        if (bookCount < 10) {
            books[bookCount] = b;
            bookCount++;
            System.out.println("Added to Library: " + b.getTitle());
        } else {
            System.out.println("Error: Library capacity reached.");
        }
    }

    // Finds a book by ISBN and issues it
    public void issueBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                books[i].issueBook();
                return;
            }
        }
        System.out.println("Error: ISBN " + isbn + " not found.");
    }

    // Finds a book by ISBN and returns it
    public void returnBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                books[i].returnBook();
                return;
            }
        }
        System.out.println("Error: ISBN " + isbn + " not found.");
    }

    // Lists only the books where isIssued is false
    public void listAvailableBooks() {
        System.out.println("\n--- LIST OF AVAILABLE BOOKS ---");
        System.out.printf("%-15s | %-20s | %-15s | %s%n", "ISBN", "Title", "Author", "Status");
        System.out.println("--------------------------------------------------------------------------");
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isIssued()) {
                books[i].displayBookInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No books currently available on the shelf.");
        System.out.println("--------------------------------------------------------------------------\n");
    }
}

/**
 * Main Class: The entry point of the program.
 */
public class LibrarySystem {
    public static void main(String[] args) {
        Library myLibrary = new Library();

        // 1. Create at least 3 Book objects
        Book b1 = new Book("Java Basics", "John Smith", "ISBN-101");
        Book b2 = new Book("Python Guide", "Jane Doe", "ISBN-202");
        Book b3 = new Book("C++ Mastery", "Alan Turing", "ISBN-303");

        // 2. Add them to the Library
        System.out.println("--- Initializing Library ---");
        myLibrary.addBook(b1);
        myLibrary.addBook(b2);
        myLibrary.addBook(b3);

        // 3. Issue one book and return another
        System.out.println("\n--- Processing Transactions ---");
        myLibrary.issueBook("ISBN-202"); // Issue Python Guide
        myLibrary.issueBook("ISBN-303"); // Issue C++ Mastery
        myLibrary.returnBook("ISBN-202"); // Return Python Guide (now available again)

        // 4. List all available books
        // Result should show Java Basics and Python Guide as available
        myLibrary.listAvailableBooks();
    }
}