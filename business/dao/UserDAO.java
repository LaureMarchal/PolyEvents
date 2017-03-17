package business.dao;

import business.model.User;

/**
 * Abstract DAO for the user model
 */
public interface UserDAO {

    /**
     * Get a user according to his pseudo
     *
     * @param pseudo The pseudo of the user
     * @return The user according to the pseudo, or null if no one matching
     */
    User read(String pseudo);

    User create(String pseudo,String firstname,String lastname,String password,String mail);

    User update(User user);

    User delete(User user);

}
