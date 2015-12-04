package manager;

import dao.DaoFactory;
import dao.mysql.MySqlDaoFactory;
import dao.mysql.MySqlUserDao;
import ents.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Perform all available functions with user objects
 */
public class UserManager {

    /**
     * Dao objects factory
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Get user from data source with specific login
     * @param login user login
     * @return user with this login
     * @throws SQLException
     */
    public User get(String login) throws SQLException {
        MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);
        User user = userDao.getByLogin(login);

        return user;
    }

    /**
     * Verifies the existence of pair (login, password) in data source
     * @param login user login
     * @param password user password
     * @return user with this login and password or null such pair doesn't exist in data source
     * @throws SQLException
     */
    public User login(String login, String password) throws SQLException {
        User user = get(login);
        try {
            if (user != null && HashUtil.validatePassword(password.toCharArray(), user.getHash())) {
                return user;
            }
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create new record in data source with specific params
     * @param login user login
     * @param password user password
     * @param mail user email address
     * @return created user object
     * @throws SQLException
     */
    public User create(String login, String password, String mail) throws SQLException {
        MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);
        User user = null;
        try {
            user = new User(login, HashUtil.createHash(password), mail);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }
        return userDao.persist(user);
    }

    /**
     * Update record in data source for specific user
     * @param user user to update
     * @throws SQLException
     */
    public void update(User user) throws SQLException {
        MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);
        userDao.update(user);
    }

}
