package bl.dao;

import bl.model.Message;

public abstract class MessageDAO {

    public abstract Message create(Message message, Message parent);

}
