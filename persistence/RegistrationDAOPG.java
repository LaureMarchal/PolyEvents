package persistence;

import bl.dao.RegistrationDAO;
import bl.model.Event;
import bl.model.Registration;

import java.util.List;

public class RegistrationDAOPG extends RegistrationDAO {

    @Override
    public Registration create(Registration registration) {
        return null;
    }

    @Override
    public boolean delete(Registration registration) {
        return false;
    }

    @Override
    public Registration update(Registration registration) {
        return null;
    }

    @Override
    public List<Registration> findAll(Event event) {
        return null;
    }
}
