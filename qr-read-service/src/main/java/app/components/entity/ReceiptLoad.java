package app.components.entity;

import lombok.Data;

@Data
public class ReceiptLoad {
    long idUser;
    String filePath;

    public ReceiptLoad(long idUser, String filePath) {
        this.idUser = idUser;
        this.filePath = filePath;
    }


}
