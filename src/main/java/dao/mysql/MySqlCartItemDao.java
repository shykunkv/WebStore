package dao.mysql;

import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MySqlCartItemDao extends AbstractJDBCDao<CartItem, Integer> {

    private ResourceBundle dbBundle = ResourceBundle.getBundle("db");

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
        return dbBundle.getString("CART_ITEMS.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("CART_ITEMS.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("CART_ITEMS.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("CART_ITEMS.DELETE");
    }

    @Override
    public CartItem create() throws SQLException {
        CartItem cartItem = new CartItem();
        return persist(cartItem);
    }


    protected List<CartItem> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<CartItem> result = new LinkedList<>();

        while (rs.next()) {
            PersistCartItem cardItem = new PersistCartItem();
            cardItem.setId(rs.getInt("id"));
            cardItem.setProductId(rs.getInt("product_id"));
            cardItem.setOrderId(rs.getInt("cart_id"));
            cardItem.setQuantity(rs.getInt("quantity"));
            result.add(cardItem);
        }

        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, CartItem object) throws SQLException {
        statement.setInt(1, object.getProductId());
        statement.setInt(2, object.getOrderId());
        statement.setInt(3, object.getQuantity());
        statement.setInt(4, object.getId());
    }

    protected void prepareStatementForInsert(PreparedStatement statement, CartItem object) throws SQLException {
        statement.setInt(1, object.getProductId());
        statement.setInt(2, object.getOrderId());
        statement.setInt(3, object.getQuantity());
    }



    public List<CartItem> getAllFromCard(int cardId) throws SQLException {

        List<CartItem> result;
        String sql = dbBundle.getString("CART_ITEMS.FROM_CART");

        Connection connection = parentFactory.getContext();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, cardId);
        ResultSet rs = statement.executeQuery();
        result = parseResultSet(rs);

        return result;
    }
}
