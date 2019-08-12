package by.gsu.epamlab.impl;

import by.gsu.epamlab.IUserDAO;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.ValidationException;

import java.util.HashSet;
import java.util.Set;

public class HardcodedUserImpl implements IUserDAO {

    static Set<User> entryUsers = new HashSet<>();

    @Override
    public User getUser(String login, String password) {
        User user = new User(login, password);

        if (entryUsers.contains(user)) {
            return user;
        } else {
            throw new ValidationException(Constants.ERROR_PASSWORD);
        }
    }

    @Override
    public void setUser(String login, String password) {
        for (User user: entryUsers) {
            if (user.getLogin().equals(login)) {
                throw new ValidationException(Constants.ERROR_USER_EXISTS);
            }
        }
        entryUsers.add(new User(login, password));
    }
}
