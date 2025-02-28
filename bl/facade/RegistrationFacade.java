package bl.facade;

import bl.dao.DAOFactory;
import bl.model.Event;
import bl.model.Registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFacade {

    /**
     * Singleton instance
     */
    private static RegistrationFacade instance;

    /**
     * Empty constructor for singleton
     */
    private RegistrationFacade() {
    }

    public static RegistrationFacade getInstance() {
        if (instance == null) {
            instance = new RegistrationFacade();
        }
        return instance;
    }

    public Registration create(Registration registration) {
        Registration reg = DAOFactory.getInstance().createRegistrationDAO().create(registration);
        return reg;
    }

    public Registration cancel(Registration registration) {
        registration.setStatus("REFUSED");
        return DAOFactory.getInstance().createRegistrationDAO().update(registration);
    }

    public Registration save(Registration registration) {
        return registration;
    }

    public List<Registration> getAllRegistrations(Event event) {
        return DAOFactory.getInstance().createRegistrationDAO().findAllForEvent(event);
    }

}
