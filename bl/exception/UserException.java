package bl.exception;

/**
 * Define a login exception
 */
public class UserException extends Exception {

    /**
     * The text explaining the error
     */
    private String errorText;

    /**
     * Construct a new login exception
     *
     * @param errorText The text to explain the error
     */
    public UserException(String errorText) {
        this.errorText = errorText;
    }

    /**
     * Get the text explaining the error
     *
     * @return The text explaining the error
     */
    public String getErrorText() {
        return errorText;
    }

}
