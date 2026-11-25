package models;

public class Owner extends User {

    public Owner(String userId, String username, String password) {
        super(userId, username, password, "OWNER");
    }

    @Override
    public boolean canManageInventory() {
        return true;
    }

    @Override
    public boolean canViewProfitReports() {
        return true;
    }

    @Override
    public boolean canManageUsers() {
        return true;
    }
}