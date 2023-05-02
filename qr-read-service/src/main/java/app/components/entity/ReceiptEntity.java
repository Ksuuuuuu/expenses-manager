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

    public ReceiptEntity() {
    }

    public ReceiptEntity(long id, String dateTime, BigDecimal amount, String filePath, long idUser) {
        this.id = id;
        this.dateTime = dateTime;
        this.amount = amount;
        this.filePath = filePath;
        this.idUser = idUser;
    }

    public ReceiptEntity(String dateTime, BigDecimal amount, String filePath, long idUser) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.filePath = filePath;
        this.idUser = idUser;
    }
}
