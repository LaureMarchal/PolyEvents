import bl.dao.DAOFactory;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.Provider;
import bl.model.Registration;
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
 * Created by Théo Gauchoux on 31/03/2017.
 */
class RegistrationFacadeTest {

    private static Provider provider;
    private static Consumer consumer;
    private static Event event;
    private static List<Registration> registrations = new ArrayList<>();

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
        for (Registration registration : registrations) {
            DAOFactory.getInstance().createRegistrationDAO().delete(registration);
        }

        DAOFactory.getInstance().createEventDAO().delete(event);
        DAOFactory.getInstance().createUserDAO().delete(consumer);
        DAOFactory.getInstance().createUserDAO().delete(provider);
    }

    @Test
    void cancel() {

        // Make a registration before test
        Registration registration = DAOFactory.getInstance().createRegistrationDAO().create(new Registration(event, consumer, new Date(), "WAITING_PAYMENT", null));
        registrations.add(registration);

        // Check registration in not already cancelled (= refused)
        Assertions.assertTrue(!registration.getStatus().equals("REFUSED"));

        // Check update is well executed
        registration.setStatus("REFUSED");
        Assertions.assertNotNull(DAOFactory.getInstance().createRegistrationDAO().update(registration));

        // Check status has been updated
        Registration registrationUpdated = DAOFactory.getInstance().createRegistrationDAO().getOne(consumer.getPseudo(), event.getId());
        Assertions.assertTrue(registrationUpdated.getStatus().equals("REFUSED"));

    }

}