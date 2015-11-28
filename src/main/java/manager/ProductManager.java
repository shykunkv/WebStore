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
}
