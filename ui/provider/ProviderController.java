package ui.provider;

import bl.model.Provider;
import javafx.scene.control.Label;
import ui.OnInit;

/**
 * Controller for the provider interface
 */
public class ProviderController implements OnInit {

    private Provider displayedProvider;

    public Label nameLabel;

    @Override
    public void onInit(Object data) {
        this.displayedProvider = (Provider) data;
    }

    public void initialize() {
        //this.nameLabel.setText(this.displayedProvider.getName());
    }

    public void onComment(){

    }

}
