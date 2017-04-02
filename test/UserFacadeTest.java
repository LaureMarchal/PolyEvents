import bl.Util;
import bl.dao.DAOFactory;
import bl.exception.UserException;
import bl.facade.UserFacade;
import bl.model.Provider;
import bl.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.connector.ConnectorPG;
import persistence.connector.DBMode;

import java.util.Random;

/**
 * Created by Mehdi Fakihani on 31/03/2017.
 */
class UserFacadeTest {

    private static User userToRegister;
    private static User userToLogin;
    private static User userToUpdate;

    private static Random randomizer = new Random();

    /**
     * Clear DB and initialize it before all tests
     */
    @BeforeAll
    static void initAll() {

        ConnectorPG.getInstance().setDBMde(DBMode.TEST);

        // Create user for check login
        userToLogin = DAOFactory.getInstance().createUserDAO().create(new Provider("provider" + randomizer.nextInt(), Util.getInstance().hashString("password"), "email@gmail.com", "name", "description", "0123456789", "website.com", "location"));

        // Create user for check update
        userToUpdate = DAOFactory.getInstance().createUserDAO().create(new Provider("provider" + randomizer.nextInt(), Util.getInstance().hashString("password"), "email@gmail.com", "name", "description", "0123456789", "website.com", "location"));

    }

    /**
     * Clear all tables after run tests
     */
    @AfterAll
    static void tearDownAll() {
        DAOFactory.getInstance().createUserDAO().delete(userToRegister);
        DAOFactory.getInstance().createUserDAO().delete(userToLogin);
        DAOFactory.getInstance().createUserDAO().delete(userToUpdate);
    }

    @Test
    void register() {

        // Check fail because empty values
        try {
            userToRegister = UserFacade.getInstance().registerConsumer("", "", "", "", "");
        } catch (UserException e) {
            // Don't log the exception
        } finally {
            Assertions.assertNull(userToRegister);
        }

        // Check registration to the app
        String pseudo = "Pseudo" + randomizer.nextInt();
        try {
            userToRegister = UserFacade.getInstance().registerConsumer(pseudo, "password", "email@gmail.com", "firstName", "lastName");
        } catch (UserException e) {
            // Don't log the exception
        } finally {
            Assertions.assertNotNull(userToRegister);
        }

        // Check user is registered (use DAO directly because it's not the purpose of the test)
        Assertions.assertNotNull(DAOFactory.getInstance().createUserDAO().read(pseudo));

    }

    @Test
    void login() {

        // Temporary variable for tests
        User result = null;

        // Check fail unknown user
        try {
            result = UserFacade.getInstance().login(randomizer.nextInt() + "", "");
        } catch (UserException e) {
            // Don't log the exception
        } finally {
            Assertions.assertNull(result);
        }

        // Check fail wrong password
        try {
            result = UserFacade.getInstance().login(userToLogin.getPseudo(), "");
        } catch (UserException e) {
            // Don't log the exception
        } finally {
            Assertions.assertNull(result);
        }

        // Check good login
        try {
            result = UserFacade.getInstance().login(userToLogin.getPseudo(), "password");
        } catch (UserException e) {
            // Don't log the exception
        } finally {
            Assertions.assertNotNull(result);
        }
    }

    @Test
    void updateProfile() {

        // Temporary variable for tests
        User result = null;

        // Check fail update empty pseudo
        try {
            userToUpdate.setPseudo("");
            result = UserFacade.getInstance().updateUser(userToUpdate);
        } catch (UserException e) {
            // Don't log the exception
        } finally {
            Assertions.assertNull(result);
        }

        // Check good update of pseudo
        try {
            userToUpdate.setPseudo("newPseudo-" + randomizer.nextInt());
            result = UserFacade.getInstance().updateUser(userToUpdate);
        } catch (UserException e) {
            // Don't log the exception
        } finally {
            Assertions.assertNotNull(result);
        }

    }

}