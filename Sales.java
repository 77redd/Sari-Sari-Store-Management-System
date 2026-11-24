package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A customer transaction made up of items and (eventually) a payment. */
public class Sales {
    private final String saleId;
    private final String customerName;
    private final LocalDateTime dateTime;
    private final List<SaleItem> items = new ArrayList<>();
    private Payment payment;

    public Sales(String saleId, String customerName) {
        this.saleId = saleId;
        this.customerName = (customerName == null || customerName.isBlank()) ? "Walk-in" : customerName;
        this.dateTime = LocalDateTime.now();
    }

    public void addItem(SaleItem item) {
        ensureNotPaid();
        for (SaleItem existing : items) {
            if (existing.getProductId().equals(item.getProductId())) {
                existing.addQuantity(item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public boolean removeItem(String productId) {
        ensureNotPaid();
        return items.removeIf(i -> i.getProductId().equals(productId));
    }

    public BigDecimal getTotal() {
        return items.stream().map(SaleItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCost() {
        return items.stream().map(SaleItem::getTotalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getProfit() { return getTotal().subtract(getTotalCost()); }

    public void setPayment(Payment payment) {
        ensureNotPaid();
        if (items.isEmpty()) throw new IllegalStateException("Cannot pay for an empty sale.");
        this.payment = payment;
    }

    private void ensureNotPaid() {
        if (payment != null) throw new IllegalStateException("Sale " + saleId + " is already paid.");
    }

    public boolean isPaid()               { return payment != null; }
    public String getSaleId()             { return saleId; }
    public String getCustomerName()       { return customerName; }
    public LocalDateTime getDateTime()    { return dateTime; }
    public Payment getPayment()           { return payment; }
    public List<SaleItem> getItems()      { return Collections.unmodifiableList(items); }
}
