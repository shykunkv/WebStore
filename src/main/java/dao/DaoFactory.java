package dao;

/**
 * Objects that works with database factory
 * @param <Context>
 */
public interface DaoFactory<Context> {

    public interface DaoCreator {
        public GenericDao create(DaoFactory parentFactory);
    }

    public Context getContext();
    public void putContext(Context context);

    /**
     * Return object to work with database
     * @param dtoClass class of object
     * @return DAO object, according to dtoClass parameter
     * @throws IllegalArgumentException
     */
    public GenericDao getDao(Class dtoClass) throws IllegalArgumentException;
}
