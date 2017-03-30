package bl.dao;

import bl.model.Consumer;
import bl.model.Provider;
import bl.model.ProviderReview;
import java.util.List;

/**
 * Abstract DAO for the provider review model
 */
public abstract class ProviderReviewDAO {

    public abstract ProviderReview create(Provider provider, Consumer consumer, ProviderReview providerReview);

    public abstract List<ProviderReview> getAllReviewsForProvider(Provider provider);

    public abstract boolean delete(Provider provider, Consumer consumer, ProviderReview providerReview);
}
