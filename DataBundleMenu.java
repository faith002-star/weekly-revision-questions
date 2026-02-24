import java.util.scanner
public class DataBundleMenu{
    public static void main (string) [] args {
        Scanner scanner = new Scanner(system.in)
        system.out.println("Display data bundle menu");
         system.out.println("1. 100 MB - 50 KES");
         system.out.println("2. 500 MB - 200 KES");
         system.out.println("3. 1 GB - 350 KES");
         system.out.println("4. 2 GB - 600 KES");

         system.out.println("Enter your choice (1-4):");
         int choice = scanner.nextInt();

         switch (choice) {
            case 1:
                System.out.println("You selected 100 MB. Cost: 50 KES");
                break;
            case 2:
                System.out.println("You selected 500 MB. Cost: 200 KES");
                break;
            case 3:
                System.out.println("You selected 1 GB. Cost: 350 KES");
                break;
            case 4:
                System.out.println("You selected 2 GB. Cost: 600 KES");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        scanner.close();

    }
}