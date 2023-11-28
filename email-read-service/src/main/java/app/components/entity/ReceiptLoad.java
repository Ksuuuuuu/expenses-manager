package app.components.entity;

import lombok.Data;

@Data
public class ReceiptLoad {
    long idUser;
    String filePath;
    public ReceiptLoad(String path, Long idUser) {
        this.filePath = path;
        this.idUser = idUser;
    }
}
