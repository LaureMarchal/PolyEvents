package bl.facade;

import bl.model.Notification;

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

}
