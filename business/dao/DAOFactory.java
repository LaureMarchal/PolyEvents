package business.dao;

import business.model.ProviderReview;
import persistence.DAOFactoryPG;

/**
 * Abstract factory to make DAO
 */
public abstract class DAOFactory {

    /**
     * Singleton instance
     */
    private static DAOFactory instance;

    /**
     * Empty constructor for singleton
     */
    protected DAOFactory() {
    }

    /**
     * Get the unique instance of DAO Factory
     *
     * @return An instance of DAOFactory
     */
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactoryPG();
        }
        return instance;
    }

    /**
     * Get the unique User DAO
     *
     * @return An instance of UserDAO
     */
    public abstract UserDAO getUserDAO();

    public abstract EventDAO getEventDAO();

    public abstract ProviderReviewDAO getProviderReviewDAO();

}
