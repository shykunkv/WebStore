package dao.mysql;

import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {

    private ResourceBundle dbBundle = ResourceBundle.getBundle("db");


    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlUserDao(DaoFactory parentFactory) {
        super(parentFactory);
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("USERS.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("USERS.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("USERS.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("USERS.DELETE");
    }

    @Override
    public User create() throws SQLException {
        User user = new User();
        return persist(user);
    }


    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<User> result = new LinkedList<>();

        while (rs.next()) {
            PersistUser student = new PersistUser();
            student.setId(rs.getInt("id"));
            student.setLogin(rs.getString("login"));
            student.setHash(rs.getString("password"));
            student.setMail(rs.getString("mail"));
            student.setRole(User.Role.valueOf(rs.getString("role")));
            result.add(student);
        }

        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getHash());
        statement.setString(3, object.getMail());
        statement.setString(4, object.getRole().toString());
        statement.setInt(5, object.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getHash());
        statement.setString(3, object.getMail());
        statement.setString(4, object.getRole().toString());
    }

    public User getByLogin(String login) throws SQLException {
        List<User> list;
        String sql = dbBundle.getString("USERS.WITH_LOGIN");
        PreparedStatement statement = parentFactory.getContext().prepareStatement(sql);
        statement.setString(1, login);
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
