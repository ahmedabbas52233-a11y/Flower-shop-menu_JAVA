import java.util.Scanner;

public class BouquetOrders {
    private static final int MAX_ORDERS = 100;
    private static final String[][] orders = new String[MAX_ORDERS][4];
    private static int orderCount = 0;

    // Arrays to store names
    private static final String[] FLOWER_NAMES = {"Rose", "Tulip", "Daisy", "Sunflower", "Lily", "Orchid", "Carnation", "Hydrangea", "Lavender", "Marigold", "Peony", "Chrysanthemum"};
    private static final String[] COLOUR_NAMES = {"Red", "Pink", "White", "Yellow", "Purple", "Orange", "Blue"};
    private static final String[] SIZE_NAMES = {"Small", "Medium", "Large"};

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            OUTER:
            while (true) {
                System.out.println("1.  Order bouquet and get the price");
                System.out.println("2.  Display statistics");
                System.out.println("3.  Exit");
                System.out.print("Enter your choice: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid choice. Please type a value between 1 & 3.");
                    scanner.next(); // Clear the invalid input
                    continue;
                }
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> orderDetailsAndPriceCalculation(scanner);
                    case 2 -> summaryStatistics();
                    case 3 -> {
                        System.out.println("Exiting the program. Have a pleasant day");
                        break OUTER;
                    }
                    default -> System.out.println("Invalid choice. Enter 1 to place an order, 2 to display statistics, or 3 to exit.");
                }
            }
        }
    }

    public static void orderDetailsAndPriceCalculation(Scanner scanner) {   // Display the flower Choices
        System.out.println("Enter Flower Type:");
        for (int i = 0; i < FLOWER_NAMES.length; i++) {
            System.out.println((i + 1) + ". " + FLOWER_NAMES[i]);
        } 

         // Read the flower type
        int flowerTypeIndex;
        if (scanner.hasNextInt()) {
            flowerTypeIndex = scanner.nextInt() - 1;
            if (flowerTypeIndex < 0 || flowerTypeIndex >= FLOWER_NAMES.length) {
                System.out.println("Invalid input. Please type a number between 1 and " + FLOWER_NAMES.length);
                return;
            }
        } else {
            System.out.println("Invalid input. Please type a value between 1 & 12.");
            scanner.next(); // Remove the incorrect input choice
            return;
        }
        String flowerType = FLOWER_NAMES[flowerTypeIndex];

        System.out.println("Enter Colour:");
        for (int i = 0; i < COLOUR_NAMES.length; i++) {
            System.out.println((i + 1) + ". " + COLOUR_NAMES[i]);
        }
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please type a value between 1 & 7.");
            scanner.next(); // Remove the incorrect input choice
            return;
        }
        int colourIndex = scanner.nextInt() - 1;
        if (colourIndex < 0 || colourIndex >= COLOUR_NAMES.length) {
            System.out.println("Invalid input. Please type a value between 1 and " + COLOUR_NAMES.length);
            return;
        }
        String colour = COLOUR_NAMES[colourIndex];

        System.out.println("Enter Size:");
        for (int i = 0; i < SIZE_NAMES.length; i++) {
            System.out.println((i + 1) + ". " + SIZE_NAMES[i]);
        }
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please type a value between 1 & 3.");
            scanner.next(); // Clear the invalid input
            return;
        }
        int sizeIndex = scanner.nextInt() - 1;
        if (sizeIndex < 0 || sizeIndex >= SIZE_NAMES.length) {
            System.out.println("Invalid input. Please type a number between 1 and " + SIZE_NAMES.length);
            return;
        }
        String size = SIZE_NAMES[sizeIndex];

        // Calculate the price based on the size
        double sizePrice;
        switch (size.toLowerCase()) {
            case "small" -> sizePrice = 12.0;
            case "medium" -> sizePrice = 17.0;
            case "large" -> sizePrice = 22.0;
            default -> {
                System.out.println("Invalid size. Please enter small, medium, or large.");
                return;
            }
        }

        // Calculate price based on the flower and color
        double flowerPrice = 1.2; // Assume a fixed price for simplicity
        double colorPrice = 1.3; // Assume a fixed price for simplicity
        double price = (flowerPrice + colorPrice) * sizePrice;

        // Store the order details
        orders[orderCount][0] = size;
        orders[orderCount][1] = flowerType;
        orders[orderCount][2] = colour;
        orders[orderCount][3] = String.valueOf(price);
        orderCount++;

        // Display the order details
        System.out.println("Order Details:");
        System.out.println("Size: " + size + " (Price: " + sizePrice + ")");
        System.out.println("Flower Type: " + flowerType + " (Price: " + flowerPrice + ")");
        System.out.println("Colour: " + colour + " (Price: " + colorPrice + ")");
        System.out.println("Total Price: " + price);
    }

    public static void summaryStatistics() {
        System.out.println("Summary statistics provided.");
    }
}
