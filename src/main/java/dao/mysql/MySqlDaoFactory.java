package dao.mysql;

import dao.ConnectionManager;
import dao.DaoFactory;
import dao.GenericDao;
import ents.*;
import org.apache.log4j.Logger;

import javax.enterprise.context.spi.Context;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO objects factory for MySQL databse
 * Implemented as singleton
 */
public class MySqlDaoFactory implements DaoFactory<Connection> {

    /**
     * Instance of factory
     */
    private static MySqlDaoFactory instance;

    /**
     * Connection to database
     */
    private ConnectionManager connectionManager;

    /**
     * Map for DAO implementation for every entity class
     */
    private Map<Class, DaoCreator> creators;


    @Override
    public GenericDao getDao(Class daoClass) throws IllegalArgumentException {
        DaoCreator creator = creators.get(daoClass);
        if (creator == null) {
            throw new IllegalArgumentException("Dao object for " + daoClass + " not found.");
        }
        return creator.create(this);
    }

    /**
     * get connection to database
     * @return
     */
    public Connection getContext() {
        try {
            return connectionManager.getConnection();
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public void putContext(Connection connection) {
        connectionManager.addConnection(connection);
    }

    /**
     * Constructor (fill map)
     */
    private MySqlDaoFactory() {

        connectionManager = new ConnectionManager();

        creators = new HashMap();

        creators.put(User.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new MySqlUserDao(factory);
            }
        });
        creators.put(Category.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new MySqlCategoryDao(factory);
            }
        });
        creators.put(Product.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new MySqlProductDao(factory);
            }
        });
        creators.put(Cart.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new MySqlCartDao(factory);
            }
        });
        creators.put(CartItem.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new MySqlCartItemDao(factory);
            }
        });

    }

    /**
     * Get instance of DAO factory
     * @return instance of this singleton class
     */
    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }
}
