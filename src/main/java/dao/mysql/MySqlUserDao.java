package dao.mysql;

import dao.AbstractJDBCDao;
import dao.DaoFactory;
import ents.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


//TODO : web_store static
public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {

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
        return "SELECT id, login, password, mail, role FROM webstore_dev.users ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO webstore_dev.users (login, password, mail, role) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webstore_dev.user \n" +
                "SET login = ?, password  = ?, mail = ?, role = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM webstore_dev.user WHERE id = ?;";
    }

    @Override
    public User create() throws Exception {
        User user = new User();
        return persist(user);
    }


    protected List<User> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistUser student = new PersistUser();
                student.setId(rs.getInt("id"));
                student.setLogin(rs.getString("login"));
                student.setHash(rs.getString("password"));
                student.setMail(rs.getString("mail"));
                student.setRole(User.Role.valueOf(rs.getString("role")));
                result.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws Exception {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getHash());
            statement.setString(3, object.getMail());
            statement.setString(4, object.getRole().toString());
            statement.setInt(7, object.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws Exception {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getHash());
            statement.setString(3, object.getMail());
            statement.setString(4, object.getRole().toString());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public User getByLogin(String login) throws Exception {
        List<User> list;
        String sql = getSelectQuery();
        sql += " WHERE login = ?";
        try (PreparedStatement statement = parentFactory.getContext().prepareStatement(sql)) {
            statement.setString(1, login);
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
