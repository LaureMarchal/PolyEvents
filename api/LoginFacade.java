package api;

import business.Util;
import business.dao.DAOFactory;
import business.model.User;
import exception.LoginException;

/**
 * Entry point to the business behaviour
 */
public class LoginFacade {

    /**
     * Singleton instance
     */
    private static LoginFacade instance;

    /**
     * Empty constructor for singleton
     */
    private LoginFacade() {
    }

    /**
     * Get the unique instance of the LoginFacade
     *
     * @return An instance of LoginFacade
     */
    public static LoginFacade getInstance() {
        if (instance == null) {
            instance = new LoginFacade();
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
