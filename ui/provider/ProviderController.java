package ui.provider;

import bl.facade.ProviderFacade;
import bl.model.Consumer;
import bl.model.Event;
import bl.model.Provider;
import bl.model.ProviderReview;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import ui.Controller;
import ui.OnLoad;
import ui.View;
import ui.helper.AlertHelper;
import ui.helper.PropertyConverter;

import java.util.List;
import java.util.Map;

/**
 * Controller for the provider interface
 */
public class ProviderController implements OnLoad {

    public Label nameLabel;
    public Label descriptionLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label websiteLabel;
    public Label officeLocationLabel;

    public TitledPane yourReviewPanel;
    public Slider rateSlider;
    public TextArea commentTextArea;
    public Button postButton;
    public Button deleteButton;

    public TableView<ProviderReview> reviewsTable;
    public TableColumn<ProviderReview, String> rateColumn;
    public TableColumn<ProviderReview, String> commentColumn;

    private Provider displayedProvider;
    private ProviderReview displayedProviderReview;
    private Event returnToEvent;

    @Override
    public void onLoad(Object data) {
        // Display basic data
        Map<String, Object> dataRetrieved = (Map<String, Object>) data;
        this.displayedProvider = (Provider) dataRetrieved.get("provider");
        this.returnToEvent = (Event) dataRetrieved.get("event");
        this.nameLabel.setText(this.displayedProvider.getName());
        this.descriptionLabel.setText(this.displayedProvider.getDescription());
        this.emailLabel.setText(this.displayedProvider.getEmail());
        this.phoneLabel.setText(this.displayedProvider.getPhone());
        this.websiteLabel.setText(this.displayedProvider.getWebsite());
        this.officeLocationLabel.setText(this.displayedProvider.getOfficeLocation());

        // Don't display "Your review" for the ownership
        if (this.displayedProvider.getPseudo().equals(Controller.getInstance().getUserLogged().getPseudo())) {
            this.yourReviewPanel.setVisible(false);
        } else {
            // Display potential review by current user
            this.displayedProviderReview = ProviderFacade.getInstance().getReviewByProviderAndConsumer(this.displayedProvider, (Consumer) Controller.getInstance().getUserLogged());
            if (this.displayedProviderReview != null) {
                this.rateSlider.setValue(this.displayedProviderReview.getRate());
                this.commentTextArea.setText(this.displayedProviderReview.getContent());
            }
            this.postButton.setVisible(this.displayedProviderReview == null);
            this.deleteButton.setVisible(this.displayedProviderReview != null);
        }

        loadReviews();
    }

    public void onPost() {

        if (this.commentTextArea.getText().isEmpty()) {
            AlertHelper.getInstance().showInfoAlert("You must write a comment to post a review.");
            return;
        }

        ProviderReview result = ProviderFacade.getInstance().postReview(
                this.displayedProvider,
                (Consumer) Controller.getInstance().getUserLogged(),
                new ProviderReview(this.commentTextArea.getText(), (int) this.rateSlider.getValue())
        );

        if (result != null) {
            AlertHelper.getInstance().showInfoAlert("Your review has been submitted.");
            this.displayedProviderReview = result;
            this.postButton.setVisible(false);
            this.deleteButton.setVisible(true);
            loadReviews();
        } else {
            AlertHelper.getInstance().showInfoAlert("Your review can't be submitted.");
        }
    }

    public void onDelete() {
        if (ProviderFacade.getInstance().deleteReview(this.displayedProvider, (Consumer) Controller.getInstance().getUserLogged())) {
            AlertHelper.getInstance().showInfoAlert("Your review has been deleted");
            this.rateSlider.setValue(3);
            this.commentTextArea.setText("");
            this.displayedProviderReview = null;
            this.postButton.setVisible(true);
            this.deleteButton.setVisible(false);
            loadReviews();
        } else {
            AlertHelper.getInstance().showInfoAlert("Your review can't be deleted");
        }
    }

    public void onReturn() {
        Controller.getInstance().goTo(View.SEE_EVENT, this.returnToEvent);
    }

    private void loadReviews() {
        List<ProviderReview> reviews = ProviderFacade.getInstance().getAllReviewsByProvider(this.displayedProvider);
        this.reviewsTable.setItems(FXCollections.observableList(reviews));
        this.rateColumn.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getRate() + "/5"));
        this.commentColumn.setCellValueFactory(cellData -> PropertyConverter.getInstance().convert(cellData.getValue().getContent()));
    }

}