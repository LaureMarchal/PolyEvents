package bl.model;

import java.util.List;

/**
 * Model to represent an user
 */
public class User {

    private String pseudo;
    private String password;
    private String email;
    private String role;
    private List<Notification> notifications;

    public User(String pseudo, String password, String email, String role, List<Notification> notifications) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.role = role;
        this.notifications = notifications;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

}
