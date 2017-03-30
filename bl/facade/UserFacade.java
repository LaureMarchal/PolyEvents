package bl.facade;

import bl.Util;
import bl.dao.DAOFactory;
import bl.exception.UserException;
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
     * @throws UserException If there is a problem of login (missing field, unknown user, wrong password)
     */
    public User login(String pseudo, String password) throws UserException {
        User potentialUser = DAOFactory.getInstance().createUserDAO().read(pseudo);
        if (pseudo.equals("")) {
            throw new UserException("The pseudo can't be empty");
        } else if (password.equals("")) {
            throw new UserException("The password can't be empty");
        } else if (potentialUser == null) {
            throw new UserException("The pseudo or the password is wrong");
        } else if (!potentialUser.getPassword().equals(Util.getInstance().hashString(password))) {
            throw new UserException("The password typed is wrong");
        } else {
            return potentialUser;
        }
    }

    public User registerConsumer(String pseudo, String password, String email, String firstName, String lastName) throws UserException {
        User potentialUser = DAOFactory.getInstance().createUserDAO().read(pseudo);
        if (pseudo.equals("")) {
            throw new UserException("The pseudo can't be empty");
        } else if (potentialUser != null) {
            throw new UserException("The pseudo is already used");
        } else if (password.equals("")) {
            throw new UserException("The password can't be empty");
        } else if (email.equals("")) {
            throw new UserException("The email can't be empty");
        } else if (firstName.equals("")) {
            throw new UserException("The first name can't be empty");
        } else if (lastName.equals("")) {
            throw new UserException("The last name can't be empty");
        }
        Consumer consumer = new Consumer(pseudo, Util.getInstance().hashString(password), email, firstName, lastName, "");
        return DAOFactory.getInstance().createUserDAO().create(consumer);
    }

    public User registerProvider(String pseudo, String password, String email, String name, String description, String phoneNumber, String website, String officeLocation) throws UserException {
        User potentialUser = DAOFactory.getInstance().createUserDAO().read(pseudo);
        if (pseudo.equals("")) {
            throw new UserException("The pseudo can't be empty");
        } else if (potentialUser != null) {
            throw new UserException("The pseudo is already used");
        } else if (password.equals("")) {
            throw new UserException("The password can't be empty");
        } else if (email.equals("")) {
            throw new UserException("The email can't be empty");
        } else if (name.equals("")) {
            throw new UserException("The name can't be empty");
        } else if (description.equals("")) {
            throw new UserException("The description can't be empty");
        } else if (!phoneNumber.equals("") && (phoneNumber.length() != 10 || !phoneNumber.matches("-?\\d+(\\.\\d+)?"))) {
            throw new UserException("The phone number is incorrect (it needs 10 numbers)");
        }
        Provider provider = new Provider(pseudo, Util.getInstance().hashString(password), email, name, description, phoneNumber, website, officeLocation);
        return DAOFactory.getInstance().createUserDAO().create(provider);
    }

    public User updateUser(User user) throws UserException {
        if (user.getPseudo().isEmpty()) {
            throw new UserException("The pseudo can't be empty");
        } else if (user.getPassword().isEmpty()) {
            throw new UserException("The password can't be empty");
        } else if (user.getEmail().isEmpty()) {
            throw new UserException("The email can't be empty");
        } else if (user.getRole() == null) {
            throw new UserException("The role can't be null");
        }

        switch (user.getRole()) {
            case CONSUMER:
                Consumer consumer = (Consumer) user;
                if (consumer.getFirstName().isEmpty()) {
                    throw new UserException("The first name can't be empty");
                } else if (consumer.getLastName().isEmpty()) {
                    throw new UserException("The last name can't be empty");
                }
                break;
            case PROVIDER:
                Provider provider = (Provider) user;
                if (provider.getName().isEmpty()) {
                    throw new UserException("The name can't be empty");
                } else if (provider.getDescription().isEmpty()) {
                    throw new UserException("The description can't be empty");
                } else if (!provider.getPhone().isEmpty() && (provider.getPhone().length() != 10 || !provider.getPhone().matches("-?\\d+(\\.\\d+)?"))) {
                    throw new UserException("The phone number is incorrect (it needs 10 numbers)");
                }
                break;
            case ADMINISTRATOR:
                break;
        }

        return DAOFactory.getInstance().createUserDAO().update(user);
    }

    public User getOneByPseudo(String pseudo) {
        return DAOFactory.getInstance().createUserDAO().read(pseudo);
    }

}
