package dao.mysql;

import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.Category;
import ents.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by Kostya on 26.11.2015.
 */
public class MySqlCategoryDao extends AbstractJDBCDao<Category, Integer> {

    private class PersistCategory extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlCategoryDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, name, description FROM webstore_dev.categories ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO webstore_dev.categories (name, description) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webstore_dev.categories \n" +
                "SET name = ?, description  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM webstore_dev.categories WHERE id = ?;";
    }

    @Override
    public Category create() throws Exception {
        Category category = new Category();
        return persist(category);
    }


    protected List<Category> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<Category> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistCategory category = new PersistCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                result.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, Category object) throws Exception {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Category object) throws Exception {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public Category getByName(String name) throws Exception {
        List<Category> list;
        String sql = getSelectQuery();
        sql += " WHERE name = ?";
        try (PreparedStatement statement = parentFactory.getContext().prepareStatement(sql)) {
            statement.setString(1, name);
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