package bl.exception;

/**
 * Define a sign in exception
 */
public class SignInException extends Throwable {

    /**
     * The text explaining the error
     */
    private String errorText;

    /**
     * Construct a new sign in exception
     *
     * @param errorText The text to explain the error
     */
    public SignInException(String errorText) {
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
