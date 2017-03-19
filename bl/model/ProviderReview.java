package bl.model;

public class ProviderReview implements Notifiable {

    private String content;
    private int rate;

    public ProviderReview(String content, int rate) {
        this.content = content;
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String getNotificationText() {
        return "A review has been written about you.";
    }
}
