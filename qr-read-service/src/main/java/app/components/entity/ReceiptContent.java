package app.components.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiptContent {
    private String dateTime;
    private BigDecimal amount;
    private long checksum;

    public ReceiptContent(String dateTime, BigDecimal amount, long checksum) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.checksum = checksum;
    }
}
