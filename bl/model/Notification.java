package bl.model;

public class Notification {

    private int id;
    private boolean isRead;
    private Notifiable target;

    public Notification(int id, boolean isRead, Notifiable target) {
        this.id = id;
        this.isRead = isRead;
        this.target = target;
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

    public Notifiable getTarget() {
        return target;
    }

    public void setTarget(Notifiable target) {
        this.target = target;
    }

}
