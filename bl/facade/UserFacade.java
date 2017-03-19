package bl.facade;

import bl.Util;
import bl.dao.DAOFactory;
import bl.exception.LoginException;
import bl.exception.SignInException;
import bl.model.Consumer;
import bl.model.Provider;
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

    public User registerConsumer(String pseudo, String password, String email, String firstName, String lastName) throws SignInException {
        User potentialUser = DAOFactory.getInstance().createUserDAO().read(pseudo);
        if (pseudo.equals("")) {
            throw new SignInException("The pseudo can't be empty");
        } else if (potentialUser != null) {
            throw new SignInException("The pseudo is already used");
        } else if (password.equals("")) {
            throw new SignInException("The password can't be empty");
        } else if (email.equals("")) {
            throw new SignInException("The email can't be empty");
        } else if (firstName.equals("")) {
            throw new SignInException("The first name can't be empty");
        } else if (lastName.equals("")) {
            throw new SignInException("The last name can't be empty");
        }
        Consumer consumer = new Consumer(pseudo, Util.getInstance().hashString(password), email, firstName, lastName);
        return DAOFactory.getInstance().createUserDAO().create(consumer);
    }

    public User registerProvider(String pseudo, String password, String email, String name, String description, String phoneNumber, String website, String officeLocation) throws SignInException {
        User potentialUser = DAOFactory.getInstance().createUserDAO().read(pseudo);
        if (pseudo.equals("")) {
            throw new SignInException("The pseudo can't be empty");
        } else if (potentialUser != null) {
            throw new SignInException("The pseudo is already used");
        } else if (password.equals("")) {
            throw new SignInException("The password can't be empty");
        } else if (email.equals("")) {
            throw new SignInException("The email can't be empty");
        } else if (name.equals("")) {
            throw new SignInException("The name can't be empty");
        } else if (description.equals("")) {
            throw new SignInException("The description can't be empty");
        } else if (!phoneNumber.equals("") && (phoneNumber.length() != 10 || !phoneNumber.matches("-?\\d+(\\.\\d+)?"))) {
            throw new SignInException("The phone number is incorrect (it needs 10 numbers)");
        }
        Provider provider = new Provider(pseudo, Util.getInstance().hashString(password), email, name, description, phoneNumber, website, officeLocation);
        return DAOFactory.getInstance().createUserDAO().create(provider);
    }

    public User updateUser(User user) {
        return user;
    }

    public boolean deleteUser(User user) {
        return true;
    }
}
