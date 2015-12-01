package manager;

import dao.DaoFactory;
import dao.mysql.MySqlDaoFactory;
import dao.mysql.MySqlUserDao;
import ents.User;
import utils.HashUtil;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;


@WebServlet("/controller")
public class UserManager {
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    public User get(String login) throws Exception {
        try {
            MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);

            User user = userDao.getByLogin(login);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User login(String login, String password) throws Exception {
        User user = get(login);
        if (user != null && HashUtil.validatePassword(password.toCharArray(), user.getHash())) {
            return user;
        }
        return null;
    }

    public User create(String login, String password, String mail) throws Exception {
        try {
            MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);
            User user = new User(login,HashUtil.createHash(password), mail);
            return userDao.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) throws Exception {
        try {
            MySqlUserDao userDao = (MySqlUserDao) factory.getDao(User.class);
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
