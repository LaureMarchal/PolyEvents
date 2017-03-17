package api;

import business.model.User;

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

    public User register(String pseudo,String firstname,String lastname,String password,String mail){
        return null;
    }

    public User updateUser(User user){
        return user;
    }

    public User deleteUser(User user){
        return user;
    }
}
