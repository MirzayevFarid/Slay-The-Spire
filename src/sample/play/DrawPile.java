package sample.play;

import Components.Card.Card;
import Components.Card.ParseCardJSONObjects;
import Components.Character.Character;
import Components.TopBar;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;


public class DrawPile {
    @FXML
    private AnchorPane drawAnchor;

    @FXML
    private AnchorPane insideAnchor;

    @FXML
    private GridPane gridPane;

    ArrayList<Card> cards;

     public void initialize() throws Exception {
         cards = new ArrayList<>();

         System.out.println(cards.get(0).getImage());
//         for (int i = 0; i <= 5; i++){
//                for (int j = 0; j <= 6; j++){
//                    ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + cards.getCardDeck().getCardList().get(1).getImage())));
//                      gridPane.add(cardView, i, j);
//            }
//         }
     }

     public Card getCard(){
         return cards.get(0);
     }

     public void addCard(Card card){
         cards.add(card);
     }

}
