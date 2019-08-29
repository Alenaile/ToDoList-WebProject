package by.gsu.epamlab.impl;

import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.connection.ConnectionManager;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBImpl implements IUserDAO {

    @Override
    public User getUser(String login, String password) throws DAOException {
        User user;
        ResultSet rs = null;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(Constants.SELECT_USER_WITH_LOGIN)) {

            pst.setString(Constants.LOGIN_INDEX, login);
            rs = pst.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString(ConstantsJSP.KEY_PASSWORD);
                if (dbPassword.equals(password)) {
                    return new User(login, password, rs.getInt(Constants.ID_INDEX));
                } else {
                    throw new DAOException(Constants.ERROR_PASSWORD);
                }

            } else {
                throw new DAOException(Constants.ERROR_LOGIN);
            }
        } catch (SQLException e) {
            throw new DAOException(Constants.ERROR_SOURCE);
        } finally {
            ConnectionManager.closeResultSets(rs);
        }
    }

    @Override
    public void addUser(String login, String password) throws DAOException {
        ResultSet rs = null;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement prStCheck = cn.prepareStatement(Constants.SELECT_USER_WITH_LOGIN);
             PreparedStatement prStAddUser = cn.prepareStatement(Constants.SET_USER_SQL)) {

            prStCheck.setString(Constants.LOGIN_INDEX, login);

            prStAddUser.setString(Constants.LOGIN_INDEX, login);
            prStAddUser.setString(Constants.PASSWORD_INDEX, password);

            checkLoginAddUser(prStCheck, rs, prStAddUser);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionManager.closeResultSets(rs);
        }
    }


    private synchronized void checkLoginAddUser(PreparedStatement prStCheck, ResultSet rs, PreparedStatement prStAdd) throws SQLException, DAOException {
        rs = prStCheck.executeQuery();
        if (rs.next()) {
            throw new DAOException(Constants.ERROR_USER_EXISTS);
        }
        prStAdd.execute();
    }


}
