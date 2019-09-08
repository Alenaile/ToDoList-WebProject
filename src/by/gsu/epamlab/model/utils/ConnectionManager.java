package by.gsu.epamlab.model.utils;

import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.exceptions.DAOException;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

    private static DataSource dataSource;
    private static final String CONTEXT = "java:comp/env/jdbc/webProject";


    static {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup(CONTEXT);

        } catch (NamingException e) {
            LOGGER.error(e.toString(), e);
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(Constants.ERROR_CANNOT_CREATE_CONN);
        }
    }


    public static void closeResultSets(ResultSet... resultSets) throws DAOException {
        for (int i = 0; i < resultSets.length; i++) {
            if (resultSets[i] != null) {
                try {
                    resultSets[i].close();
                } catch (SQLException e) {
                    LOGGER.error(e.toString(), e);
                    throw new DAOException(Constants.ERROR_SOURCE, e);
                }
            }
        }

    }

    public static void closeStatements(Statement... statements) throws DAOException {
        for (int i = 0; i < statements.length; i++) {
            if (statements[i] != null) {
                try {
                    statements[i].close();
                } catch (SQLException e) {
                    LOGGER.error(e.toString(), e);
                    throw new DAOException(Constants.ERROR_SOURCE, e);
                }
            }
        }

    }


    public static void closeConnection(Connection connection) throws DAOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.toString(), e);
                throw new DAOException(Constants.ERROR_SOURCE, e);
            }
        }
    }


}
