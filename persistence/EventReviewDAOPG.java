package persistence;

import bl.dao.EventReviewDAO;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.EventReview;

/**
 * PostgreSQL DAO for the provider review model
 */
public class EventReviewDAOPG extends EventReviewDAO {

    @Override
    public EventReview create(Event event, Consumer consumer, EventReview eventReview) {
        return null;
    }
}
