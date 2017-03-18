package persistence;

import business.dao.DAOFactory;
import business.dao.EventDAO;
import business.dao.ProviderReviewDAO;
import business.dao.UserDAO;

/**
 * Factory to make PostgreSQL DAO for models
 */
public class FactoryDAOPG extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOPG();
    }

    @Override
    public EventDAO getEventDAO() {
        return null;
    }

    @Override
    public ProviderReviewDAO getProviderReviewDAO() {
        return null;
    }

}
