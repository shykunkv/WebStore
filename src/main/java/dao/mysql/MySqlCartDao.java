package dao.mysql;

import ents.Cart;

import dao.AbstractJDBCDao;
import dao.DaoFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MySqlCartDao extends AbstractJDBCDao<Cart, Integer> {

    private ResourceBundle dbBundle = ResourceBundle.getBundle("db");

    private class PersistCart extends Cart {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlCartDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("CARTS.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("CARTS.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("CARTS.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("CARTS.DELETE");
    }

    @Override
    public Cart create() throws SQLException {
        Cart cart = new Cart();
        return persist(cart);
    }

    @Override
    protected List<Cart> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Cart> result = new LinkedList<>();

        while (rs.next()) {
            PersistCart card = new PersistCart();
            card.setId(rs.getInt("id"));
            card.setUserId(rs.getInt("user_id"));
            card.setCreatedAt(rs.getDate("created_at"));
            result.add(card);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Cart object) throws SQLException {
        statement.setInt(1, object.getUserId());
        statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
        statement.setInt(3, object.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Cart object) throws SQLException {
        statement.setInt(1, object.getUserId());
        statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
    }


    public Cart getByUserId(int userId) throws SQLException {
        List<Cart> list;
        String sql = dbBundle.getString("CARTS.FROM_USER_ID");
        PreparedStatement statement = parentFactory.getContext().prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();
        list = parseResultSet(rs);


        if (list == null || list.size() == 0) {
            return null;
        }

        if (list.size() > 1) {
            throw new SQLException("Received more than one record.");
        }

        return list.iterator().next();
    }
}
