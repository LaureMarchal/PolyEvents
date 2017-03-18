package persistence;

import business.dao.EventDAO;
import business.model.Event;

/**
 * PostgreSQL DAO for the event model
 */
public class EventDAOPG implements EventDAO {
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
}
