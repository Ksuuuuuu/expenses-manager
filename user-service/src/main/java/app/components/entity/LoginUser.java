package app.components.entity;

import lombok.Data;

@Data
public class LoginUser {
    private String login;
    private String password;

    public LoginUser(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
