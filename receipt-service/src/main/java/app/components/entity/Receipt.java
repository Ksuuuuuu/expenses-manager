package app.components.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Receipt {
    private long id;
    private LocalDateTime dateTime;
    private BigDecimal amount;
    private String filePath;
    private long idUser;

    public Receipt(){

    }

    public Receipt(long id, LocalDateTime dateTime, BigDecimal amount, String filePath, long idUser) {
        this.id = id;
        this.dateTime = dateTime;
        this.amount = amount;
        this.filePath = filePath;
        this.idUser = idUser;
    }

    public Receipt(LocalDateTime dateTime, BigDecimal amount, String filePath, long idUser) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.filePath = filePath;
        this.idUser = idUser;
    }
}
