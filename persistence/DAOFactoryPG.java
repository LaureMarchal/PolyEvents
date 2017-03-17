package persistence;

import business.dao.DAOFactory;
import business.dao.EventDAO;
import business.dao.UserDAO;

/**
 * Factory to make PostgreSQL DAO for models
 */
public class DAOFactoryPG extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOPG();
    }

    @Override
    public EventDAO getEventDAO(){ return null; }

}
