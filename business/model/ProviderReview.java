package business.model;

public class ProviderReview implements Notifiable {

    private String content;
    private String rate;

    public ProviderReview(String content, String rate) {
        this.content = content;
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String getNotificationText() {
        return "A review has been written about you.";
    }
}
