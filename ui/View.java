package ui;

public enum View {

    LOGIN("login/loginView"),
    SIGN_IN("signIn/signInView"),
    MAIN("event/MainView"),
    CREATE_EVENT("event/createEventView");

    private final String text;

    View(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
