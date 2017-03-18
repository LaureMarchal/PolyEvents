package api;

import business.model.Provider;
import business.model.ProviderReview;
import business.model.ProviderSubscription;

/**
 * Entry point to the business behaviour about subscription
 */
public class SubscriptionFacade {

    /**
     * Singleton instance
     */
    private static SubscriptionFacade instance;

    /**
     * Empty constructor for singleton
     */
    private SubscriptionFacade() {
    }

    /**
     * Get the unique instance of the SubscriptionFacade
     *
     * @return An instance of SubscriptionFacade
     */
    public static SubscriptionFacade getInstance() {
        if (instance == null) {
            instance = new SubscriptionFacade();
        }
        return instance;
    }

    public ProviderReview addProviderSubscription(ProviderSubscription providerSubscription){
        return null;
    }

}
