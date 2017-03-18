package persistence;

import bl.dao.MessageDAO;
import bl.model.Message;

public class MessageDAOPG extends MessageDAO {
    @Override
    public Message create(Message message, Message parent) {
        return null;
    }
}
