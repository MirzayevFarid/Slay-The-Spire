package sample.compendium.Watcher;

import Components.Card.ParseCardJSONObjects;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CardView {
    @FXML
    private AnchorPane compendiumAnchor;

    @FXML
    private AnchorPane cardsAnchor;

    @FXML
    private VBox VBox;

    @FXML
    private HBox defaultHBox;


    private ParseCardJSONObjects defectCards;

    {
        try {
            defectCards = new ParseCardJSONObjects("Watcher");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

        for (int i = 0 ; i < defectCards.getCardDeck().getCardList().size() ; i++ ) {
            try {
                System.out.println(defectCards.getCardDeck().getCardList().get(i).getImage());
                ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + defectCards.getCardDeck().getCardList().get(i).getImage())));

                if(i%5 == 0){
                    HBox hBox = new HBox();
                    hBox.getChildren().add(cardView);
                    defaultHBox = hBox;
                    VBox.getChildren().add(hBox);
                }
                else {
                    defaultHBox.getChildren().add(cardView);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
