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
    public Label descriptionLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label websiteLabel;
    public Label officeLocationLabel;

    @Override
    public void onInit(Object data) {
        this.displayedProvider = (Provider) data;
        this.nameLabel.setText(this.displayedProvider.getName());
        this.descriptionLabel.setText(this.displayedProvider.getDescription());
        this.emailLabel.setText(this.displayedProvider.getEmail());
        this.phoneLabel.setText(this.displayedProvider.getPhone());
        this.websiteLabel.setText(this.displayedProvider.getWebsite());
        this.officeLocationLabel.setText(this.displayedProvider.getOfficeLocation());
    }

    public void onComment(){

    }

}