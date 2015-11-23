package dao;
import java.io.Serializable;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /** Создает новую запись и соответствующий ей объект */
    T create() throws Exception;

    /** Создает новую запись, соответствующую объекту object */
    T persist(T object)  throws Exception;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    T getByPK(PK key) throws Exception;

    /** Сохраняет состояние объекта group в базе данных */
    void update(T object) throws Exception;

    /** Удаляет запись об объекте из базы данных */
    void delete(T object) throws Exception;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<T> getAll() throws Exception;
}
