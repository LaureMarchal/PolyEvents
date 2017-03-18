package business.model;

public class TagSubscription {

    private Tag tag;
    private Consumer consumer;

    public TagSubscription(Tag tag, Consumer consumer) {
        this.tag = tag;
        this.consumer = consumer;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
