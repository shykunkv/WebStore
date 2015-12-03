package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * Connection pool to database
 * Implemented as singleton pattern
 */
public class ConnectionManager {

    /**
     * Database URL
     */
    private static String URL = "jdbc:mysql://localhost:3306/webstore_dev";

    /**
     * Driver name for connection
     */
    private static String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * Database user name
     */
    private static String USER = "root";

    /**
     * Database user password
     */
    private static String PASSWORD = "password12345";

    /**
     * Number of connection to create
     */
    private static int CONNECTION_NUM = 10;

    /**
     * Instance to get connection
     */
    private static ConnectionManager instance;

    /**
     * Queue of connections
     */
    private BlockingQueue<Connection> connections;

    public ConnectionManager() {
        connections = new ArrayBlockingQueue<Connection>(CONNECTION_NUM);
    }

    /**
     * get instance of connection pool
     * @return instance of the class
     */
    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    /**
     * Get connection to database
     * @return connection to database
     * @throws SQLException
     * @throws InterruptedException
     */
    public synchronized Connection getConnection() throws SQLException, InterruptedException {
        if (connections.isEmpty()) {
            connections.put(createInstance());
        }
        return connections.poll();
    }

    /**
     * Create instance of connection pool
     * @return instance of the class
     * @throws SQLException
     */
    public Connection createInstance() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    /**
     * Return connection to the connection pool
     * @param connection
     */
    public void addConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }
    }
}