package app.components.entity;

import lombok.Data;

@Data
public class RegistryUser {
    private String login;
    private String password;
    private String name;
    private String city;
    private String email;

    public RegistryUser(String login, String password, String name, String city, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.city = city;
        this.email = email;
    }
}
