package persistence;

import business.dao.DAOFactory;
import business.dao.UserDAO;

public class DAOFactoryPG extends DAOFactory {


    @Override
    public UserDAO getUserDAO() {
        return new UserDAOPG();
    }
}
