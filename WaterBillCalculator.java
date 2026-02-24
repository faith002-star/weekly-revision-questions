import java.util.Scanner;

public class WaterBillCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter water units consumed: ");
        double units = scanner.nextDouble();
        double totalBill = 0;
        if (units <= 30) {
            totalBill = units * 20;
        } 
        else if (units <= 60) {
            totalBill = (30 * 20) + ((units - 30) * 25);
        } 
        else{
            totalBill = (30 * 20) + (30 * 25) + ((units - 60) * 30);
        }
        System.out.printf("Total water bill: %.2f KES%n", totalBill);
        scanner.close();
    }
}