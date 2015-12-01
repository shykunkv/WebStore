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


public class UserManager {
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    public User get(String login) throws SQLException {
        MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);
        User user = userDao.getByLogin(login);

        return user;
    }

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

    public void update(User user) throws SQLException {
        MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);
        userDao.update(user);
    }

}
