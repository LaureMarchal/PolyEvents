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
import java.util.List;
import java.util.Random;

/**
 * Created by Tom on 31/03/2017.
 */
class EventFacadeTest {

    private static Provider provider;
    private static Consumer consumer;
    private static Event event;
    private static Event eventTest;
    private static List<Event> eventsTest;
    private static Registration registration;
    private static EventReview eventReview;

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

    }

    /**
     * Clear all tables after run tests
     */
    @AfterAll
    static void tearDownAll() {
        DAOFactory.getInstance().createEventReviewDAO().delete(event, consumer);
        DAOFactory.getInstance().createRegistrationDAO().delete(registration);
        DAOFactory.getInstance().createEventDAO().delete(event);
        DAOFactory.getInstance().createUserDAO().delete(consumer);
        DAOFactory.getInstance().createUserDAO().delete(provider);
    }

    @Test
    void create() {

        // Check creating the event
        Event test = new Event(-1,
                "Title",
                "Subtitle",
                "Location",
                "Description",
                new java.util.Date(),
                new java.util.Date(),
                1.5f,
                "Restriction",
                100,
                15.0f,
                0,
                "AVAILABLE",
                provider);
        eventTest = EventFacade.getInstance().create(test);
        Assertions.assertNotNull(eventTest);

        // Check creating the event review with existing registration
        //eventReview = EventFacade.getInstance().postReview(event, consumer, new EventReview("A review", 3));
        // Assertions.assertNotNull(eventReview);

        // Check event review is saved
        //Assertions.assertNotNull(DAOFactory.getInstance().createEventReviewDAO().getReviewByEventID(event.getId(), consumer.getPseudo()));

    }

    @Test
    void search() {
        // Check search with title
        eventsTest = EventFacade.getInstance().search("title", "");//Assertions.assertNotNull(registration);
        Assertions.assertNotNull(eventsTest);
    }

    @Test
    void update() {
        // Check update with event object
        Event test = new Event(-1,
                "Title",
                "Subtitle",
                "Location",
                "Description",
                new java.util.Date(),
                new java.util.Date(),
                1.5f,
                "Restriction",
                100,
                15.0f,
                0,
                "AVAILABLE",
                provider);
        event = EventFacade.getInstance().update(test);
        Assertions.assertNotNull(event);
    }

    @Test
    void getAll() {
        // Check getAll events
        eventsTest = EventFacade.getInstance().getAllEvent();
        Assertions.assertNotNull(eventsTest);
    }


    @Test
    void delete() {

        // Check creating the event
        Event test = new Event(-1,
                "Title",
                "Subtitle",
                "Location",
                "Description",
                new java.util.Date(),
                new java.util.Date(),
                1.5f,
                "Restriction",
                10 ;
    }
}