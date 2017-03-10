package business.dao;

import persistence.DAOFactoryPG;

public abstract class DAOFactory {

    private static DAOFactory instance;

    protected DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactoryPG();
        }
        return instance;
    }

    public abstract UserDAO getUserDAO();

}
