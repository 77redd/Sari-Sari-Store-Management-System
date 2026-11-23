import java.util.Scanner;
import util.FileHandler;

// TODO: Uncomment imports as teammates merge their branches into main
// import models.*;
// import managers.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // TODO: Instantiate your team's managers here once merged
    // private static InventoryManager inventoryManager = new InventoryManager();
    // private static SalesManager salesManager = new SalesManager();
    // private static SalesReportManager reportManager = new SalesReportManager();
    // private static CustomerManager customerManager = new CustomerManager();
    // private static UserManager userManager = new UserManager();

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   SARI-SARI STORE MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        // 1. Initialize and load saved records on startup
        FileHandler.initializeFiles();
        loadAllData();

        boolean appRunning = true;
        while (appRunning) {
            System.out.println("\n--- MAIN LOGIN ---");
            System.out.println("1. Login");
            System.out.println("2. Exit & Save");
            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    saveAllData();
                    System.out.println("All records saved. Goodbye!");
                    appRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void handleLogin() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        /*
         * TODO: Replace this temporary check with UserManager login once merged:
         * User currentUser = userManager.login(username, password);
         * if (currentUser == null) {
         *     System.out.println("Invalid credentials!");
         *     return;
         * }
         * currentUser.showMenu(); // Polymorphism!
         */

        // Temporary simulation so you can test the menus right now
        if (username.equalsIgnoreCase("owner") && password.equals("1234")) {
            showOwnerMenu();
        } else if (username.equalsIgnoreCase("cashier") && password.equals("1234")) {
            showCashierMenu();
        } else {
            System.out.println("Invalid username or password. (Hint: use owner/1234 or cashier/1234 for testing)");
        }
    }

    private static void showOwnerMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n=== OWNER MENU ===");
            System.out.println("1. Manage Products (Add / Edit / Delete / View)");
            System.out.println("2. Restock & View Low-Stock Alerts");
            System.out.println("3. New Sale (POS)");
            System.out.println("4. Manage Customer Utang (Credit)");
            System.out.println("5. Record Expense");
            System.out.println("6. View Sales & Profit Reports");
            System.out.println("7. Manage Cashier Accounts");
            System.out.println("8. Logout");

            int choice = getIntInput("Select an operation: ");
            switch (choice) {
                case 1:
                    System.out.println("[Hook] Calling Dwayne's InventoryManager product CRUD...");
                    break;
                case 2:
                    System.out.println("[Hook] Calling Dwayne's InventoryManager restock/low-stock...");
                    break;
                case 3:
                    System.out.println("[Hook] Calling SalesManager new sale flow...");
                    break;
                case 4:
                    System.out.println("[Hook] Calling CustomerManager utang module...");
                    break;
                case 5:
                    System.out.println("[Hook] Calling SalesReportManager record expense...");
                    break;
                case 6:
                    System.out.println("[Hook] Calling SalesReportManager sales & profit report...");
                    break;
                case 7:
                    System.out.println("[Hook] Calling UserManager cashier management...");
                    break;
                case 8:
                    saveAllData();
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1-8.");
            }
        }
    }

    private static void showCashierMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n=== CASHIER MENU ===");
            System.out.println("1. New Sale (POS)");
            System.out.println("2. Check Product Price & Stock");
            System.out.println("3. Record Customer Utang Payment");
            System.out.println("4. Logout");

            int choice = getIntInput("Select an operation: ");
            switch (choice) {
                case 1:
                    System.out.println("[Hook] Calling SalesManager new sale flow...");
                    break;
                case 2:
                    System.out.println("[Hook] Calling Dwayne's InventoryManager searchProduct()...");
                    break;
                case 3:
                    System.out.println("[Hook] Calling CustomerManager process debt payment...");
                    break;
                case 4:
                    saveAllData();
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1-4.");
            }
        }
    }

    private static void loadAllData() {
        System.out.println("Loading saved data...");
        // TODO: Call FileHandler.loadProducts(inventoryManager), loadUsers(userManager), etc.
    }

    private static void saveAllData() {
        System.out.println("Saving all data to files...");
        // TODO: Call FileHandler.saveProducts(...), saveUsers(...), etc.
    }

    // --- Safe Scanner Input Helpers (Prevents InputMismatchException crashes) ---
    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}