import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter temperature in Fahrenheit (°F): ");
        double fahrenheit = input.nextDouble();

        // Call the method and display the result
        double celsius = convertToCelsius(fahrenheit);
        
        // Formatting to 2 decimal places for a cleaner look
        System.out.printf("%.1f°F is equivalent to %.1f°C\n", fahrenheit, celsius);

        input.close();
    }

    /**
     * Converts Fahrenheit to Celsius
     * Formula: C = (F - 32) * (5/9)
     */
    public static double convertToCelsius(double fahrenheit) {
        // We use 5.0 and 9.0 to ensure floating-point division
        return (fahrenheit - 32) * (5.0 / 9.0);
    }
}