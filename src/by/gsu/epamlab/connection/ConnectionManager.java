package by.gsu.epamlab.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";


    private static ConnectionManager connectionManager = null;

    private ConnectionManager() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        return conn;
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }
}
