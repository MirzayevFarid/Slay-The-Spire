package sample.compendium.Potions;

import Components.Potion.ParsePotionJSONObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class PotionView {
    @FXML
    private AnchorPane compendiumAnchor;

    @FXML
    private AnchorPane cardsAnchor;

    @FXML
    private VBox VBox;

    @FXML
    private HBox defaultHBox;


    private ParsePotionJSONObjects potions;

    {
        try {
            potions = new ParsePotionJSONObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

        for (int i = 0; i < potions.getPotions().getPotions().size() ; i++ ) {
            try {
                System.out.println(potions.getPotions().getPotions().get(i).getImage());
                ImageView potionView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + potions.getPotions().getPotions().get(i).getImage())));
                final Tooltip tooltip = new Tooltip();
                Button potionButton = new Button();
                potionButton.setGraphic(potionView);
                // Add Info
                tooltip.setText(
                        potions.getPotions().getPotions().get(i).getToolTipString()
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
