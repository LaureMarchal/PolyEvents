package business.dao;

import business.model.Provider;
import business.model.ProviderReview;
import business.model.User;

/**
 * Abstract DAO for the provider review model
 */
public interface ProviderReviewDAO {

    ProviderReview create(Provider provider, User user, ProviderReview providerReview);

}
