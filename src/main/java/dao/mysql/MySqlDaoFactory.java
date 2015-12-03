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

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private static MySqlDaoFactory instance;
    private ConnectionManager connectionManager;
    private Map<Class, DaoCreator> creators;


    public GenericDao getDao(Class daoClass) throws IllegalArgumentException {
        DaoCreator creator = creators.get(daoClass);
        if (creator == null) {
            throw new IllegalArgumentException("Dao object for " + daoClass + " not found.");
        }
        return creator.create(this);
    }

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

    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }
}
