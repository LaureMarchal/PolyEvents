package persistence;

import bl.dao.*;

/**
 * Factory to make PostgreSQL DAO for models
 */
public class FactoryDAOPG extends DAOFactory {

    @Override
    public EventDAO createEventDAO() {
        return new EventDAOPG();
    }

    @Override
    public EventReviewDAO createEventReviewDAO() {
        return new EventReviewDAOPG();
    }

    @Override
    public MessageDAO createMessageDAO() {
        return new MessageDAOPG();
    }

    @Override
    public NotificationDAO createNotificationDAO() {
        return new NotificationDAOPG();
    }

    @Override
    public ProviderReviewDAO createProviderReviewDAO() {
        return new ProviderReviewDAOPG();
    }

    @Override
    public ProviderSubscriptionDAO createProviderSubscriptionDAO() {
        return new ProviderSubscriptionDAOPG();
    }

    @Override
    public RegistrationDAO createRegistrationDAO() {
        return new RegistrationDAOPG();
    }

    @Override
    public TagSubscriptionDAO createTagSubscriptionDAO() {
        return new TagSubscriptionDAOPG();
    }

    @Override
    public UserDAO createUserDAO() {
        return new UserDAOPG();
    }
}
