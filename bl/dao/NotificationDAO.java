package bl.dao;

import bl.model.*;

public abstract class NotificationDAO {

    public abstract Notification update(Notification notification);

    public abstract Notification createEventNotification(Notification notification, User user, String relatedTo, Event event, Message message, User relatesToUser, Consumer relatesToConsumer, Provider relatesToProvider);

    public abstract Notification getAllForUser(User user);

}
