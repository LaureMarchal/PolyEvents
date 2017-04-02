package bl.facade;

import bl.dao.DAOFactory;
import bl.model.Event;
import bl.model.Notification;
import ui.Controller;

import java.util.List;

public class NotificationFacade {


    /**
     * Singleton instance
     */
    private static NotificationFacade instance;

    /**
     * Empty constructor for singleton
     */
    private NotificationFacade() {
    }

    public static NotificationFacade getInstance() {
        if (instance == null) {
            instance = new NotificationFacade();
        }
        return instance;
    }

    public void readNotification(Notification notification) {

    }

    public Notification update(Notification notification){
        return DAOFactory.getInstance().createNotificationDAO().updateRead(notification,true);
    }

    public Notification create(Notification notif) {
        Notification potentialNotification = DAOFactory.getInstance().createNotificationDAO().createEventNotification(notif);
        return potentialNotification;
    }

    public List<Notification> getAllNotification() {
        List<Notification> notifications = DAOFactory.getInstance().createNotificationDAO().getAllForUser(Controller.getInstance().getUserLogged());
        return notifications;
    }

}
