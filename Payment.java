package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Payment made for a sale. Calculates change for cash payments. */
public class Payment {
    public enum Method { CASH, CARD, E_WALLET }

    private final Method method;
    private final BigDecimal amountDue;
    private final BigDecimal amountTendered;
    private final BigDecimal change;
    private final LocalDateTime paidAt;

    public Payment(Method method, BigDecimal amountDue, BigDecimal amountTendered) {
        if (amountTendered.compareTo(amountDue) < 0)
            throw new IllegalArgumentException(
                "Insufficient payment. Due: " + amountDue + ", tendered: " + amountTendered);
        this.method = method;
        this.amountDue = amountDue;
        this.amountTendered = amountTendered;
        this.change = amountTendered.subtract(amountDue);
        this.paidAt = LocalDateTime.now();
    }

    public Method getMethod()              { return method; }
    public BigDecimal getAmountDue()       { return amountDue; }
    public BigDecimal getAmountTendered()  { return amountTendered; }
    public BigDecimal getChange()          { return change; }
    public LocalDateTime getPaidAt()       { return paidAt; }
}
