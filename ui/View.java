package ui;

public enum View {

    LOGIN("login/loginView"),
    SIGN_IN("signIn/signInView"),
    MAIN("MainView"),
    CREATE_EVENT("event/createEventView"),
    SEE_EVENT("event/eventView");

    private final String text;

    View(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
