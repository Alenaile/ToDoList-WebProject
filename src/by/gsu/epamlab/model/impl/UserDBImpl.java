package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsSQL;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.interfaces.IUserDAO;
import by.gsu.epamlab.model.utils.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDBImpl implements IUserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDBImpl.class);
    @Override
    public User getUser(String login, String password) throws DAOException {
        ResultSet rs = null;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(ConstantsSQL.SELECT_USER_WITH_LOGIN)) {

            pst.setString(ConstantsSQL.LOGIN_INDEX, login);
            rs = pst.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString(Constants.PASSWORD);
                if (dbPassword.equals(password)) {
                    return new User(login, password, rs.getInt(ConstantsSQL.USER_ID_COLUMN_INDEX));
                } else {

                    throw new DAOException(Constants.ERROR_PASSWORD);
                }

            } else {
                throw new DAOException(Constants.ERROR_LOGIN);
            }
        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(Constants.ERROR_SOURCE);
        } finally {
            ConnectionManager.closeResultSets(rs);
        }
    }

    @Override
    public void addUser(String login, String password) throws DAOException {
        ResultSet rs = null;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement prStCheck = cn.prepareStatement(ConstantsSQL.SELECT_USER_WITH_LOGIN);
             PreparedStatement prStAddUser = cn.prepareStatement(ConstantsSQL.SET_USER_SQL)) {

            prStCheck.setString(ConstantsSQL.LOGIN_INDEX, login);

            prStAddUser.setString(ConstantsSQL.LOGIN_INDEX, login);
            prStAddUser.setString(ConstantsSQL.PASSWORD_INDEX, password);

            checkLoginAddUser(prStCheck, rs, prStAddUser);

        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(Constants.ERROR_SOURCE);
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
