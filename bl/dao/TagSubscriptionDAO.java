package bl.dao;

import bl.model.TagSubscription;
import bl.model.User;

public abstract class TagSubscriptionDAO {

    public abstract TagSubscription create(TagSubscription tagSubscription);

    public abstract boolean delete(TagSubscription tagSubscription);

}
