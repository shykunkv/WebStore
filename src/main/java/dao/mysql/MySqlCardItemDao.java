package dao.mysql;


import ents.Card;

import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.CardItem;
import ents.Product;
import ents.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MySqlCardItemDao extends AbstractJDBCDao<CardItem, Integer> {

    private class PersistCardItem extends CardItem {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlCardItemDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, product_id, order_id, quantity  FROM webstore_dev.card_items ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO webstore_dev.card_items (product_id, order_id, quantity) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webstore_dev.card_items \n" +
                "SET product_id = ?, order_at  = ?, quantity = ?\n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM webstore_dev.card_items WHERE id = ?;";
    }

    @Override
    public CardItem create() throws Exception {
        CardItem cardItem = new CardItem();
        return persist(cardItem);
    }


    protected List<CardItem> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<CardItem> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistCardItem cardItem = new PersistCardItem();
                cardItem.setId(rs.getInt("id"));
                cardItem.setProductId(rs.getInt("product_id"));
                cardItem.setOrderId(rs.getInt("order_id"));
                cardItem.setQuantity(rs.getInt("quantity"));
                result.add(cardItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, CardItem object) throws Exception {
        try {
            statement.setInt(1, object.getProductId());
            statement.setInt(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void prepareStatementForInsert(PreparedStatement statement, CardItem object) throws Exception {
        try {
            statement.setInt(1, object.getProductId());
            statement.setInt(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public List<CardItem> getAllFromCard(int cardId) {

        List<CardItem> result = null;
        String sql = getSelectQuery();
        sql += "WHERE card_id = ?";

        try (Connection connection = parentFactory.getContext()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, cardId);
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
