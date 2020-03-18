package ua.lviv;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final Logger LOG = Logger.getLogger(ConnectionUtil.class);

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/iShop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOG.error("Can't connect to DB",e);
            throw new RuntimeException("Can't connect to DB");
        }catch (ClassNotFoundException e) {
            LOG.error("Database connection creation failed!",e);
            throw new RuntimeException(e);
        }
    }
}
