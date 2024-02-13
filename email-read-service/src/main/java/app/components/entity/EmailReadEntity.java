package app.components.entity;

import lombok.Data;

@Data
public class EmailReadEntity {
    private long userId;
    private String email;
    private String token;

    //read from db
    public EmailReadEntity(long userId, String email, String hashPwd){
        this.userId = userId;
        this.email = email;
        this.token = hashPwd;
    }

}
