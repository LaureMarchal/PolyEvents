package api;

import business.model.User;
import exception.LoginException;

public class Facade {

    private static Facade instance;

    private Facade() {

    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public User login(String pseudo, String password) throws LoginException {
        return User.login(pseudo, password);
    }

}
