package bl.model;

public class ProviderSubscription {

    private Provider provider;
    private Consumer consumer;
    private ProviderReview review;

    public ProviderSubscription(Provider provider, Consumer consumer, ProviderReview review) {
        this.provider = provider;
        this.consumer = consumer;
        this.review = review;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public ProviderReview getReview() {
        return review;
    }

    public void setReview(ProviderReview review) {
        this.review = review;
    }
}
