package persistence;

import business.dao.DAOFactory;
import business.dao.UserDAO;

public class DAOFactoryPG extends DAOFactory {


    @Override
    public UserDAO get(String daoToCreate) {

        switch (daoToCreate){
            case "userDAO":
                return UserDAO.getInstance();
            default:
                return null;
        }

    }
}
