package business.dao;

import business.model.Event;

/**
 * Abstract DAO for the user model
 */
public interface EventDAO {

    Event update(Event event);
}
