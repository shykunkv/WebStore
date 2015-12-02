package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstarct class that perform basic CRUD operations with JDBC.
 *
 * @param <T>  persistance object type
 * @param <PK> primary key type
 */
public abstract class AbstractJDBCDao<T  extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

    /**
     * Connection to database
     */
    protected DaoFactory<Connection> parentFactory;

    /**
     * Return select query
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Return sql query to insert new item
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Return sql query to update item
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Return sql query for delete some row
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Parse result set and return list of items in this set
     *
     * @param rs Result set to parse
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    /**
     * Set arguments for insert query
     *
     * @param statement insert statement without parameters
     * @param object from which we take parameters
     * @throws SQLException
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    /**
     * Set arguments for update query
     *
     * @param statement insert statement without parameters
     * @param object from which we take parameters
     * @throws SQLException
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;


    @Override
    public T persist(T object) throws SQLException {
        T persistInstance;
        Connection connection = parentFactory.getContext();
        // Добавляем запись
        String sql = getCreateQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        prepareStatementForInsert(statement, object);
        int count = statement.executeUpdate();
        if (count != 1) {
            throw new SQLException("On persist modify more then 1 record: " + count);
        }

        // Получаем только что вставленную запись
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<T> list = parseResultSet(rs);
        if ((list == null) || (list.size() != 1)) {
            throw new SQLException("Exception on findByPK new persist data.");
        }
        persistInstance = list.iterator().next();

        return persistInstance;
    }

    @Override
    public T getByPK(Integer key) throws SQLException {
        List<T> list;
        Connection connection = parentFactory.getContext();
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, key);
        ResultSet rs = statement.executeQuery();
        list = parseResultSet(rs);

        if (list == null || list.size() == 0) {
            throw new SQLException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new SQLException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws SQLException {
        String sql = getUpdateQuery();
        Connection connection = parentFactory.getContext();
        PreparedStatement statement = connection.prepareStatement(sql);
        prepareStatementForUpdate(statement, object);
        int count = statement.executeUpdate();
        if (count != 1) {
            throw new SQLException("On update modify more then 1 record: " + count);
        }

    }

    @Override
    public void delete(T object) throws SQLException {
        String sql = getDeleteQuery();
        PreparedStatement statement = parentFactory.getContext().prepareStatement(sql);

        statement.setObject(1, object.getId());

        int count = statement.executeUpdate();
        if (count != 1) {
            throw new SQLException("On delete modify more then 1 record: " + count);
        }
        statement.close();

    }

    @Override
    public List<T> getAll() throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        PreparedStatement statement = parentFactory.getContext().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        list = parseResultSet(rs);

        return list;
    }

    /**
     * Constructor
     */
    public AbstractJDBCDao(DaoFactory < Connection > parentFactory) {
        this.parentFactory = parentFactory;
    }
}