import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SalesCalculator {
    public static void main(String[] args) {
        double totalSales = 0.0;
        File file = new File("sales.txt");

        // The try-with-resources statement ensures the file is closed automatically
        try (Scanner fileScanner = new Scanner(file)) {
            
            while (fileScanner.hasNext()) {
                if (fileScanner.hasNextDouble()) {
                    totalSales += fileScanner.nextDouble();
                } else {
                    // Skip any non-numeric data to prevent errors
                    fileScanner.next();
                }
            }

            System.out.printf("Total sales for the day: $%.2f%n", totalSales);

        } catch (FileNotFoundException e) {
            System.err.println("Error: The file 'sales.txt' was not found.");
        }
    }
}