package sample.play.drawPile;

import Components.Card.ParseCardJSONObjects;
import Components.TopBar;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class DrawPile {
    @FXML
    private AnchorPane drawAnchor;

    @FXML
    private AnchorPane insideAnchor;

    @FXML
    private GridPane drawGrid;

    @FXML
    private  ImageView imageView;


     public void initialize() throws Exception {

         ParseCardJSONObjects cards = new ParseCardJSONObjects();
         System.out.println(cards.toString());

         Image image = new Image("IMAGES/play/black.jpeg");
         imageView.setImage(image);
         for (int i = 0; i <= 4; i++){
                for (int j = 0; j <= 15; j++){
                    System.out.println(" i: " + i +  "j: " + j);
                    ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + cards.getCardDeck().getCardList().get(1).getImage())));
                      drawGrid.add(cardView, i, j);
            }
         }
     }

}
