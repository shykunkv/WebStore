package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCategoryDao;
import dao.mysql.MySqlDaoFactory;
import dao.mysql.MySqlProductDao;
import ents.Category;
import ents.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class CategoryManager {

    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    public List<Product> getAllFromCategory(int categoryId) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);

        return productDao.getAllFromCategory(categoryId);
    }

    public Category getById(int categoryId) throws SQLException {
        MySqlCategoryDao productDao = (MySqlCategoryDao) factory.getDao(Category.class);

        return productDao.getByPK(categoryId);
    }


    public List<Category> getAllCategories() throws SQLException {
        MySqlCategoryDao productDao = (MySqlCategoryDao) factory.getDao(Category.class);

        return productDao.getAll();
    }

    public Category getCategoryByName(String name) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);

        return categoryDao.getByName(name);
    }

    public Category create(String name, String description) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
        Category category = new Category(name, description);

        return categoryDao.persist(category);
    }


    public void update(Category category) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
        categoryDao.update(category);
    }

    public void delete(Category category) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
        categoryDao.delete(category);
    }
}
