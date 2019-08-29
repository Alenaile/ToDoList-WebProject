package by.gsu.epamlab.bean;

import java.util.Objects;

public class User {
    private String login;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;


    public User(String login, String password) {
        setLogin(login);
        setPassword(password);
    }

    public User(String login, String password, int userId) {
        this.login = login;
        this.password = password;
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public User getCopy() {
        return new User(login, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return login;
    }
}
