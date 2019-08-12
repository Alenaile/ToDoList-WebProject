package by.gsu.epamlab.impl;

import by.gsu.epamlab.IUserDAO;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.connection.ConnectionManager;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.ValidationException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlUserImpl implements IUserDAO {
    private static final Logger LOGGER = Logger.getLogger(SqlUserImpl.class.getName());

    private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn = ConnectionManager.getInstance().getConnection();
        return conn;
    }


    @Override
    public User getUser(String login, String password) {
        User user = null;
        try {
            String queryString = Constants.GET_USER_SQL;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setString(Constants.LOGIN_INDEX, login);
            ptmt.setString(Constants.PASSWORD_INDEX, password);

            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                String log = resultSet.getString(ConstantsJSP.KEY_LOGIN);
                String pass = resultSet.getString(ConstantsJSP.KEY_PASSWORD);

                user = new User(log, pass);
            }

            if (user == null) {
                throw new ValidationException(Constants.ERROR_PASSWORD);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new ValidationException(Constants.ERROR_PASSWORD, e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                throw new DaoException(Constants.ERROR_SOURCE, e);
            }
        }
        return user;
    }


    @Override
    public void setUser(String login, String password) {
        try {
            String queryString = Constants.SET_USER_SQL;
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setString(Constants.LOGIN_INDEX, login);
            ptmt.setString(Constants.PASSWORD_INDEX, password);

            ptmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new ValidationException(Constants.ERROR_USER_EXISTS, e);
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                throw new DaoException(Constants.ERROR_SOURCE, e);
            }
        }
    }
}
