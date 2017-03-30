package bl.facade;

import bl.dao.DAOFactory;
import bl.model.Consumer;
import bl.model.Provider;
import bl.model.ProviderReview;

/**
 * Entry point to the business behaviour
 */
public class ProviderFacade {

    /**
     * Singleton instance
     */
    private static ProviderFacade instance;

    /**
     * Empty constructor for singleton
     */
    private ProviderFacade() {
    }

    /**
     * Get the unique instance of the ProviderFacade
     *
     * @return An instance of ProviderFacade
     */
    public static ProviderFacade getInstance() {
        if (instance == null) {
            instance = new ProviderFacade();
        }
        return instance;
    }

    public ProviderReview postReview(Provider provider, Consumer consumer, ProviderReview providerReview) {
        return DAOFactory.getInstance().createProviderReviewDAO().create(provider, consumer, providerReview);
    }

    public ProviderReview getReviewByProviderAndConsumer(Provider provider, Consumer consumer) {
        return DAOFactory.getInstance().createProviderReviewDAO().getReviewForProviderAndConsumer(provider, consumer);
    }

    public boolean deleteReview(Provider provider, Consumer consumer) {
        return DAOFactory.getInstance().createProviderReviewDAO().delete(provider, consumer);
    }
}
