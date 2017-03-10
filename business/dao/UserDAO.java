package business.dao;

import business.model.User;
import persistence.UserDAOPG;

public abstract class UserDAO {

    private static UserDAO userDAO;

    protected UserDAO() {
    }

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAOPG();
        }
        return userDAO;
    }

    public abstract User read(String pseudo);

}
