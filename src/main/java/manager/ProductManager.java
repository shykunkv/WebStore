package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCategoryDao;
import dao.mysql.MySqlDaoFactory;
import dao.mysql.MySqlProductDao;
import ents.Product;

import java.sql.Connection;
import java.util.List;


public class ProductManager {

    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();


    public Product getById(int productId) {
        try {
            MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
            return productDao.getByPK(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Product create(String name, String brand, double price, String description, int categoryId, String image) throws Exception {
        try {
            MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
            Product product = new Product(name, brand, price, 20, description, categoryId, image);
            return productDao.persist(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Product product) {
        try {
            MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
            productDao.delete(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        try {
            MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
            productDao.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
