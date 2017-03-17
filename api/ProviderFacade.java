package api;

import business.model.ProviderReview;
import business.model.Provider;

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

    public ProviderReview reviewProvider(Provider provider){
        return null;
    }

}
