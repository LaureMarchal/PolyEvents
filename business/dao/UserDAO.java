package business.dao;

import business.model.User;
import persistence.UserDAOPG;

public abstract class UserDAO {

    private static UserDAO instance;

    protected UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAOPG();
        }
        return instance;
    }

    public abstract User read(String pseudo);

}
