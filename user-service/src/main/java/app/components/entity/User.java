package app.components.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String login;
    private String password;
    private String name;
    private long idCity;

    public User(long id, String login, String password, String name, long idCity) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.idCity = idCity;
    }

    public User(String login, String password, String name, long idCity) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.idCity = idCity;
    }
}
