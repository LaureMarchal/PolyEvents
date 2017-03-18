package business.model;

import java.util.Date;
import java.util.List;

/**
 * Model to represent an event
 */
public class Event implements Notifiable {

    private int id;
    private String title;
    private String subTitle;
    private String place;
    private String description;
    private Date beginningTime;
    private Date registrationDeadline;
    private String duration;
    private String constraints;
    private int placesNumber;
    private float price;
    private int delayToPay;
    private String status;
    private Provider provider;
    private List<Registration> registrations;
    private List<Tag> tags;
    private List<Message> messages;

    public Event(int id, String title, String subTitle, String place, String description, Date beginningTime, Date registrationDeadline, String duration, String constraints, int placesNumber, float price, int delayToPay, String status, Provider provider, List<Registration> registrations, List<Tag> tags, List<Message> messages) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.place = place;
        this.description = description;
        this.beginningTime = beginningTime;
        this.registrationDeadline = registrationDeadline;
        this.duration = duration;
        this.constraints = constraints;
        this.placesNumber = placesNumber;
        this.price = price;
        this.delayToPay = delayToPay;
        this.status = status;
        this.provider = provider;
        this.registrations = registrations;
        this.tags = tags;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(Date beginningTime) {
        this.beginningTime = beginningTime;
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDelayToPay() {
        return delayToPay;
    }

    public void setDelayToPay(int delayToPay) {
        this.delayToPay = delayToPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String getNotificationText() {
        return "An new event called " + title + " has been created by " + provider.getPseudo();
    }
}
