package business.dao;

public abstract class DAOFactory {

    public abstract UserDAO get(String daoToCreate);

}
