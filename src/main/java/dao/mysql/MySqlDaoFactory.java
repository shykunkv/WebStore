package dao.mysql;

import dao.ConnectionManager;
import dao.DaoFactory;
import dao.GenericDao;
import ents.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private static MySqlDaoFactory instance;
    private ConnectionManager connectionManager;
    private Map<Class, DaoCreator> creators;


    public GenericDao getDao(Class dtoClass) throws Exception {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new Exception("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(this);
    }

    public Connection getContext() throws Exception {
        try {
            return connectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private MySqlDaoFactory() {

        connectionManager = new ConnectionManager();

        creators = new HashMap();

//        creators.put(Order.class, new DaoCreator() {
//            @Override
//            public GenericDao create(DaoFactory factory) {
//                return new MySqlOrderDao(factory);
//            }
//        });
        creators.put(User.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new MySqlUserDao(factory);
            }
        });
//        creators.put(Product.class, new DaoCreator() {
//            @Override
//            public GenericDao create(DaoFactory factory) {
//                return new MySqlProductDao(factory);
//            }
//        });
//        creators.put(OrderPart.class, new DaoCreator() {
//            @Override
//            public GenericDao create(DaoFactory factory) {
//                return new MySqlOrderPartDao(factory);
//            }
//        });
    }

    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }
}
