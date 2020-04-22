package sample.play.drawPile;

import Components.Card.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class DrawPile {
    @FXML
    private AnchorPane drawAnchor;

    @FXML
    private AnchorPane insideAnchor;

    @FXML
    private GridPane gridPane;

    private ArrayList<Card> cardsOfPlayer = new ArrayList<>();
    private Gson gson;//Google gson library for json-pojo object mapping!
    private String directory;
    private String path;// File path that includes json libraries

     public void initialize() {
         gson = new GsonBuilder().create();                     //creating gson
         directory = System.getProperty("user.dir");
         path = directory + "/src/sample/play/drawPile/JSONFiles";              //path for json files
         File folder = new File(path);
         File[] files = folder.listFiles();

         for (File file: files) {
             try {
                 Object object = gson.fromJson(new FileReader(file.getAbsolutePath()), Card.class);

                 if(object instanceof Card) {
                     cardsOfPlayer.add((Card) object);
                 }

             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

         //    TODO: Indexing of the cards of player on to the drawPile

         int index = 0;
         for (Card card: cardsOfPlayer){
             ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + card.getImage())));
             gridPane.add(cardView, 0, index);
             index++;
         }
     }

     public Card getCard(){
         return cardsOfPlayer.get(0);
     }

     public void addCard(Card card){
         cardsOfPlayer.add(card);
     }

}
