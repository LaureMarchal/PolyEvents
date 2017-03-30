package ui;

public enum View {

    LOGIN("login/loginView"),
    SIGN_UP("signUp/signUpView"),
    MAIN("mainView"),
    CREATE_EVENT("event/createEventView"),
    SEE_EVENT("event/eventView"),
    UPDATE_EVENT("event/updateEventView"),
    ADD_MESSAGE("message/newMessageView"),
    SEE_PROVIDER("provider/providerView");

    private final String text;

    View(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
