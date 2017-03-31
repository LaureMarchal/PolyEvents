package ui.helper;

import bl.facade.EventFacade;
import bl.model.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import ui.Controller;
import ui.View;

import java.util.Optional;

/**
 * Created by Theo Gauchoux on 30/03/2017.
 */
public class AlertHelper {

    private static AlertHelper ourInstance = new AlertHelper();

    private AlertHelper() {
    }

    public static AlertHelper getInstance() {
        return ourInstance;
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

    public void showConfirmationDeleteAlert(String text,Event event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            delete(event);
        } else {
            Controller.getInstance().goTo(View.MAIN);
        }
    }

    private void delete(Event event){
        EventFacade.getInstance().delete(event);
        Controller.getInstance().goTo(View.MAIN);
    }

}
