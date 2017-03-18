package bl.dao;

import persistence.FactoryDAOPG;

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
            instance = new FactoryDAOPG();
        }
        return instance;
    }

    public abstract EventDAO createEventDAO();

    public abstract EventReviewDAO createEventReviewDAO();

    public abstract MessageDAO createMessageDAO();

    public abstract NotificationDAO createNotificationDAO();

    public abstract ProviderReviewDAO createProviderReviewDAO();

    public abstract ProviderSubscriptionDAO createProviderSubscriptionDAO();

    public abstract RegistrationDAO createRegistrationDAO();

    public abstract TagSubscriptionDAO createTagSubscriptionDAO();

    public abstract UserDAO createUserDAO();

}
