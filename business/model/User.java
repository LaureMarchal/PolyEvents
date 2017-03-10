package business.model;

import business.Util;
import business.dao.DAOFactory;
import exception.LoginException;

/**
 * Model to represent an user
 */
public class User {

    /**
     * Pseudo of the user
     */
    private String pseudo;

    /**
     * Hashed password of the user (by SHA-256)
     */
    private String password;

    /**
     * Construct a new User
     *
     * @param pseudo   The pseudo of the user
     * @param password The hashed password of the user
     */
    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    /**
     * Try to get an user by login it
     *
     * @param pseudo   The pseudo of the potential user
     * @param password The password of the potential user
     * @return The User if the couple pseudo/password is correct
     * @throws LoginException If there is a problem of login (missing field, unknown user, wrong password)
     */
    public static User login(String pseudo, String password) throws LoginException {
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

    /**
     * Get the password of the user
     *
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

}
