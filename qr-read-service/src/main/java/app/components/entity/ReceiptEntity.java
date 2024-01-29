package app.components.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiptEntity {
    private long id;
    private String dateTime;
    private BigDecimal amount;
    private String filePath;
    private long idUser;
    private long checksum;

    public ReceiptEntity() {
    }



    public ReceiptEntity(String dateTime, BigDecimal amount, String filePath, long idUser, long checksum) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.filePath = filePath;
        this.idUser = idUser;
        this.checksum = checksum;

    }
}
