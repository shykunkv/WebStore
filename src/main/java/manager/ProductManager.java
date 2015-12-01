package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCategoryDao;
import dao.mysql.MySqlDaoFactory;
import dao.mysql.MySqlProductDao;
import ents.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ProductManager {

    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();


    public Product getById(int productId) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);

        return productDao.getByPK(productId);
    }


    public Product create(String name, String brand, double price, String description, int categoryId, String image)
            throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
        Product product = new Product(name, brand, price, 20, description, categoryId, image);

        return productDao.persist(product);
    }

    public void delete(Product product) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
        productDao.delete(product);
    }

    public void update(Product product) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
        productDao.update(product);
    }

}
