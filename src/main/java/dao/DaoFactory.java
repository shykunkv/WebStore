package dao;

/** Фабрика объектов для работы с базой данных */
public interface DaoFactory<Context> {

    public interface DaoCreator {
        public GenericDao create(DaoFactory parentFactory);
    }

    public Context getContext();

    /** Возвращает объект для управления персистентным состоянием объекта */
    public GenericDao getDao(Class dtoClass) throws IllegalArgumentException;
}
