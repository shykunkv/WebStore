package dao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /** Создает новую запись и соответствующий ей объект */
    T create() throws SQLException;

    /** Создает новую запись, соответствующую объекту object */
    T persist(T object)  throws SQLException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    T getByPK(PK key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    void update(T object) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    void delete(T object) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<T> getAll() throws SQLException;
}
