package ui;


/**
 * Controller for the main interface
 */
public class MainController {
    /**
     * On "Log out" button click, go to the registration interface
     */
    public void onLogout() {
        Controller.getInstance().removeUserLogged();
        Controller.getInstance().goTo(View.LOGIN);
    }
}
