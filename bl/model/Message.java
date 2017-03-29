package bl.model;

import java.util.Date;
import java.util.List;

public class Message implements Notifiable {

    private String content;
    private Date postTime;
    private User writer;
    private Event event;
    private List<Message> children;
    private int id;

    public Message(int id, String content, Date postTime, User writer, Event event, List<Message> children) {
        this.id = id;
        this.content = content;
        this.postTime = postTime;
        this.writer = writer;
        this.event = event;
        this.children = children;
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

    public List<Message> getChildren() {
        return children;
    }

    public void setChildren(List<Message> children) {
        this.children = children;
    }

    @Override
    public String getNotificationText() {
        return "A message has been posted by " + writer.getPseudo() + " on " + event.getTitle();
    }
}
