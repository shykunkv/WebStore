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


    public List<Category> getAllCategories() throws Exception {
        try {
            MySqlCategoryDao productDao = (MySqlCategoryDao) factory.getDao(Category.class);
            return productDao.getAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Category getCategoryByName(String name) {
        try {
            MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
            return categoryDao.getByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Category create(String name, String description) {
        try {
            MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
            Category category = new Category(name, description);
            return categoryDao.persist(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Category category) {
        try {
            MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
            categoryDao.update(category);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Category category) {
        try {
            MySqlCategoryDao categoryDao = (MySqlCategoryDao) factory.getDao(Category.class);
            categoryDao.delete(category);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
