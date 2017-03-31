package bl.dao;

import bl.model.Notification;
import bl.model.User;

import java.util.List;

public abstract class NotificationDAO {

    public abstract Notification createSuspectEventNotification(Notification notification);

    public abstract Notification createEventNotification(Notification notification);

    public abstract Notification updateRead(Notification notification, Boolean read);

    public abstract List<Notification> getAllForUser(User user);

    public abstract Notification getOne(int id);

}
