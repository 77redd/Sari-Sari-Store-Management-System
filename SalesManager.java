package managers;

import models.Expense;
import models.Payment;
import models.SaleItem;
import models.Sales;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Records purchases, processes payments, generates receipts and records expenses. */
public class SalesManager {
    private final List<Sales> sales = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();
    private int saleCounter = 1;
    private int expenseCounter = 1;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // ---------- Sales ----------
    public Sales startSale(String customerName) {
        Sales sale = new Sales(String.format("S%05d", saleCounter++), customerName);
        sales.add(sale);
        return sale;
    }

    public void addItem(Sales sale, String productId, String name,
                        BigDecimal price, BigDecimal cost, int qty) {
        sale.addItem(new SaleItem(productId, name, price, cost, qty));
    }

    /** Processes payment and returns the change due. */
    public BigDecimal processPayment(Sales sale, Payment.Method method, BigDecimal tendered) {
        Payment payment = new Payment(method, sale.getTotal(), tendered);
        sale.setPayment(payment);
        return payment.getChange();
    }

    public String generateReceipt(Sales sale) {
        StringBuilder sb = new StringBuilder();
        String line = "=".repeat(46);
        sb.append(line).append('\n');
        sb.append("                 SALES RECEIPT\n");
        sb.append(line).append('\n');
        sb.append("Receipt #: ").append(sale.getSaleId()).append('\n');
        sb.append("Date     : ").append(sale.getDateTime().format(FMT)).append('\n');
        sb.append("Customer : ").append(sale.getCustomerName()).append('\n');
        sb.append("-".repeat(46)).append('\n');
        sb.append(String.format("%-18s %4s %10s %10s%n", "Item", "Qty", "Price", "Subtotal"));
        for (SaleItem i : sale.getItems()) {
            sb.append(String.format("%-18s %4d %10.2f %10.2f%n",
                    truncate(i.getProductName(), 18), i.getQuantity(), i.getUnitPrice(), i.getSubtotal()));
        }
        sb.append("-".repeat(46)).append('\n');
        sb.append(String.format("%-30s %15.2f%n", "TOTAL:", sale.getTotal()));
        if (sale.isPaid()) {
            Payment p = sale.getPayment();
            sb.append(String.format("%-30s %15s%n", "Payment method:", p.getMethod()));
            sb.append(String.format("%-30s %15.2f%n", "Amount tendered:", p.getAmountTendered()));
            sb.append(String.format("%-30s %15.2f%n", "Change:", p.getChange()));
        } else {
            sb.append("*** UNPAID ***\n");
        }
        sb.append(line).append('\n');
        sb.append("          Thank you for your purchase!\n");
        return sb.toString();
    }

    private String truncate(String s, int max) {
        return s.length() <= max ? s : s.substring(0, max - 1) + ".";
    }

    /** Only completed (paid) sales. */
    public List<Sales> getPaidSales() {
        List<Sales> paid = new ArrayList<>();
        for (Sales s : sales) if (s.isPaid()) paid.add(s);
        return Collections.unmodifiableList(paid);
    }

    public List<Sales> getAllSales() { return Collections.unmodifiableList(sales); }

    // ---------- Expenses ----------
    public Expense recordExpense(String description, String category, BigDecimal amount, LocalDate date) {
        Expense e = new Expense(String.format("E%05d", expenseCounter++), description, category, amount, date);
        expenses.add(e);
        return e;
    }

    public List<Expense> getExpenses() { return Collections.unmodifiableList(expenses); }
}
