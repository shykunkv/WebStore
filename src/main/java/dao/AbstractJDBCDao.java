package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public abstract class AbstractJDBCDao<T  extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

    protected DaoFactory<Connection> parentFactory;

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws Exception;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws Exception;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws Exception;


    public T persist(T object) throws Exception {
        T persistInstance;
        Connection connection = parentFactory.getContext();
        // Добавляем запись
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        // Получаем только что вставленную запись
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new Exception("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new Exception(e);
        }

        return persistInstance;
    }


    public T getByPK(Integer key) throws Exception {
        List<T> list;
        Connection connection = parentFactory.getContext();
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (list == null || list.size() == 0) {
            throw new Exception("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new Exception("Received more than one record.");
        }
        return list.iterator().next();
    }


    public void update(T object) throws Exception {
        String sql = getUpdateQuery();
        Connection connection = parentFactory.getContext();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public void delete(T object) throws Exception {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = parentFactory.getContext().prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new Exception(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new Exception("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public List<T> getAll() throws Exception {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = parentFactory.getContext().prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }


    public AbstractJDBCDao(DaoFactory<Connection> parentFactory) {
        this.parentFactory = parentFactory;
    }
}