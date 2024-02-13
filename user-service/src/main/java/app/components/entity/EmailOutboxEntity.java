package app.components.entity;

import lombok.Data;

@Data
public class EmailOutboxEntity {

    private long id;
    private long idUser;
    private String email;
    private OperationType operationType;

    public EmailOutboxEntity(long id, String email, OperationType operationType){
        this.idUser = id;
        this.email = email;
        this.operationType = operationType;
    }

    public EmailOutboxEntity(long id, long userId, String email, OperationType operationType){
        this.id = id;
        this.idUser = userId;
        this.email = email;
        this.operationType = operationType;
    }




}


