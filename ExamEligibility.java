import java.util.Scanner;

public class ExamEligibility {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user for Attendance
        System.out.print("Enter attendance percentage: ");
        double attendance = input.nextDouble();

        // Prompt user for Average Marks
        System.out.print("Enter average marks: ");
        double marks = input.nextDouble();

        // Eligibility Logic
        if (attendance >= 75 && marks >= 40) {
            System.out.println("Eligible for final exams.");
        } else {
            System.out.println("Not eligible.");
        }

        input.close();
    }
}