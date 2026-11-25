package models;

public class Cashier extends User {

    public Cashier(String userId, String username, String password) {
        super(userId, username, password, "CASHIER");
    }

    @Override
    public boolean canManageInventory() {
        return false; // Cashiers can view products during sales, not edit/delete stock
    }

    @Override
    public boolean canViewProfitReports() {
        return false;
    }

    @Override
    public boolean canManageUsers() {
        return false;
    }
}