package persistence;

import bl.dao.ProviderReviewDAO;
import bl.model.Consumer;
import bl.model.Provider;
import bl.model.ProviderReview;

/**
 * PostgreSQL DAO for the provider review model
 */
public class ProviderReviewDAOPG extends ProviderReviewDAO {

    @Override
    public ProviderReview create(Provider provider, Consumer consumer, ProviderReview providerReview) {
        return null;
    }
}
