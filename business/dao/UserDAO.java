package business.dao;

import business.model.User;

public abstract class UserDAO {

    public abstract User read(String pseudo);

}
