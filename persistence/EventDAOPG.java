package persistence;

import bl.dao.EventDAO;
import bl.model.Event;

import java.util.List;

/**
 * PostgreSQL DAO for the event model
 */
public class EventDAOPG extends EventDAO {
    @Override
    public Event create(Event event) {
        return null;
    }

    @Override
    public boolean delete(Event event) {
        return false;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public List<Event> search(String title, String tag) {
        return null;
    }
}
