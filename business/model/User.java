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
     * Get the password of the user
     *
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

}
