import java.util.Arrays;

/**
 * Class Student: Manages student data and academic calculations.
 */
class Student {
    // Private attributes for encapsulation
    private String studentId;
    private String name;
    private int[] marks; // Array of size 5

    // Constructor to initialize student details
    public Student(String studentId, String name, int[] marks) {
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
    }

    // Accessors
    public String getName() { return name; }

    /**
     * Calculates and returns the sum of the 5 subject marks.
     */
    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    /**
     * Calculates and returns the average marks.
     */
    public double calculateAverage() {
        return calculateTotal() / 5.0;
    }

    /**
     * Determines the grade based on average marks.
     */
    public char findGrade() {
        double avg = calculateAverage();
        if (avg >= 80) return 'A';
        if (avg >= 60) return 'B';
        if (avg >= 50) return 'C';
        return 'F';
    }

    /**
     * Displays a formatted report for the student.
     */
    public void displayStudentReport() {
        System.out.printf("ID: %-8s | Name: %-15s | Total: %-3d | Avg: %-5.2f | Grade: %c%n", 
            studentId, name, calculateTotal(), calculateAverage(), findGrade());
    }
}

/**
 * Main Class: Handles object creation and logic for the highest average.
 */
public class StudentReportSystem {
    public static void main(String[] args) {
        
        // 1. Create three Student objects with different marks
        Student s1 = new Student("S101", "Alice", new int[]{85, 90, 78, 92, 88});
        Student s2 = new Student("S102", "Bob", new int[]{55, 60, 58, 62, 50});
        Student s3 = new Student("S103", "Charlie", new int[]{40, 45, 50, 35, 42});

        Student[] students = {s1, s2, s3};

        // 2. Calculate and display reports for each student
        System.out.println("--- STUDENT ACADEMIC REPORTS ---");
        for (Student s : students) {
            s.displayStudentReport();
        }

        // 3. Identify and display the student with the highest average
        Student topStudent = students[0];
        for (int i = 1; i < students.length; i++) {
            if (students[i].calculateAverage() > topStudent.calculateAverage()) {
                topStudent = students[i];
            }
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("TOP PERFORMER: " + topStudent.getName() + 
                           " with an average of " + topStudent.calculateAverage());
        System.out.println("--------------------------------------------------------------------------");
    }
}