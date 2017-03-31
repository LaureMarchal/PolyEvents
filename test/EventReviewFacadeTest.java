package test;

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
    private static Event event;
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

        // Check fail creating the event review without existing registration
        eventReview = EventFacade.getInstance().postReview(event, consumer, new EventReview("A review", 3));
        Assertions.assertNull(eventReview);

        // Create appropriate registration
        registration = DAOFactory.getInstance().createRegistrationDAO().create(new Registration(event, consumer, new Date(), "WAITING_PAYMENT", null));
        Assertions.assertNotNull(registration);

        // Check creating the event review with existing registration
        eventReview = EventFacade.getInstance().postReview(event, consumer, new EventReview("A review", 3));
        Assertions.assertNotNull(eventReview);

        // Check event review is saved
        Assertions.assertNotNull(DAOFactory.getInstance().createEventReviewDAO().getReviewByEventID(event.getId(), consumer.getPseudo()));

    }

}