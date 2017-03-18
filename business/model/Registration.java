package business.model;

import java.util.Date;

public class Registration implements Notifiable {

    private Event event;
    private Consumer consumer;
    private Date creationTime;
    private String status;
    private EventReview review;

    public Registration(Event event, Consumer consumer, Date creationTime, String status, EventReview review) {
        this.event = event;
        this.consumer = consumer;
        this.creationTime = creationTime;
        this.status = status;
        this.review = review;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EventReview getReview() {
        return review;
    }

    public void setReview(EventReview review) {
        this.review = review;
    }

    @Override
    public String getNotificationText() {
        return consumer.getPseudo() + " is now registered to the event " + event.getTitle();
    }

}
