package ui;


import bl.facade.EventFacade;
import bl.facade.UserFacade;
import bl.model.Event;
import bl.model.Provider;

/**
 * Controller for the main interface
 */
public class MainController {
    /**
     * On "Log out" button click, go to the registration interface
     */
    public void onLogout() {
        Controller.getInstance().setUserLogged(null);
        Controller.getInstance().goTo(View.LOGIN);
    }

    public void testGoToProviderPage() {
        Provider provider = (Provider) UserFacade.getInstance().getOneByPseudo("provider");
        Controller.getInstance().goTo(View.SEE_PROVIDER, provider);
    }

    public void testReportEvent() {
        Event event = EventFacade.getInstance().read(4);
        EventFacade.getInstance().report(event);
    }
}
