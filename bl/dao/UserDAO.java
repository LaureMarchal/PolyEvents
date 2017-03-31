package bl.dao;
/**
 * Created by Tom
 */
import bl.model.Administrator;
import bl.model.User;

import java.util.List;

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

    public abstract List<Administrator> getAllAdministrators();
}
