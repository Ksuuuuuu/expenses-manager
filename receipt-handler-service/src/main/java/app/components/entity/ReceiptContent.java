package app.components.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiptContent {
    private String dateTime;
    private BigDecimal amount;

    public ReceiptContent(String dateTime, BigDecimal amount) {
        this.dateTime = dateTime;
        this.amount = amount;
    }

    public static ReceiptContent fromReceipt(Receipt receipt){
        return new ReceiptContent(receipt.getDateTime().toString(), receipt.getAmount());
    }
}
