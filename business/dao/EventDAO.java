package business.dao;

import business.model.Event;

public interface EventDAO {

    Event create(Event event);

    boolean delete(Event event);

    Event update(Event event);

}
