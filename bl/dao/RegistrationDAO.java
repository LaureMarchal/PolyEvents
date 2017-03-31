package bl.dao;
/**
 * Created by Tom
 */
import bl.model.Consumer;
import bl.model.Event;
import bl.model.Registration;

import java.util.List;

public abstract class RegistrationDAO {

    public abstract Registration create(Registration registration);

    public abstract boolean delete(Registration registration);

    public abstract Registration update(Registration registration);

    public abstract List<Registration> findAllForEvent(Event event);

    public abstract Registration getOne(String userID, int eventID);

    public abstract List<Registration> findAllForConsumer(Consumer consumer);

}
