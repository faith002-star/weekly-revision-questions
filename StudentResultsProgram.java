import java.io.*;
import java.util.*;

// i. Define structure (class)
class Student implements Serializable {
    String name;
    String regNumber;
    int totalMarks;

    public Student(String name, String regNumber, int totalMarks) {
        this.name = name;
        this.regNumber = regNumber;
        this.totalMarks = totalMarks;
    }
}

public class StudentResultsProgram {
    public static void main(String[] args) {
        String fileName = "results.dat";

        // Create sample student data
        Student s1 = new Student("Alice", "REG001", 85);
        Student s2 = new Student("Bob", "REG002", 78);
        Student s3 = new Student("Charlie", "REG003", 92);

        // ii. Write records to binary file
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(s1);
            oos.writeObject(s2);
            oos.writeObject(s3);

            oos.close();
            fos.close();

            System.out.println("Records written to file successfully.\n");

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

        // iii. Read records from file and display name + marks
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("Student Results:\n");

            while (true) {
                try {
                    Student student = (Student) ois.readObject();

                    System.out.println("Name: " + student.name +
                                       ", Marks: " + student.totalMarks);

                } catch (EOFException e) {
                    break; // End of file
                }
            }

            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}