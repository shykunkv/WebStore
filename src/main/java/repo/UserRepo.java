package repo;

import dao.ConnectionManager;
import common.RepoConstants;
import ents.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRepo {

    public static List<User> getAllUsers(ConnectionManager connectionManager) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "select * from " + RepoConstants.USER_TABLE_NAME);
            ResultSet resultSet = statement.executeQuery();
            List<User> allUsers = new ArrayList<User>();
            while (resultSet.next()) {
                allUsers.add(buildUser(resultSet));
            }
            return allUsers;
        } catch (InterruptedException e) {
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            connectionManager.addConnection(connection);
        }
    }

    public static User getUser(ConnectionManager connectionManager, String paramName, String paramValue) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "select * from " + RepoConstants.USER_TABLE_NAME + " where " + paramName + " = ?");
            statement.setString(1, paramValue);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return buildUser(resultSet);
            }
            return null;
        } catch (InterruptedException e) {
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            connectionManager.addConnection(connection);
        }
    }

    public static boolean addUser(ConnectionManager connectionManager, User user) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            StringBuilder statementTemplate = new StringBuilder(
                    "insert into " + RepoConstants.USER_TABLE_NAME + " values (default");
            for (int index = 0; index < RepoConstants.USER_COLUMN_NUMBER - 1; index++) {
                statementTemplate.append(", ?");
            }
            statementTemplate.append(")");

            PreparedStatement statement = connection.prepareStatement(statementTemplate.toString());
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getHash());
            statement.setString(3, user.getMail());
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return true;
        } catch (InterruptedException e) {
            return false;
        } catch (SQLException e) {
            return false;
        } finally {
            connectionManager.addConnection(connection);
        }
    }

    private static User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getInt(1), /* id */
                resultSet.getString(2), /* login */
                resultSet.getString(3), /* hash */
                resultSet.getString(4) /* mail */);
        return user;
    }
}
