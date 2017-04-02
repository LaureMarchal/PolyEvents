import bl.dao.DAOFactory;
import bl.facade.NotificationFacade;
import bl.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.connector.ConnectorPG;
import persistence.connector.DBMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by Laure on 02/04/2017.
 */
public class NotificationFacadeTest {

    private static Provider provider;
    private static Consumer consumer;
    private static Event event;
    private static Registration registration;
    private static List<Notification> notifications = new ArrayList<>();

    private static Random randomizer = new Random();

    /**
     * Clear DB and initialize it before all tests
     */
    @BeforeAll
    static void initAll() {

        ConnectorPG.getInstance().setDBMde(DBMode.TEST);

        // Create provider
        provider = (Provider) DAOFactory.getInstance().createUserDAO().create(new Provider("provider" + randomizer.nextInt(), "password", "email@gmail.com", "name", "description", "0123456789", "website.com", "location"));


        // Create consumer
        consumer = (Consumer) DAOFactory.getInstance().createUserDAO().create(new Consumer("consumer" + randomizer.nextInt(), "password", "email@gmail.com", "firstname", "lastname", "comments"));

        // Create event
        event = DAOFactory.getInstance().createEventDAO().create(new Event(-1, "title", "subtitle", "place", "description", new Date(), new Date(), 1.0f, "constraints", 4, 12.0f, 5, "AVAILABLE", provider));

        registration = DAOFactory.getInstance().createRegistrationDAO().create(new Registration(event,consumer,new Date(),"REGISTERED",null));
    }

    /**
     * Clear all tables after run tests
     */
    @AfterAll
    static void tearDownAll() {
        for (Notification notification : notifications) {
            DAOFactory.getInstance().createNotificationDAO().delete(notification);
        }

        DAOFactory.getInstance().createRegistrationDAO().delete(registration);
        DAOFactory.getInstance().createEventDAO().delete(event);
        DAOFactory.getInstance().createUserDAO().delete(consumer);
        DAOFactory.getInstance().createUserDAO().delete(provider);
    }

    @Test
    void cancel() {

        // Make a notification before test (use DAO directly because it's not the purpose of the test)
        Notification notification = DAOFactory.getInstance().createNotificationDAO().createEventNotification(new Notification(false,consumer,RelatedTo.EVENT,event.getId(),event));
        notifications.add(notification);

        // Check notification in not already read
        Assertions.assertTrue(notification.isRead()==false);

        // Check update is well executed
        Assertions.assertNotNull(NotificationFacade.getInstance().update(notification));

        // Check isRead has been updated (use DAO directly because it's not the purpose of the test)
        Notification notificationUpdated = DAOFactory.getInstance().createNotificationDAO().getOne(notification.getId());
        Assertions.assertTrue(notificationUpdated.isRead()==true);

    }

}
