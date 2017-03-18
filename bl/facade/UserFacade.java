package bl.facade;

import bl.Util;
import bl.dao.DAOFactory;
import bl.exception.LoginException;
import bl.model.User;

/**
 * Entry point to the business behaviour
 */
public class UserFacade {

    /**
     * Singleton instance
     */
    private static UserFacade instance;

    /**
     * Empty constructor for singleton
     */
    private UserFacade() {
    }

    /**
     * Get the unique instance of the UserFacade
     *
     * @return An instance of UserFacade
     */
    public static UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
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
        User potentialUser = DAOFactory.getInstance().createUserDAO().read(pseudo);
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

    public User register(User user) {
        return null;
    }

    public User updateUser(User user) {
        return user;
    }

    public boolean deleteUser(User user) {
        return true;
    }
}
