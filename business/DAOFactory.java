package business;
import db.DAOFactoryPG;
import db.UserDAOPG;

/**
 * Created by Tom Somerville Roberts on 09/03/2017.
 */
public abstract class DAOFactory {

    protected static DAOFactory instance;

    protected DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactoryPG();
        }
        return instance;
    }

    public static UserDAO createUserDAO(){
        return UserDAO.getInstance();
    }

}
