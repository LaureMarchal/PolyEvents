package ui.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

/**
 * Created by Theo Gauchoux on 30/03/2017.
 */
public class AlertHelper {

    private static AlertHelper ourInstance = new AlertHelper();

    public static AlertHelper getInstance() {
        return ourInstance;
    }

    private AlertHelper() {
    }

    public void showInfoAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public String showTextFieldAlert(String text) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ask");
        dialog.setHeaderText(null);
        dialog.setContentText(text);
        return dialog.showAndWait().orElse("");
    }

}
