package by.gsu.epamlab.model.interfaces;

import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.bean.User;

public interface IUserDAO {

    User getUser(String login, String password) throws DAOException;

    void addUser(String login, String password) throws DAOException;
}
