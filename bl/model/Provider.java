package bl.model;

import java.util.List;

public class Provider extends User {

    private String name;
    private String description;
    private String phone;
    private String website;
    private String officeLocation;
    private List<Event> events;
    private List<ProviderSubscription> subscribers;

    public Provider(String pseudo, String password, String email, String name, String description, String phone, String website, String officeLocation, List<Event> events, List<ProviderSubscription> subscribers) {
        super(pseudo, password, email, Role.PROVIDER);
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.website = website;
        this.officeLocation = officeLocation;
        this.events = events;
        this.subscribers = subscribers;
    }

    public Provider(String pseudo, String password, String email, String name, String description, String phone, String website, String officeLocation) {
        super(pseudo, password, email, Role.PROVIDER);
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.website = website;
        this.officeLocation = officeLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<ProviderSubscription> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ProviderSubscription> subscribers) {
        this.subscribers = subscribers;
    }
}
