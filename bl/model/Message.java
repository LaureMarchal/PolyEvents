package bl.model;

import java.util.Date;
import java.util.List;

public class Message implements Notifiable {

    private String content;
    private Date postTime;
    private User writer;
    private Event event;
    private int parentID;
    private int id;

    public Message(int id, String content, Date postTime, User writer, Event event, int parentID) {
        this.id = id;
        this.content = content;
        this.postTime = postTime;
        this.writer = writer;
        this.event = event;
        this.parentID = parentID;
    }

    public String getContent() {
        return content;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    @Override
    public String getNotificationText() {
        return "A message has been posted by " + writer.getPseudo() + " on " + event.getTitle();
    }
}
