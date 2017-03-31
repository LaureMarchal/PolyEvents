package bl.dao;

import bl.model.Consumer;
import bl.model.Event;
import bl.model.EventReview;

public abstract class EventReviewDAO {

    public abstract EventReview create(Event event, Consumer consumer, EventReview eventReview);

    public abstract EventReview getReviewByEventID(int eventID, String userID);

    public abstract void delete(Event event, Consumer consumer);

}
