package bl.facade;

import bl.dao.DAOFactory;
import bl.model.*;

import java.util.List;

/**
 * Entry point to the business behaviour
 */
public class EventFacade {

    /**
     * Singleton instance
     */
    private static EventFacade instance;

    /**
     * Empty constructor for singleton
     */
    private EventFacade() {
    }

    /**
     * Get the unique instance of the EventFacade
     *
     * @return An instance of EventFacade
     */
    public static EventFacade getInstance() {
        if (instance == null) {
            instance = new EventFacade();
        }
        return instance;
    }

    public Event read(int id) {
        return DAOFactory.getInstance().createEventDAO().getOne(id);
    }

    public Event create(Event event) {
        Event potentialEvent = DAOFactory.getInstance().createEventDAO().create(event);
        return potentialEvent;
    }

    public Event update(Event event) {
        Event potentialEvent = DAOFactory.getInstance().createEventDAO().update(event);
        return potentialEvent;
    }

    public boolean delete(Event event){
        DAOFactory.getInstance().createEventDAO().delete(event);
        return true;
    }

    public void report(Event event) {
        for (Administrator administrator : DAOFactory.getInstance().createUserDAO().getAllAdministrators()) {
            Notification reportNotification = new Notification(false, administrator, RelatedTo.EVENT, event.getId(), event);
            DAOFactory.getInstance().createNotificationDAO().createSuspectEventNotification(reportNotification);
        }
    }

    public EventReview postReview(Event event, Consumer consumer, EventReview eventReview) {
        if (DAOFactory.getInstance().createRegistrationDAO().getOne(consumer.getPseudo(), event.getId()) != null) {
            return DAOFactory.getInstance().createEventReviewDAO().create(event, consumer, eventReview);
        } else {
            return null;
        }
    }

    public void deleteReview(Event event, Consumer consumer) {
        DAOFactory.getInstance().createEventReviewDAO().delete(event, consumer);
    }

    public Message addMessage(Message message, Message parent) {
        DAOFactory.getInstance().createMessageDAO().create(message,parent);
        return message;
    }

    public List<Message> getAllMessage(Event event) {
        List<Message> msg = DAOFactory.getInstance().createMessageDAO().findAllMessagesForEvent(event);
        return msg;
    }

    public List<Event> search(String title, String tag){
        List<Event> events = DAOFactory.getInstance().createEventDAO().search(title,tag);
        return events;
    }

    public List<Event> getAllEvent() {
        List<Event> events = DAOFactory.getInstance().createEventDAO().getAllEvent();
        return events;
    }

}
