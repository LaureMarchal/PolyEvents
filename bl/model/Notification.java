package bl.model;

public class Notification {

    private int id;
    private boolean isRead;
    private User target;
    private RelatedTo relatedTo;
    private int relatedToId;
    private Notifiable content;

    public Notification(int id, boolean isRead, User target, RelatedTo relatedTo, int relatedToId, Notifiable content) {
        this.id = id;
        this.isRead = isRead;
        this.target = target;
        this.relatedTo = relatedTo;
        this.relatedToId = relatedToId;
        this.content = content;
    }

    public Notification(boolean isRead, User target, RelatedTo relatedTo, int relatedToId, Notifiable content) {
        this.isRead = isRead;
        this.target = target;
        this.relatedTo = relatedTo;
        this.relatedToId = relatedToId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Notifiable getContent() {
        return content;
    }

    public void setContent(Notifiable content) {
        this.content = content;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public RelatedTo getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(RelatedTo relatedTo) {
        this.relatedTo = relatedTo;
    }

    public int getRelatedToId() {
        return relatedToId;
    }

    public void setRelatedToId(int relatedToId) {
        this.relatedToId = relatedToId;
    }
}
