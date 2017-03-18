package api;

import business.model.Provider;
import business.model.ProviderReview;
import business.model.User;

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

    public ProviderReview postReview(Provider provider, User user, ProviderReview providerReview) {
        return null;
    }

}
