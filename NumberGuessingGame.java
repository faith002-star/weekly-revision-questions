import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(20) + 1;
        int guess = 0;
        int attempts = 0;

        System.out.println("I'm thinking of a number between 1 and 20. Can you guess it?");

        while (guess != secretNumber) {
            System.out.print("Enter your guess: ");
            guess = input.nextInt();
            attempts++; 
            if (guess > secretNumber) {
                System.out.println("Too high!");
            } else if (guess < secretNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Congratulations!");
                System.out.println("It took you " + attempts + " attempts to win.");
            }
        }
        input.close();
    }
}