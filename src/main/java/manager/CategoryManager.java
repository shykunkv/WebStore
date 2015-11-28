package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCategoryDao;
import dao.mysql.MySqlDaoFactory;
import dao.mysql.MySqlProductDao;
import ents.Category;
import ents.Product;

import java.sql.Connection;
import java.util.List;


public class CategoryManager {

    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    public List<Product> getAllFromCategory(int categoryId) {
        try {
            MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
            return productDao.getAllFromCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Category getById(int categoryId) {
        try {
            MySqlCategoryDao productDao = (MySqlCategoryDao) factory.getDao(Category.class);
            return productDao.getByPK(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
