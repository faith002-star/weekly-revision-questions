import java.util.Scanner;

public class ElectricityBillingSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of units consumed: ");
        int units = input.nextInt();

        // Calling the method and storing the result
        double totalBill = calculateElectricBill(units);

        System.out.println("Total Bill Amount: KSh. " + totalBill);
        
        input.close();
    }

    // The method we created earlier
    public static double calculateElectricBill(int units) {
        double totalBill = 0;

        if (units <= 100) {
            totalBill = units * 10;
        } else if (units <= 200) {
            totalBill = (100 * 10) + ((units - 100) * 15);
        } else {
            totalBill = (100 * 10) + (100 * 15) + ((units - 200) * 20);
        }

        return totalBill;
    }
}