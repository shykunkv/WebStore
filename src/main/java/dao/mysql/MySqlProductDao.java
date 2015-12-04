package dao.mysql;

import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * DAO implementation for MySQL database and Product entity
 */
public class MySqlProductDao extends AbstractJDBCDao<Product, Integer> {

    /**
     * Resource bundle with MySQL DB queries
     */
    private ResourceBundle dbBundle = ResourceBundle.getBundle("db");

    private class PersistProduct extends Product {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlProductDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("PRODUCTS.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("PRODUCTS.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("PRODUCTS.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("PRODUCTS.DELETE");
    }

    @Override
    public Product create() throws SQLException {
        Product product = new Product();
        return persist(product);
    }

    @Override
    protected List<Product> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Product> result = new LinkedList<>();

        while (rs.next()) {
            PersistProduct product = new PersistProduct();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setBrand(rs.getString("brand"));
            product.setPrice(rs.getDouble("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setDescription(rs.getString("description"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setImage(rs.getString("image"));
            result.add(product);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Product object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getBrand());
        statement.setDouble(3, object.getPrice());
        statement.setInt(4, object.getQuantity());
        statement.setString(5, object.getDescription());
        statement.setInt(6, object.getCategoryId());
        statement.setString(7, object.getImage());
        statement.setInt(8, object.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Product object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getBrand());
        statement.setDouble(3, object.getPrice());
        statement.setInt(4, object.getQuantity());
        statement.setString(5, object.getDescription());
        statement.setInt(6, object.getCategoryId());
        statement.setString(7, object.getImage());
    }

    /**
     * Get all products from category with specific id
     * @param categoryId category id
     * @return list of all products from category
     * @throws SQLException
     */
    public List<Product> getAllFromCategory(int categoryId) throws SQLException {

        List<Product> result;
        String sql = dbBundle.getString("PRODUCTS.FROM_CATEGORY");

        Connection connection = parentFactory.getContext();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, categoryId);
        ResultSet rs = statement.executeQuery();
        result = parseResultSet(rs);

        if (result == null) return Collections.emptyList();
        return result;
    }
}
