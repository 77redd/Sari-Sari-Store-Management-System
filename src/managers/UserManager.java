package managers;

import models.Cashier;
import models.Owner;
import models.User;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private User currentUser;

    public UserManager() {
        this.users = new ArrayList<>();
        this.currentUser = null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        ensureDefaultOwner();
    }

    // Ensures at least one Owner account exists on first boot
    public void ensureDefaultOwner() {
        boolean hasOwner = false;
        for (User u : users) {
            if (u instanceof Owner) {
                hasOwner = true;
                break;
            }
        }
        if (!hasOwner) {
            users.add(new Owner("U001", "admin", "admin123"));
        }
    }

    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username) && u.checkPassword(password)) {
                this.currentUser = u;
                return u;
            }
        }
        return null;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isOwnerLoggedIn() {
        return currentUser != null && currentUser instanceof Owner;
    }

    public String generateUserId() {
        int nextNum = users.size() + 1;
        return String.format("U%03d", nextNum);
    }

    public boolean usernameExists(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Owner-only: Add a new Cashier account
    public Cashier addCashier(String username, String password) {
        if (usernameExists(username)) {
            System.out.println("Username already taken.");
            return null;
        }
        String id = generateUserId();
        Cashier newCashier = new Cashier(id, username, password);
        users.add(newCashier);
        return newCashier;
    }

    // Owner-only: Remove a Cashier account
    public boolean removeCashier(String userId) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getUserId().equalsIgnoreCase(userId)) {
                if (u instanceof Owner) {
                    System.out.println("Cannot delete an Owner account.");
                    return false;
                }
                users.remove(i);
                return true;
            }
        }
        System.out.println("Cashier ID not found.");
        return false;
    }

    // Owner-only: Reset Cashier password
    public boolean updateCashierPassword(String userId, String newPassword) {
        for (User u : users) {
            if (u.getUserId().equalsIgnoreCase(userId) && u instanceof Cashier) {
                u.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    public void displayAllUsers() {
        System.out.println("\n--- SYSTEM USER ACCOUNTS ---");
        for (User u : users) {
            System.out.println(u);
        }
    }
}