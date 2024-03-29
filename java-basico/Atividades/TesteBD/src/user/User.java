package user;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String login;
    private String email;
    private String password;
    private List<String> posts;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
