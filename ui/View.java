package ui;

/**
 * Written by Théo Gauchoux
 * An string enum to describe in one place the path to use for each view
 */
public enum View {

    LOGIN("login/loginView"),
    SIGN_UP("signUp/signUpView"),
    MAIN("mainView"),
    CREATE_EVENT("event/createEventView"),
    SEE_EVENT("event/eventView"),
    UPDATE_EVENT("event/updateEventView"),
    SEE_PROVIDER("provider/providerView"),
    REGISTRATION("registration/registrationView"),
    ADD_MESSAGE("message/newMessageView");

    private final String text;

    View(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
