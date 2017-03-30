package ui;

/**
 * Interface that must be implemented by controllers that need additional data to start
 */
public interface OnInit {
    /**
     * Method called when the controller is loaded
     *
     * @param data Data to inject inside the controller
     */
    void onInit(Object data);
}
