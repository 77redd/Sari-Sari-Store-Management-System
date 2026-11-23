package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_DIR = "data/";

    // File paths for each module
    public static final String PRODUCTS_FILE = DATA_DIR + "products.csv";
    public static final String SALES_FILE = DATA_DIR + "sales.csv";
    public static final String EXPENSES_FILE = DATA_DIR + "expenses.csv";
    public static final String CUSTOMERS_FILE = DATA_DIR + "customers.csv";
    public static final String USERS_FILE = DATA_DIR + "users.csv";

    // Ensures the data/ folder and empty files exist on first run
    public static void initializeFiles() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String[] files = {PRODUCTS_FILE, SALES_FILE, EXPENSES_FILE, CUSTOMERS_FILE, USERS_FILE};
        for (String filePath : files) {
            File file = new File(filePath);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                System.out.println("Error creating file " + filePath + ": " + e.getMessage());
            }
        }
    }

    // Generic method to read all non-empty lines from a CSV file
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from " + filePath + ": " + e.getMessage());
        }
        return lines;
    }

    // Generic method to overwrite a CSV file with updated records
    public static void writeLines(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to " + filePath + ": " + e.getMessage());
        }
    }

    /*
     * =========================================================================
     * INTEGRATION HOOKS: Uncomment and connect these once teammates merge models
     * =========================================================================
     *
     * public static void saveProducts(ArrayList<Product> products) {
     *     List<String> lines = new ArrayList<>();
     *     for (Product p : products) {
     *         lines.add(p.toCSV());
     *     }
     *     writeLines(PRODUCTS_FILE, lines);
     * }
     *
     * public static void loadProducts(InventoryManager inventoryManager) {
     *     List<String> lines = readLines(PRODUCTS_FILE);
     *     for (String line : lines) {
     *         Product p = Product.fromCSV(line);
     *         if (p != null) inventoryManager.addProduct(p);
     *     }
     * }
     */
}