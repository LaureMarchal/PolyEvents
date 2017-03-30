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
        this.nameLabel.setText(this.displayedProvider.getName());
    }

    public void initialize() {
        System.out.println("test");
    }

    public void onComment(){

    }

}
