package api;

import business.Util;
import business.dao.DAOFactory;
import business.model.User;
import exception.LoginException;

/**
 * Entry point to the business behaviour
 */
public class Facade {

    /**
     * Singleton instance
     */
    private static Facade instance;

    /**
     * Empty constructor for singleton
     */
    private Facade() {
    }

    /**
     * Get the unique instance of the Facade
     *
     * @return An instance of Facade
     */
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    /**
     * Entry point to login a potential user (check pseudo and password)
     *
     * @param pseudo   The pseudo of the potential user
     * @param password The password of the potential user
     * @return The User if the couple pseudo/password is correct
     * @throws LoginException If there is a problem of login (missing field, unknown user, wrong password)
     */
    public User login(String pseudo, String password) throws LoginException {
        User potentialUser = DAOFactory.getInstance().getUserDAO().read(pseudo);
        if (pseudo.equals("")) {
            throw new LoginException("The pseudo can't be empty");
        } else if (password.equals("")) {
            throw new LoginException("The password can't be empty");
        } else if (potentialUser == null) {
            throw new LoginException("The pseudo or the password is wrong");
        } else if (!potentialUser.getPassword().equals(Util.getInstance().hashString(password))) {
            throw new LoginException("The password typed is wrong");
        } else {
            return potentialUser;
        }
    }

}
