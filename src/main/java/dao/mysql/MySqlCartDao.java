package dao.mysql;

import ents.Cart;

import dao.AbstractJDBCDao;
import dao.DaoFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlCartDao extends AbstractJDBCDao<Cart, Integer> {

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
        return "SELECT id, user_id, created_at, paid  FROM webstore_dev.carts";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO webstore_dev.carts (user_id, created_at, paid) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webstore_dev.carts \n" +
                "SET user_id = ?, created_at  = ?\n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM webstore_dev.carts WHERE id = ?;";
    }

    @Override
    public Cart create() throws Exception {
        Cart cart = new Cart();
        return persist(cart);
    }


    protected List<Cart> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Cart> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistCart card = new PersistCart();
                card.setId(rs.getInt("id"));
                card.setUserId(rs.getInt("user_id"));
                card.setCreatedAt(rs.getDate("created_at"));
                result.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, Cart object) throws Exception {
        try {
            statement.setInt(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void prepareStatementForInsert(PreparedStatement statement, Cart object) throws Exception {
        try {
            statement.setInt(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setBoolean(3, object.isPaid());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Cart getByUserId(int userId) throws Exception {
        List<Cart> list;
        String sql = getSelectQuery();
        sql += " WHERE user_id = ?";
        try (PreparedStatement statement = parentFactory.getContext().prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new Exception("Received more than one record.");
        }
        return list.iterator().next();
    }
}
