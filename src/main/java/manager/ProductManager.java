package manager;

import dao.DaoFactory;
import dao.mysql.MySqlCategoryDao;
import dao.mysql.MySqlDaoFactory;
import dao.mysql.MySqlProductDao;
import ents.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Perform all available functions with product objects
 */
public class ProductManager {

    /**
     * Dao objects factory
     */
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    /**
     * Get product from data source with specific id
     * @param productId product id
     * @return product with specific id
     * @throws SQLException
     */
    public Product getById(int productId) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);

        return productDao.getByPK(productId);
    }

    /**
     * Create new record in data source with specific params
     * @param name product name
     * @param brand product brand
     * @param price product price
     * @param description product description
     * @param categoryId id of the category, which refers for
     * @param image
     * @return
     * @throws SQLException
     */
    public Product create(String name, String brand, double price, String description, int categoryId, String image)
            throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
        Product product = new Product(name, brand, price, 20, description, categoryId, image);

        return productDao.persist(product);
    }

    /**
     * Remove record from data source for specific product
     * @param product product to remove
     * @throws SQLException
     */
    public void delete(Product product) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
        productDao.delete(product);
    }

    /**
     * Update record in data source fro specific product
     * @param product product to update
     * @throws SQLException
     */
    public void update(Product product) throws SQLException {
        MySqlProductDao productDao = (MySqlProductDao) factory.getDao(Product.class);
        productDao.update(product);
    }

}
