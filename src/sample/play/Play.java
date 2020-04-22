package sample.play;

import Components.Card.Card;
import Components.Card.ParseCardJSONObjects;
import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Monster.ParseMonsterJSONObjects;
import Components.TopBar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    ImageView characterImg;

    @FXML
    ImageView monsterImg;

    @FXML
    Label monsterHP;

    @FXML
    Label characterHP;


    ImageView drawCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/drawImage.png")));
    ImageView discardCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/discardImage.png")));
    ImageView endTurnImg = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/endTurnImage.png"),150,150,false,false));
    ParseMonsterJSONObjects monster;
    ParseCharacterJSONObjects character;

    {
        try {
            monster = new ParseMonsterJSONObjects();
            character = new ParseCharacterJSONObjects("Ironclad");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws Exception {
        addElements();
        createCharacter();
        createMonster();
    }

    private void createMonster() throws Exception {
        monsterImg.setImage(new Image(getClass().getResourceAsStream("../../" + monster.getMonsters().getMonsters().get(2).getImage())));
        monsterHP.setText(String.valueOf(monster.getMonsters().getMonsters().get(2).getHp()));
    }

    private void createCharacter() throws Exception {
        addCards(character);
        characterHP.setText(String.valueOf(character.getCharacter().getHp()));
        characterImg.setImage(new Image(getClass().getResourceAsStream("../../" + character.getCharacter().getImage())));
    }

    private void addCards(ParseCharacterJSONObjects character) throws Exception {
        for(Card card: character.getCharacter().getCardsOfPlayer().getCardList()) {
            ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../" + card.getImage())));
            cardView.onMouseClickedProperty().set((MouseEvent t) -> {
                playCard(card);
                refreshScreen();
            });
            cardBox.getChildren().add(cardView);
            cardBox.onMouseClickedProperty().set((MouseEvent t) -> {
                int imageWidth = (int) cardView.getImage().getWidth();
                int index = (int) ((t.getX())/imageWidth);
                character.getCharacter().getCardsOfPlayer().getCardList().remove(index);
                cardBox.getChildren().remove(index);
            });
        }
    }

    private void refreshScreen() {
        monsterHP.setText((monster.getMonsters().getMonsters().get(2).getHp() + "") );
    }

    private void playCard(Card card) {
        int updatedHP = monster.getMonsters().getMonsters().get(2).getHp() - card.getDamage();
        monster.getMonsters().getMonsters().get(2).setHp(updatedHP);
    }


    private void addListeners() {
        drawButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                // TODO: Add drawCard.fxml path
                Methods.changeScreen("play/drawPile/DrawPile.fxml",drawButton);
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
        addListeners();
    }
}