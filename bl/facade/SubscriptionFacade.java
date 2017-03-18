package bl.facade;

import bl.model.ProviderSubscription;
import bl.model.TagSubscription;

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

    public ProviderSubscription addProviderSubscription(ProviderSubscription providerSubscription) {
        return null;
    }

    public void removeProviderSubscription(ProviderSubscription providerSubscription) {
    }

    public TagSubscription addTagSubscription(TagSubscription tagSubscription) {
        return null;
    }

    public void removeTagSubscription(TagSubscription tagSubscription) {
    }

}
