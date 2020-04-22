package sample.play.drawPile;

import Components.Card.ParseCardJSONObjects;
import Components.TopBar;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class DrawPile {
    @FXML
    private AnchorPane drawAnchor;

    @FXML
    private AnchorPane insideAnchor;

    @FXML
    private GridPane gridPane;

     public void initialize() throws Exception {

         ParseCardJSONObjects cards = new ParseCardJSONObjects();


         for (int i = 0; i <= 5; i++){
                for (int j = 0; j <= 6; j++){
                    ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + cards.getCardDeck().getCardList().get(1).getImage())));
                      gridPane.add(cardView, i, j);
            }
         }
     }

}
