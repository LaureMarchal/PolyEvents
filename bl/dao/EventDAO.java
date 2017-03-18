package bl.dao;

import bl.model.Event;

import java.util.List;

public abstract class EventDAO {

    public abstract Event create(Event event);

    public abstract boolean delete(Event event);

    public abstract Event update(Event event);

    public abstract List<Event> search(String title, String tag);

}
