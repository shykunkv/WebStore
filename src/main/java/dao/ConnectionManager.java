package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {

    private static String URL = "jdbc:mysql://localhost:3306/webstore_dev";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USER = "root";
    private static String PASSWORD = "password12345";
    private static int CONNECTION_NUM = 10;

    private static ConnectionManager instance;
    private BlockingQueue<Connection> connections;

    public ConnectionManager() {
        connections = new ArrayBlockingQueue<Connection>(CONNECTION_NUM);
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException, InterruptedException {
        if (connections.isEmpty()) {
            connections.put(createInstance());
        }
        return connections.poll();
    }

    public Connection createInstance() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {}
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public void addConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connections.put(connection);
        } catch (InterruptedException e) {}
    }
}