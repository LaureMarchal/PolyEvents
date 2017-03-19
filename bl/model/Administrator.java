package bl.model;

import java.util.List;

public class Administrator extends User {

    public Administrator(String pseudo, String password, String email, List<Notification> notifications) {
        super(pseudo, password, email, Role.ADMINISTRATOR, notifications);
    }
}
