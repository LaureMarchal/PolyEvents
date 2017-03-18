package bl.dao;

import bl.model.User;

/**
 * Abstract DAO for the user model
 */
public abstract class UserDAO {

    /**
     * Get a user according to his pseudo
     *
     * @param pseudo The pseudo of the user
     * @return The user according to the pseudo, or null if no one matching
     */
    public abstract User read(String pseudo);

    public abstract User create(User user);

    public abstract User update(User user);

    public abstract boolean delete(User user);

}
