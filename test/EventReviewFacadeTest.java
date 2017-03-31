import bl.dao.DAOFactory;
import bl.facade.EventFacade;
import bl.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.connector.ConnectorPG;
import persistence.connector.DBMode;

import java.util.Date;
import java.util.Random;

/**
 * Created by Théo Gauchoux on 31/03/2017.
 */
class EventReviewFacadeTest {

    private static Provider provider;
    private static Consumer consumer;
    private static Event event1;
    private static Event event2;
    private static Registration registration1;
    private static Registration registration2;
    private static EventReview eventReview1;

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

        // Create event1
        event1 = DAOFactory.getInstance().createEventDAO().create(new Event(-1, "title", "subtitle", "place", "description", new Date(), new Date(), 1.0f, "constraints", 4, 12.0f, 5, "AVAILABLE", provider));

        // Create event2
        event2 = DAOFactory.getInstance().createEventDAO().create(new Event(-1, "title", "subtitle", "place", "description", new Date(), new Date(), 1.0f, "constraints", 4, 12.0f, 5, "AVAILABLE", provider));

    }

    /**
     * Clear all tables after run tests
     */
    @AfterAll
    static void tearDownAll() {
        DAOFactory.getInstance().createEventReviewDAO().delete(event1, consumer);
        DAOFactory.getInstance().createRegistrationDAO().delete(registration1);
        DAOFactory.getInstance().createRegistrationDAO().delete(registration2);
        DAOFactory.getInstance().createEventDAO().delete(event1);
        DAOFactory.getInstance().createEventDAO().delete(event2);
        DAOFactory.getInstance().createUserDAO().delete(consumer);
        DAOFactory.getInstance().createUserDAO().delete(provider);
    }

    @Test
    void create() {

        // Check fail creating the event1 review without existing registration1
        eventReview1 = EventFacade.getInstance().postReview(event1, consumer, new EventReview("A review", 3));
        Assertions.assertNull(eventReview1);

        // Create appropriate registration1 (use DAO directly because it's not the purpose of the test)
        registration1 = DAOFactory.getInstance().createRegistrationDAO().create(new Registration(event1, consumer, new Date(), "WAITING_PAYMENT", null));
        Assertions.assertNotNull(registration1);

        // Check creating the event1 review with existing registration1
        eventReview1 = EventFacade.getInstance().postReview(event1, consumer, new EventReview("A review", 3));
        Assertions.assertNotNull(eventReview1);

        // Check event1 review is saved (use DAO directly because it's not the purpose of the test)
        Assertions.assertNotNull(DAOFactory.getInstance().createEventReviewDAO().getReviewByEventID(event1.getId(), consumer.getPseudo()));

    }

    @Test
    void delete() {

        // Create appropriate registration1 (use DAO directly because it's not the purpose of the test)
        registration2 = DAOFactory.getInstance().createRegistrationDAO().create(new Registration(event2, consumer, new Date(), "WAITING_PAYMENT", null));
        Assertions.assertNotNull(registration2);

        // Create the event2 review with existing registration2
        EventReview eventReview2 = EventFacade.getInstance().postReview(event2, consumer, new EventReview("A review", 3));
        Assertions.assertNotNull(eventReview2);

        // Check deleting review (use DAO directly because it's not the purpose of the test)
        EventFacade.getInstance().deleteReview(event2, consumer);
        Assertions.assertNull(DAOFactory.getInstance().createEventReviewDAO().getReviewByEventID(event2.getId(), consumer.getPseudo()));

    }

}