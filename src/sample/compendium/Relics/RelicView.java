package sample.compendium.Relics;

import Components.Potion.ParsePotionJSONObjects;
import Components.Relic.ParseRelicJSONObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class RelicView {
    @FXML
    private AnchorPane compendiumAnchor;

    @FXML
    private AnchorPane cardsAnchor;

    @FXML
    private VBox VBox;

    @FXML
    private HBox defaultHBox;


    private ParseRelicJSONObjects relics;

    {
        try {
            relics = new ParseRelicJSONObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

        for (int i = 0; i < relics.getRelics().getRelics().size() ; i++ ) {
            try {
                ImageView relicView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + relics.getRelics().getRelics().get(i).getImage()), 100, 100, false, false));
                final Tooltip tooltip = new Tooltip();
                Button potionButton = new Button();
                potionButton.setGraphic(relicView);
                potionButton.setStyle("-fx-background-color: transparent;");
                // Add Info
                tooltip.setText(
                        relics.getRelics().getRelics().get(i).getToolTipString()
                );
                potionButton.setTooltip(tooltip);

                if(i%5 == 0){
                    HBox hBox = new HBox();
                    hBox.getChildren().add(potionButton);
                    defaultHBox = hBox;
                    VBox.getChildren().add(hBox);
                }
                else {
                    defaultHBox.getChildren().add(potionButton);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
