package models;

import java.math.BigDecimal;
import java.time.LocalDate;

/** A business expense (rent, utilities, supplies, salaries, etc.). */
public class Expense {
    private final String expenseId;
    private final String description;
    private final String category;
    private final BigDecimal amount;
    private final LocalDate date;

    public Expense(String expenseId, String description, String category,
                   BigDecimal amount, LocalDate date) {
        if (amount.signum() <= 0) throw new IllegalArgumentException("Expense amount must be positive.");
        this.expenseId = expenseId;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getExpenseId()   { return expenseId; }
    public String getDescription() { return description; }
    public String getCategory()    { return category; }
    public BigDecimal getAmount()  { return amount; }
    public LocalDate getDate()     { return date; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s", expenseId, date, category, description, amount);
    }
}
