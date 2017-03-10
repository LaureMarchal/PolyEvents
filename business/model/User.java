package business.model;

import business.Util;
import business.dao.DAOFactory;
import exception.LoginException;
import persistence.DAOFactoryPG;

public class User {

    private String pseudo;
    private String password;

    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public static User login(String pseudo, String password) throws LoginException {
        DAOFactory daoFactory = new DAOFactoryPG();
        User potentialUser = daoFactory.createUserDAO("userDAO").read(pseudo);
        if (pseudo.equals("")) {
            throw new LoginException("NO_PSEUDO", "The pseudo can't be empty");
        } else if (password.equals("")) {
            throw new LoginException("NO_PASSWORD", "The password can't be empty");
        } else if (potentialUser == null) {
            throw new LoginException("UNKNOWN_USER", "The pseudo or the password is wrong");
        } else if (!potentialUser.getPassword().equals(Util.getInstance().hashString(password))) {
            throw new LoginException("WRONG_PASSWORD", "The password typed is wrong");
        } else {
            return potentialUser;
        }

    }

    public String getPassword() {
        return password;
    }

}
