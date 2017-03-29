package bl.dao;

import bl.model.Event;
import bl.model.Registration;

import java.util.List;

public abstract class RegistrationDAO {

    public abstract Registration create(Registration registration);

    public abstract boolean delete(Registration registration);

    public abstract Registration update(Registration registration);

    public abstract List<Registration> findAll(Event event);

    public abstract Registration getOne(String userID, int eventID);

}
