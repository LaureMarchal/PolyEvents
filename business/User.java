package business;

import db.UserDAO;
import exception.LoginException;

public class User {

    private String pseudo;
    private String password;

    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public static User login(String pseudo, String password) throws LoginException {
        User potentialUser = UserDAO.getInstance().read(pseudo);
        if (pseudo.equals("")) {
            throw new LoginException("NO_PSEUDO", "The pseudo can't be empty");
        } else if (password.equals("")) {
            throw new LoginException("NO_PASSWORD", "The password can't be empty");
        } else if (potentialUser == null) {
            throw new LoginException("UNKNOW_USER", "The user doesn't exist");
        } else if (!potentialUser.getPassword().equals(Util.getInstance().hashString(password))) {
            throw new LoginException("WRONG_PASSWORD", "The password typed is wrong");
        } else {
            return potentialUser;
        }

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
