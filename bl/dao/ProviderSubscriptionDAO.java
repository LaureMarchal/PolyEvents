package bl.dao;
/**
 * Created by Tom
 */
import bl.model.ProviderSubscription;
import bl.model.User;

public abstract class ProviderSubscriptionDAO {

    public abstract User create(ProviderSubscription providerSubscription);

    public abstract void delete(ProviderSubscription providerSubscription);

}
