package dao.mysql;


import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MySqlCartItemDao extends AbstractJDBCDao<CartItem, Integer> {

    private class PersistCartItem extends CartItem {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlCartItemDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, product_id, cart_id, quantity  FROM webstore_dev.cart_items ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO webstore_dev.cart_items (product_id, cart_id, quantity) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webstore_dev.cart_items \n" +
                "SET product_id = ?, cart_id  = ?, quantity = ?\n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM webstore_dev.cart_items WHERE id = ?;";
    }

    @Override
    public CartItem create() throws Exception {
        CartItem cartItem = new CartItem();
        return persist(cartItem);
    }


    protected List<CartItem> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<CartItem> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistCartItem cardItem = new PersistCartItem();
                cardItem.setId(rs.getInt("id"));
                cardItem.setProductId(rs.getInt("product_id"));
                cardItem.setOrderId(rs.getInt("cart_id"));
                cardItem.setQuantity(rs.getInt("quantity"));
                result.add(cardItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, CartItem object) throws Exception {
        try {
            statement.setInt(1, object.getProductId());
            statement.setInt(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void prepareStatementForInsert(PreparedStatement statement, CartItem object) throws Exception {
        try {
            statement.setInt(1, object.getProductId());
            statement.setInt(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }



    public List<CartItem> getAllFromCard(int cardId) {

        List<CartItem> result = null;
        String sql = getSelectQuery();
        sql += "WHERE cart_id = ?";

        try (Connection connection = parentFactory.getContext()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, cardId);
                ResultSet rs = statement.executeQuery();
                result = parseResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
