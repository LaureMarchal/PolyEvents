package api;

import business.model.Event;

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
        return event;
    }

    public boolean delete(Event event) {
        return true;
    }

    public Event report(Event event) {
        return event;
    }
}
