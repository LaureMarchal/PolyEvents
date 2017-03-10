package api;

import business.model.User;
import exception.LoginException;

public class Facade {

    public User login(String pseudo, String password) throws LoginException {
        return User.login(pseudo, password);
    }

}
