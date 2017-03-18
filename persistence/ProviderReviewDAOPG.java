package persistence;

import business.dao.ProviderReviewDAO;
import business.model.Provider;
import business.model.ProviderReview;
import business.model.User;

/**
 * PostgreSQL DAO for the provider review model
 */
public class ProviderReviewDAOPG implements ProviderReviewDAO {

    @Override
    public ProviderReview create(Provider provider, User user, ProviderReview providerReview) {
        return null;
    }
}
