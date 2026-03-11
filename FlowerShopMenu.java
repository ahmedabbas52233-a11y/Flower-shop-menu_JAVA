import java.util.InputMismatchException;
import java.util.Scanner;
public class FlowerShopMenu {
    // Arrays to store mark-up values
    private static final double[] FLOWER_PRICES = {1.2, 5.3, 3.0, 8.0, 7.5, 4.8, 2.8, 6.5, 9.2, 1.9, 8.6, 3.5};
    private static final double[] COLOUR_PRICES = {9.3, 8.2, 6.1, 7.4, 5.6, 4.0, 3.5};
    private static final double[] SIZE_PRICES = {5.5, 7.5, 9.5};
    // Arrays to store names
    private static final String[] FLOWER_NAMES = {"Rose", "Tulip", "Daisy", "Sunflower", "Lily", "Orchid", "Carnation", "Hydrangea", "Lavender", "Marigold", "Peony", "Chrysanthemum"};
    private static final String[] COLOUR_NAMES = {"Red", "Pink", "White", "Yellow", "Purple", "Orange", "Blue"};
    
    // Constants
    private static final int MAX_ORDERS = 10;

    // Array to store order details
    private static final String[][] orders = new String[MAX_ORDERS][4];
    private static int orderCount = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenu();
                int userInput = getUserInput(scanner, 1, 3);

                switch (userInput) {
                    case 1 -> orderBouquetAndGetPrice(scanner);
                    case 2 -> displayStatistics();
                    case 3 -> {
                        System.out.println("Exiting the program. Have a pleasant day!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice. Please type a value between 1 and 3.");
                }
            }
        }
    }

    // Displays the main menu
    public static void displayMenu() {
        System.out.println("Flower shop menu");
        System.out.println("1. Order a bouquet and get the price.");
        System.out.println("2. Display statistics");
        System.out.println("3. Exit");
    }

    // Handles the ordering of a bouquet and calculates its price
    public static void orderBouquetAndGetPrice(Scanner scanner) {
        orderDetailsAndPriceCalculation(scanner);
    }

    // Method to display summary statistics
    public static void displayStatistics() {
        summaryStatistics();
    }

    // Method to calculate and display summary statistics
    public static void summaryStatistics() {
        // Initialize variables for statistics
        double totalRevenue = 0.0;
        double minPrice = Double.MAX_VALUE;
        double maxPrice = Double.MIN_VALUE;
        double price;

        // Iterate through all orders to calculate statistics
        for (int i = 0; i < orderCount; i++) {
            price = Double.parseDouble(orders[i][3]); // Assuming price is at index 3
            totalRevenue += price;
            minPrice = Math.min(minPrice, price);
            maxPrice = Math.max(maxPrice, price);
        }

        double averagePrice = (orderCount > 0) ? (totalRevenue / orderCount) : 0;
        double priceRange = maxPrice - minPrice;

        // Display statistics in a tabular format
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s%n", "Minimum Price", "Maximum Price", "Price Range", "Total Orders", "Total Revenue", "Average Price");
        System.out.printf("%-20.2f %-20.2f %-20.2f %-20d %-20.2f %-20.2f%n", minPrice, maxPrice, priceRange, orderCount, totalRevenue, averagePrice);
    }

    // Handles the details of the order and calculates the price
    public static void orderDetailsAndPriceCalculation(Scanner scanner) {
        // Ensure the user input is within the bounds of the FLOWER_PRICES array
        int flowerType = getUserInput(scanner, 1, FLOWER_PRICES.length, "Enter Flower Type (1-" + FLOWER_PRICES.length + "):", FLOWER_NAMES);
        // Ensure the user input is within the bounds of the COLOUR_PRICES array
        int colour = getUserInput(scanner, 1, COLOUR_PRICES.length, "Enter Colour (1-" + COLOUR_PRICES.length + "):", COLOUR_NAMES);
        int size = getUserInput(scanner, 1, SIZE_PRICES.length, "Enter Size (1-" + SIZE_PRICES.length + "):", new String[]{"Small", "Medium", "Large"});

        // Calculate price
        double flowerPrice = FLOWER_PRICES[flowerType - 1];
        double colourPrice = COLOUR_PRICES[colour - 1];
        double sizePrice = SIZE_PRICES[size - 1];
        double totalPrice = (flowerPrice + colourPrice) * sizePrice;

        // Store order details
        orders[orderCount][0] = FLOWER_NAMES[flowerType - 1];
        orders[orderCount][1] = COLOUR_NAMES[colour - 1];
        orders[orderCount][2] = String.valueOf(size);
        orders[orderCount][3] = String.valueOf(totalPrice);
        orderCount++;

        // Display price and order details
        displayOrderDetails(flowerType, colour, size, totalPrice);
    }

    // Displays the details of the order
    public static void displayOrderDetails(int flowerType, int colour, int size, double totalPrice) {
        System.out.println("Order Details:");
        System.out.println("Flower: " + FLOWER_NAMES[flowerType - 1]);
        System.out.println("Colour: " + COLOUR_NAMES[colour - 1]);
        System.out.println("Size: " + size);
        System.out.println("Total Price: " + totalPrice);
    }

    // Gets the user input and validates it
    public static int getUserInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please type a value between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please type a value.");
                scanner.next(); // Remove the incorrect input choice
            }
        }
    }

    // Gets the user input with a prompt and options
    public static int getUserInput(Scanner scanner, int min, int max, String prompt, String[] options) {
        System.out.println(prompt);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        return getUserInput(scanner, min, max);
    }
}
