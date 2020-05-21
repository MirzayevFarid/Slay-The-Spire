package sample.play.drawPile;

import Components.Card.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Compendium {
    @FXML
    private AnchorPane drawAnchor;

    @FXML
    private AnchorPane insideAnchor;

    @FXML
    private VBox VBox;

    @FXML
    private HBox defaultHBox;


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
        int index = 0;

        for (File file: files) {
            try {
                Object object = gson.fromJson(new FileReader(file.getAbsolutePath()), Card.class);

                if(object instanceof Card) {
                    cardsOfPlayer.add((Card) object);

                    ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../../" + cardsOfPlayer.get(index).getImage())));

                    if(index%5 == 0){
                        HBox hBox = new HBox();
                        hBox.getChildren().add(cardView);
                        defaultHBox = hBox;
                        VBox.getChildren().add(hBox);
                    }
                    else {
                        defaultHBox.getChildren().add(cardView);
                    }

                    index++;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Card getCard(){
        return cardsOfPlayer.get(0);
    }

    public void addCard(Card card){
        cardsOfPlayer.add(card);
    }
}
