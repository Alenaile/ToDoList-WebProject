package by.gsu.epamlab;

import by.gsu.epamlab.bean.User;

public interface IUserDAO {
    User getUser(String login, String password);

    void setUser(String login, String password);
}
