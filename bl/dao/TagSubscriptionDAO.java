package bl.dao;

import bl.model.TagSubscription;
import bl.model.User;

public abstract class TagSubscriptionDAO {

    public abstract User create(TagSubscription tagSubscription);

    public abstract void delete(TagSubscription tagSubscription);

}
