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

/**
 * Perform all available functions with category objects
 */
public class CategoryManager {

    /**
     * Dao object factory
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Get all products from data source, that refers to some specific category
     * @param categoryId category id
     * @return list all products from specific category
     * @throws SQLException
     */
    public List<Product> getAllFromCategory(int categoryId) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);

        return productDao.getAllFromCategory(categoryId);
    }

    /**
     * Get category from data source with specific id
     * @param categoryId category id
     * @return category with this id
     * @throws SQLException
     */
    public Category getById(int categoryId) throws SQLException {
        MySqlCategoryDao productDao = (MySqlCategoryDao) factory.getDao(Category.class);

        return productDao.getByPK(categoryId);
    }

    /**
     * Get all categories from data source
     * @return list of all categories from data source
     * @throws SQLException
     */
    public List<Category> getAllCategories() throws SQLException {
        MySqlCategoryDao productDao = (MySqlCategoryDao) factory.getDao(Category.class);

        return productDao.getAll();
    }

    /**
     * Get category from data source with specific name
     * @param name category name
     * @return category with this name
     * @throws SQLException
     */
    public Category getCategoryByName(String name) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);

        return categoryDao.getByName(name);
    }

    /**
     * Create new record in data source with specific params
     * @param name category name
     * @param description category description
     * @return created category object
     * @throws SQLException
     */
    public Category create(String name, String description) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
        Category category = new Category(name, description);

        return categoryDao.persist(category);
    }

    /**
     * Update record in data source for specific category
     * @param category category to update
     * @throws SQLException
     */
    public void update(Category category) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
        categoryDao.update(category);
    }

    /**
     * Remove record from data source for specific category
     * @param category categpry to remove
     * @throws SQLException
     */
    public void delete(Category category) throws SQLException {
        MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
        categoryDao.delete(category);
    }
}
