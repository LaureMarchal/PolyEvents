import bl.dao.DAOFactory;
import bl.dao.UserDAO;
import bl.exception.UserException;
import bl.facade.UserFacade;
import bl.model.Administrator;
import bl.model.Consumer;
import bl.model.Provider;
import bl.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.connector.ConnectorPG;
import persistence.connector.DBMode;

/**
 * Created by Mehdi on 02/04/2017.
 */
public class UserFacadeTest {
    private static User administrator;
    private static User consumer;
    private static User provider;

    /**
     * Clear DB and initialize it before all tests
     */
    @BeforeAll
    static void initAll() {

        ConnectorPG.getInstance().setDBMde(DBMode.TEST);

        UserDAO dao = DAOFactory.getInstance().createUserDAO();
        administrator = dao.create(new Administrator("admin", "password", "admin@admin"));
        consumer = dao.create(new Consumer("test", "test", "test@test", "Henri", "Marcel", "Coucou"));
        provider = dao.create(new Provider("Le provider", "provider", "provider@test", "Jean", "Best provider", "0404040404", "provider.com", "Montpellier"));
    }

    /**
     * Clear all tables after run tests
     */
    @AfterAll
    static void tearDownAll() {
        DAOFactory.getInstance().createUserDAO().delete(provider);
        DAOFactory.getInstance().createUserDAO().delete(administrator);
        DAOFactory.getInstance().createUserDAO().delete(consumer);
    }

    @Test
    void login() throws UserException {

        User admin = UserFacade.getInstance().login("admin", "password");
        User test = UserFacade.getInstance().login("test", "test");
        User provider = UserFacade.getInstance().login("Le provider", "provider");

        Assertions.assertNotNull(admin);
        Assertions.assertNotNull(test);
        Assertions.assertNotNull(provider);

    }
}
