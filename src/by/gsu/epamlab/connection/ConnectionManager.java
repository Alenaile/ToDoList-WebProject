package by.gsu.epamlab.connection;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());

    private static DataSource dataSource;
    private static final String CONTEXT = "java:comp/env/jdbc/webProject";


    static {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup(CONTEXT);

        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() throws DAOException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException(Constants.ERROR_CANNOT_CREATE_CONN);
        }
    }



    public static void closeResultSets(ResultSet... resultSets) {
        for (int i = 0; i < resultSets.length; i++) {
            if (resultSets[i] != null) {
                try {
                    resultSets[i].close();
                } catch (SQLException e) {
                    System.err.println(Constants.ERROR_SOURCE + e.getMessage());
                    LOGGER.log(Level.SEVERE, e.toString(), e);

                }
            }
        }

    }

    public static void closeStatements(Statement... statements) {
        for (int i = 0; i < statements.length; i++) {
            if (statements[i] != null) {
                try {
                    statements[i].close();
                } catch (SQLException e) {
                    System.err.println(Constants.ERROR_SOURCE + e.getMessage());
                    // LOGGER.log(Level.SEVERE, e.toString(), e);
                }
            }
        }

    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(Constants.ERROR_SOURCE + e.getMessage());
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
    }


}
