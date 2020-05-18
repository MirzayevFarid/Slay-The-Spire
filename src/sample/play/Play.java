package sample.play;

import Components.Card.Card;
import Components.Card.Cards;
import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Monster.ParseMonsterJSONObjects;
import Components.TopBar;
import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Methods;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.util.ArrayList;


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

    @FXML
    Label characterEnergy;



    ImageView drawCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/drawImage.png")));
    ImageView discardCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/discardImage.png")));
    ImageView endTurnImg = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/endTurnImage.png"),150, 150, true, false));
    ParseMonsterJSONObjects monster;
    ParseCharacterJSONObjects character;
    ArrayList<Card> cards = new ArrayList<>();
    public static MediaPlayer player;
    TopBar bar;
    int charHP;

    {
        try {
            monster = new ParseMonsterJSONObjects();
            character = new ParseCharacterJSONObjects("Ironclad");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initialize() throws Exception {
        characterEnergy.setId("characterEnergy");
        addElements();
        File drawFile = new File("src/sample/play/drawPile/JSONFiles");
        deleteFolder(drawFile);
        File discardFile = new File("src/sample/play/discardPile/JSONFiles");
        deleteFolder(discardFile);
        createCharacter();
        createMonster();
    }


        private void createMonster() throws Exception {
        monsterImg.setImage(new Image(getClass().getResourceAsStream("../../" + monster.getMonsters().getMonsters().get(2).getImage())));
        monsterHP.setText(String.valueOf(monster.getMonsters().getMonsters().get(2).getHp()));
    }

    private void createCharacter() throws Exception {
        addCards(character);
        updateDrawJson();
        characterHP.setText(String.valueOf(character.getCharacter().getHp()));
        characterImg.setImage(new Image(getClass().getResourceAsStream("../../" + character.getCharacter().getImage())));
        characterEnergy.setText(character.getCharacter().getEnergy() + "/" + character.getCharacter().getEnergy());
        bar.getHp().setText(character.getCharacter().getHp() + "/" + character.getCharacter().getHp());
        charHP = character.getCharacter().getHp();

    }

    private void addCards(ParseCharacterJSONObjects character) {
        for(int i = 0; i < 5 - cards.size(); i++) {
            Card card = character.getCharacter().getCardsOfPlayer().getCardList().get(i);
            character.getCharacter().getCardsOfPlayer().getDrawList().remove(i);
            character.getCharacter().getCardsOfPlayer().getCardList().remove(i);
            cards.add(card);
            i--;
            ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../" + card.getImage())));
            cardView.onMouseClickedProperty().set((MouseEvent t) -> {
                int leftEnergy = Integer.parseInt(characterEnergy.getText().substring(0,1));
                if(leftEnergy >= card.getEnergyCost()) {
                    try {
                        playCard(card);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    refreshScreen();
                }
            });
            cardBox.getChildren().add(cardView);
        }

        cardBox.onMouseClickedProperty().set((MouseEvent t) -> {
            int imageWidth = 155;
            int index = (int) ((t.getX()) / imageWidth);
            int leftEnergy = Integer.parseInt(characterEnergy.getText().substring(0,1));
            Card card = cards.get(index);
            int charEnergy = character.getCharacter().getEnergy();
            if(leftEnergy >= card.getEnergyCost()) {
                KeyValue widthValue = new KeyValue(cardBox.getChildren().get(index).scaleXProperty(), 0);
                KeyValue heightValue = new KeyValue(cardBox.getChildren().get(index).scaleYProperty(), 0);
                KeyFrame frame1 = new KeyFrame(Duration.seconds(0.3), widthValue);
                KeyFrame frame2 = new KeyFrame(Duration.seconds(0.3), heightValue);
                Timeline timeline = new Timeline(frame1, frame2);
                timeline.play();
                timeline.setOnFinished(finishedEvent -> {
                    cards.remove(index);
                    cardBox.getChildren().remove(cardBox.getChildren().get(index));
                });
                leftEnergy = leftEnergy - card.getEnergyCost();
                characterEnergy.setText(leftEnergy + "/" + charEnergy);
            }
        });
    }

    private void refreshScreen() {
        monsterHP.setText((monster.getMonsters().getMonsters().get(2).getHp() + "") );
        characterHP.setText((character.getCharacter().getHp() + "") );
    }

    private void playCard(Card card) throws IOException {
        if(monster.getMonsters().getMonsters().get(2).getHp() > 0) {
            character.getCharacter().getCardsOfPlayer().addDiscardList(card);
            updateDiscardJson();
            int updatedHP = monster.getMonsters().getMonsters().get(2).getHp() - card.getDamage();
            monster.getMonsters().getMonsters().get(2).setHp(updatedHP);
        }
        else {
            victory();
        }
    }


    private void victory() {
        popUp("You Won The Game","map/map.fxml");
    }


    private void addListeners() {
        drawButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("play/drawPile/DrawPile.fxml", drawButton,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        discardButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("play/discardPile/DiscardPile.fxml", discardButton,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        endTurnButton.onMouseClickedProperty().set((MouseEvent t) -> {
            int damage = monster.getMonsters().getMonsters().get(2).getAttackPoint();
            int newCharacterHP = character.getCharacter().getHp() - damage;
            character.getCharacter().setHp(newCharacterHP);
            characterEnergy.setText(character.getCharacter().getEnergy() + "/" + character.getCharacter().getEnergy());
            bar.getHp().setText(newCharacterHP + "/" + charHP);
            exposeCards();
            refreshScreen();

            if(character.getCharacter().getHp() <= 0){
                popUp("Game Over","mainMenu/mainMenu.fxml");
            }
        });
    }

    private void popUp(String text, String path) {
        VBox pauseRoot = new VBox(15);
        pauseRoot.getChildren().add(new Label(text));
        pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        pauseRoot.setAlignment(Pos.CENTER);
        pauseRoot.setPadding(new Insets(50));


        Button exit = new Button("Exit");
        pauseRoot.getChildren().add(exit);

        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(endTurnButton.getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));

        exit.setOnAction(event -> {
            try {
                popupStage.hide();
                Methods.changeScreen(path, endTurnButton,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        popupStage.show();
    }

    private void exposeCards() {
        try {
            Cards charCards =  character.getCharacter().getCardsOfPlayer();

            for(int i = 0; i < cards.size(); i++){
                charCards.addDiscardList(cards.get(i));
            }
            updateDiscardJson();
            cards.clear();
            cardBox.getChildren().clear();

            if(charCards.getCardList().size() < 5){
                for(int i = 0; i < charCards.getDiscardList().size(); i++){
                    character.getCharacter().addCard(charCards.getDiscardList().get(i));
                    character.getCharacter().getCardsOfPlayer().getDrawList().add(charCards.getDiscardList().get(i));
                }
                charCards.getDiscardList().clear();
                updateDiscardJson();
            }

            addCards(character);
            updateDrawJson();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addElements(){
        bar = new TopBar();
        AnchorPane.getChildren().add(bar.getTopBar());
        drawButton.setGraphic(drawCard);
        drawButton.setStyle("-fx-background-color: transparent;");
        discardButton.setGraphic(discardCard);
        discardButton.setStyle("-fx-background-color: transparent;");
        endTurnButton.setGraphic(endTurnImg);
        endTurnButton.setStyle("-fx-background-color: transparent;");
        endTurnButton.setOnMouseEntered(e -> endTurnButton.setStyle("-fx-background-color: transparent; -fx-effect: dropshadow(gaussian, #FF00005F, 50, 0.3, 0, 0);"));
        endTurnButton.setOnMouseExited(e -> endTurnButton.setStyle("-fx-background-color: transparent;"));
        addListeners();
    }


    void updateDiscardJson() throws IOException {
        int index = 0;
        FileWriter writer = null;
        File file = new File("src/sample/play/discardPile/JSONFiles");
        deleteFolder(file);
        for(Card card: character.getCharacter().getCardsOfPlayer().getDiscardList()){
            writer = new FileWriter("src/sample/play/discardPile/JSONFiles/" + card.getName() + ".json");
            new Gson().toJson(card, writer);
            index++;
            writer.close();
        }
    }

    void updateDrawJson() throws IOException {
        int index = 0;
        FileWriter writer = null;
        File file = new File("src/sample/play/drawPile/JSONFiles");
        deleteFolder(file);
        for(Card card: character.getCharacter().getCardsOfPlayer().getDrawList()){
            writer = new FileWriter("src/sample/play/drawPile/JSONFiles/" + card.getName() + ".json");
            new Gson().toJson(card, writer);
            index++;
            writer.close();
        }
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
    }

    public ArrayList<Card> getCharacterCards(){
        return character.getCharacter().getCardsOfPlayer().getCardList();
    }

    public MediaPlayer getPlayer(){
        return player;
    }
    public static void music() throws IOException {
        Media file = new Media(new File("C:\\Users\\Burcu\\Documents\\1A-SS\\src\\sample\\play\\irmakAski.wav").toURI().toString());
        player = new MediaPlayer(file);
        MediaView mediaView = new MediaView(player);
        player.setAutoPlay(true);
        player.setVolume(0.5);

        player.play();


    }


}