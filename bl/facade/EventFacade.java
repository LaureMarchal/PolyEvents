package bl.facade;

import bl.dao.DAOFactory;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.EventReview;
import bl.model.Message;

import java.util.ArrayList;
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

    public Event report(Event event) {
        return event;
    }

    public EventReview postReview(Event event, Consumer consumer, EventReview eventReview) {
        return eventReview;
    }

    public Message addMessage(Message message, Message parent) {
        return null;
    }

    public List<Event> search(String title, String tag){
        List<Event> events = DAOFactory.getInstance().createEventDAO().search(title,tag);
        return events;
    }

    public List<Event> getAllEvent() {
        //List<Event> events = DAOFactory.getInstance().createEventDAO().getAllEvent();
        return new ArrayList<>();
    }

}
