import java.util.Scanner;

public class TaxiFareSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter distance traveled (km): ");
        double distance = input.nextDouble();

        // Calculate and display the result
        double totalFare = calculateFare(distance);
        System.out.println("The total fare is: KSh. " + totalFare);

        input.close();
    }

    /**
     * Calculates fare based on distance.
     * Rate: KSh. 50 per km
     */
    public static double calculateFare(double distance) {
        double ratePerKm = 50.0;
        return distance * ratePerKm;
    }
}