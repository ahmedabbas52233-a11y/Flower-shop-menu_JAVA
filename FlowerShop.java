import java.util.Scanner;

public class FlowerShop {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenu();  // Display the Menu options
                if (!scanner.hasNextInt()) {  // Check if input is an integer
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    scanner.next(); // Clear the invalid input
                    continue;  // Continue to the next iteration
                }
                int userInput = scanner.nextInt();  // Read user input

                if (userInput < 1 || userInput > 3) {   // Check if the input is in the valid range
                    System.out.println("Invalid input. Please try again.");
                    continue;  // Continue to the next iteration
                }

                switch (userInput) {   // Execute the correspomding method based on user input
                    case 1 -> orderBouquetAndGetPrice();  // Option 1
                    case 2 -> displayStatistics();  // Option 2
                    case 3 -> {  // Option 3
                        System.out.println("Exiting the program. Have a nice day.");
                        System.exit(0);  // Exit the program
                    }
                }
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Flower shop menu");
        System.out.println("1. Order a bouquet and get the price.");
        System.out.println("2. Display statistics");
        System.out.println("3. Exit");
    }

    public static void orderBouquetAndGetPrice() {
        System.out.println("A bouquet has been ordered");
    }

    public static void displayStatistics() {
        System.out.println("Summary statistics provided");
    }
}
