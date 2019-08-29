package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.exceptions.DAOException;

public interface IUserDAO {

    User getUser(String login, String password) throws DAOException;

    void addUser(String login, String password) throws DAOException;
}
