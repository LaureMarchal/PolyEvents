package bl.dao;
/**
 * Created by Tom
 */
import bl.model.Event;
import bl.model.Message;

import java.util.List;

public abstract class MessageDAO {

    public abstract Message create(Message message, Message parent);

    public abstract boolean delete(Message message);

    public abstract List<Message> findAllMessagesForEvent(Event event);
}
