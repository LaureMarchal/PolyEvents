package bl.dao;
/**
 * Created by Tom on 31/03/2017.
 */
import bl.model.Event;
import bl.model.Message;
import bl.model.Tag;
import java.util.List;

public abstract class EventDAO {

    public abstract Event create(Event event);

    public abstract boolean delete(Event event);

    public abstract Event update(Event event);

    public abstract List<Event> search(String title, String tag);

    public abstract Event getOne(int id);

    public abstract List<Event> getAllEventForTag(Tag tag);

    public abstract List<Event> getAllEvent();

}
