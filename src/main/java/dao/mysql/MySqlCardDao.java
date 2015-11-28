package dao.mysql;

import ents.Card;

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

public class MySqlCardDao extends AbstractJDBCDao<Card, Integer> {

    private class PersistCard extends Card {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlCardDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, user_id, created_at, paid  FROM webstore_dev.cards ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO webstore_dev.cards (user_id, created_at, paid) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webstore_dev.cards \n" +
                "SET user_id = ?, created_at  = ?, paid = ?\n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM webstore_dev.cards WHERE id = ?;";
    }

    @Override
    public Card create() throws Exception {
        Card card = new Card();
        return persist(card);
    }


    protected List<Card> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Card> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistCard card = new PersistCard();
                card.setId(rs.getInt("id"));
                card.setUserId(rs.getInt("user_id"));
                card.setCreatedAt(rs.getDate("created_at"));
                card.setPaid(rs.getBoolean("paid"));
                result.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, Card object) throws Exception {
        try {
            statement.setInt(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setBoolean(3, object.isPaid());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void prepareStatementForInsert(PreparedStatement statement, Card object) throws Exception {
        try {
            statement.setInt(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setBoolean(3, object.isPaid());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Card getByUserId(int userId) throws Exception {
        List<Card> list;
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
