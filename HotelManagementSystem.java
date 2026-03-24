import java.util.Scanner;

public class HotelManagementSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // --- TASK 1: 1D Array (Weekly Revenue) ---
        System.out.println("--- TASK 1: WEEKLY REVENUE ---");
        double[] revenue = new double[7];
        double totalRev = 0;
        for (int i = 0; i < 7; i++) {
            System.out.print("Enter revenue for day " + (i + 1) + ": ");
            revenue[i] = input.nextDouble();
            totalRev += revenue[i];
        }
        System.out.println("Total Weekly Revenue: KSh. " + totalRev);
        System.out.println("Average Daily Revenue: KSh. " + (totalRev / 7));

        // --- TASK 2: 2D Array (Single Branch Occupancy) ---
        System.out.println("\n--- TASK 2: SINGLE BRANCH OCCUPANCY ---");
        int[][] occupancy = new int[5][10];
        for (int f = 0; f < 5; f++) {
            int floorOccupied = 0;
            for (int r = 0; r < 10; r++) {
                occupancy[f][r] = (Math.random() > 0.5) ? 1 : 0; // Random data
                if (occupancy[f][r] == 1) floorOccupied++;
            }
            System.out.println("Floor " + (f + 1) + ": " + floorOccupied + " Occupied, " + (10 - floorOccupied) + " Vacant");
        }

        // --- TASK 3: 3D Array (Chain-wide Occupancy) ---
        System.out.println("\n--- TASK 3: TOTAL CHAIN OCCUPANCY ---");
        int[][][] chain = new int[3][5][10];
        int totalChainOccupied = 0;
        for (int b = 0; b < 3; b++) {
            for (int f = 0; f < 5; f++) {
                for (int r = 0; r < 10; r++) {
                    chain[b][f][r] = (Math.random() > 0.5) ? 1 : 0;
                    if (chain[b][f][r] == 1) totalChainOccupied++;
                }
            }
        }
        System.out.println("Total Occupied Rooms (All 3 Branches): " + totalChainOccupied);

        input.close();
    }
}