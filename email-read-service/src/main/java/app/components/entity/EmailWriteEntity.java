package app.components.entity;
import lombok.Data;
@Data
public class EmailWriteEntity {
    private String operation;
    private long userId;
    private String email;

    public EmailWriteEntity(String operation, long userId, String email){
        this.operation = operation;
        this.userId = userId;
        this.email = email;
    }
}
