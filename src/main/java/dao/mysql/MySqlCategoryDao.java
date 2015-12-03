package dao.mysql;

import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MySqlCategoryDao extends AbstractJDBCDao<Category, Integer> {

    private ResourceBundle dbBundle = ResourceBundle.getBundle("db");


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
        return dbBundle.getString("CATEGORIES.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("CATEGORIES.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("CATEGORIES.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("CATEGORIES.DELETE");
    }

    @Override
    public Category create() throws SQLException {
        Category category = new Category();
        return persist(category);
    }

    @Override
    protected List<Category> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Category> result = new LinkedList<>();

        while (rs.next()) {
            PersistCategory category = new PersistCategory();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("description"));
            result.add(category);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Category object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDescription());
        statement.setInt(3, object.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Category object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDescription());
    }


    public Category getByName(String name) throws SQLException {
        List<Category> list;
        String sql = dbBundle.getString("CATEGORIES.WITH_NAME");
        PreparedStatement statement = parentFactory.getContext().prepareStatement(sql);
        statement.setString(1, name);
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