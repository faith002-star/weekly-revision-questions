import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LibraryTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("--- Library Book Entry System ---");
        System.out.print("Enter the title of the book to record: ");
        String bookTitle = input.nextLine();

        // The second parameter 'true' enables append mode
        try (FileWriter writer = new FileWriter("borrowed_books.txt", true)) {
            writer.write(bookTitle + System.lineSeparator());
            
            // Confirmation message
            System.out.println("Success: \"" + bookTitle + "\" has been stored in borrowed_books.txt.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        } finally {
            input.close();
        }
    }
}