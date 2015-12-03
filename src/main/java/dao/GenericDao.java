package dao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for work with persistance objects
 * @param <T> object type
 * @param <PK> primary key type
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /**
     * Creates new record in database
     * @return created object
     * @throws SQLException
     */
    T create() throws SQLException;

    /**
     * Create new record from object
     * @param object create new record from this object
     * @return created object
     * @throws SQLException
     */
    T persist(T object)  throws SQLException;

    /**
     * Return object with primary key 'key' or null
     * @param key primary key of object
     * @return object with primary key 'key' or null
     * @throws SQLException
     */
    T getByPK(PK key) throws SQLException;

    /**
     * Update object in database
     * @param object to update
     * @throws SQLException
     */
    void update(T object) throws SQLException;

    /**
     * Delete record from database
     * @param object to delete
     * @throws SQLException
     */
    void delete(T object) throws SQLException;

    /**
     * Return list of all items from database
     * @return list of all items
     * @throws SQLException
     */
    List<T> getAll() throws SQLException;
}
