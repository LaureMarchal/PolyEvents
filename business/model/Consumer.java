package business.model;

import java.util.List;

public class Consumer extends User {

    private String firstName;
    private String lastName;
    private String comments;
    private List<ProviderSubscription> providerSubscription;
    private List<TagSubscription> tagSubscriptions;
    private List<Registration> registrations;

    public Consumer(String pseudo, String password, String email, String role, List<Notification> notifications, String firstName, String lastName, String comments, List<ProviderSubscription> providerSubscription, List<TagSubscription> tagSubscriptions, List<Registration> registrations) {
        super(pseudo, password, email, role, notifications);
        this.firstName = firstName;
        this.lastName = lastName;
        this.comments = comments;
        this.providerSubscription = providerSubscription;
        this.tagSubscriptions = tagSubscriptions;
        this.registrations = registrations;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<ProviderSubscription> getProviderSubscription() {
        return providerSubscription;
    }

    public void setProviderSubscription(List<ProviderSubscription> providerSubscription) {
        this.providerSubscription = providerSubscription;
    }

    public List<TagSubscription> getTagSubscriptions() {
        return tagSubscriptions;
    }

    public void setTagSubscriptions(List<TagSubscription> tagSubscriptions) {
        this.tagSubscriptions = tagSubscriptions;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
