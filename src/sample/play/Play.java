package sample.play;

import Components.Card.ParseCardJSONObjects;
import Components.TopBar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sample.Methods;

import java.io.IOException;


public class Play {
    @FXML
    AnchorPane AnchorPane;

    @FXML
    Button discardButton;

    @FXML
    Button drawButton;

    @FXML
    ImageView energyView;

    @FXML
    Button endTurnButton;

    @FXML
    HBox cardBox;

    ImageView drawCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/drawImage.png")));
    ImageView discardCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/discardImage.png")));
    ImageView endTurnImg = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/endTurnImage.png"),150,150,false,false));


    public void initialize(){
        addElements();
        addListeners();
        ParseCardJSONObjects cards = null;
        try {
            cards = new ParseCardJSONObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("../../" + cards.getCardDeck().getCardList().get(2).getImage());

        ImageView cardImage0 = new ImageView(new Image(getClass().getResourceAsStream("../../" + cards.getCardDeck().getCardList().get(0).getImage())));
        ImageView cardImage1 = new ImageView(new Image(getClass().getResourceAsStream("../../" + cards.getCardDeck().getCardList().get(1).getImage())));
        ImageView cardImage2 = new ImageView(new Image(getClass().getResourceAsStream("../../" + cards.getCardDeck().getCardList().get(2).getImage())));
        ImageView cardImage3 = new ImageView(new Image(getClass().getResourceAsStream("../../" + cards.getCardDeck().getCardList().get(3).getImage())));

        cardBox.getChildren().add(cardImage0);
        cardBox.getChildren().add(cardImage1);
        cardBox.getChildren().add(cardImage2);
        cardBox.getChildren().add(cardImage3);

    }

    private void addListeners() {
        drawButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                // TODO: Add drawCard.fxml path
                Methods.changeScreen("",drawButton);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        discardButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                // TODO: Add discard.fxml path
                Methods.changeScreen("",drawButton);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        endTurnButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                // TODO: Add endTurn.fxml path
                Methods.changeScreen("",endTurnButton);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    void addElements(){
        TopBar bar = new TopBar();
        AnchorPane.getChildren().add(bar.getTopBar());
        drawButton.setGraphic(drawCard);
        drawButton.setStyle("-fx-background-color: transparent;");
        discardButton.setGraphic(discardCard);
        discardButton.setStyle("-fx-background-color: transparent;");
        endTurnButton.setGraphic(endTurnImg);
        endTurnButton.setStyle("-fx-background-color: transparent;");
    }
}
