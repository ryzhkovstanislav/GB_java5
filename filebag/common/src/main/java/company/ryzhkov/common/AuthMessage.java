package company.ryzhkov.common;

import java.io.Serializable;

public class AuthMessage extends Message implements Serializable {
    private String username;
    private String password;

    public AuthMessage(String login, String password) {
        this.header = "AUTHENTICATION";
        this.username = login;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
