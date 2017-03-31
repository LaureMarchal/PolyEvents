package bl.dao;

import bl.model.*;

import java.util.List;

public abstract class NotificationDAO {

    public abstract Notification createSuspectEventNotification(Notification notification);

    public abstract Notification updateRead(Notification notification, Boolean read);

    public abstract Notification createEventNotification(Notification notification, User user, String relatedTo, Event event, Message message, User relatesToUser, Consumer relatesToConsumer, Provider relatesToProvider);

    public abstract List<Notification> getAllForUser(User user);

    public abstract Notification getOne(int id);

}
