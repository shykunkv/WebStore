package dao.mysql;


import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.Product;
import ents.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MySqlProductDao extends AbstractJDBCDao<Product, Integer> {

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
        return "SELECT id, name, brand, price, quantity, description, category_id, image FROM webstore_dev.products ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO webstore_dev.products (name, brand, price, quantity, description, category_id, image) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webstore_dev.products \n" +
                "SET name = ?, brand  = ?, price = ?, quantity = ?, description = ?, category_id = ?, image = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM webstore_dev.products WHERE id = ?;";
    }

    @Override
    public Product create() throws Exception {
        Product product = new Product();
        return persist(product);
    }


    protected List<Product> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Product> result = new LinkedList<>();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, Product object) throws Exception {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getBrand());
            statement.setDouble(3, object.getPrice());
            statement.setInt(4, object.getQuantity());
            statement.setString(5, object.getDescription());
            statement.setInt(6, object.getCategoryId());
            statement.setString(7, object.getImage());
            statement.setInt(8, object.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void prepareStatementForInsert(PreparedStatement statement, Product object) throws Exception {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getBrand());
            statement.setDouble(3, object.getPrice());
            statement.setInt(4, object.getQuantity());
            statement.setString(5, object.getDescription());
            statement.setInt(6, object.getCategoryId());
            statement.setString(7, object.getImage());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public List<Product> getAllFromCategory(int categoryId) {

        List<Product> result = null;
        String sql = getSelectQuery();
        sql += "WHERE category_id = ?";

        try (Connection connection = parentFactory.getContext()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, categoryId);
                ResultSet rs = statement.executeQuery();
                result = parseResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == null) return Collections.emptyList();
        return result;
    }
}
