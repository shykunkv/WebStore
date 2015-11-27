package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCategoryDao;
import dao.mysql.MySqlDaoFactory;
import ents.Category;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Kostya on 26.11.2015.
 */
public class CategoryManager {
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    public List<Category> getAll() throws Exception {
        try {
            MySqlCategoryDao productDao = (MySqlCategoryDao) factory.getDao(Category.class);
            return productDao.getAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
