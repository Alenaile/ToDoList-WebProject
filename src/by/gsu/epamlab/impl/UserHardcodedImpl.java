package by.gsu.epamlab.impl;

import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;

import java.util.HashSet;
import java.util.Set;

public class UserHardcodedImpl implements IUserDAO {

    static Set<User> users = new HashSet<>();

    @Override
    public User getUser(String login, String password) throws DAOException {
        User user = new User(login, password);

        if (users.contains(user)) {
            return user;
        } else {
            throw new DAOException(Constants.ERROR_PASSWORD);
        }
    }

    @Override
    public void addUser(String login, String password) throws DAOException {
        synchronized (login) {
            for (User user: users) {
                if (user.getLogin().equals(login)) {
                    throw new DAOException(Constants.ERROR_USER_EXISTS);
                }
            }
            users.add(new User(login, password));
        }
    }

}
