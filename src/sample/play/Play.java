package sample.play;

import Components.Card.Card;
import Components.Card.Cards;
import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Monster.ParseMonsterJSONObjects;
import Components.Potion.ParsePotionJSONObjects;
import Components.Potion.Potion;
import Components.Relic.ParseRelicJSONObjects;
import Components.RelicBar;
import Components.TopBar;
import com.google.gson.Gson;
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
import sample.Methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    ImageView endTurnImg = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/endTurnImage.png"),150,150,false,false));
    ParseMonsterJSONObjects monster;
    ParseCharacterJSONObjects character;
    ParseRelicJSONObjects relics;
    ParsePotionJSONObjects potions;
    ArrayList<Card> cards = new ArrayList<>();
    public static MediaPlayer player;
    TopBar bar;
    RelicBar relicBar;
    int charHP;
    int charAttackBoost = 0;
    int charDefence = 0;



    {
        try {
            monster = new ParseMonsterJSONObjects();
            character = new ParseCharacterJSONObjects("Ironclad");
            relics = new ParseRelicJSONObjects();
            potions = new ParsePotionJSONObjects();
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
        character.getCharacter().addRelic(relics.getRelics().getRelics().get(0));
        character.getCharacter().addPotion(potions.getPotions().getPotions().get(0));
        character.getCharacter().addPotion(potions.getPotions().getPotions().get(1));
        character.getCharacter().addPotion(potions.getPotions().getPotions().get(2));
        updateDrawJson();
        updatePotions();
        characterHP.setText(String.valueOf(character.getCharacter().getHp()));
        characterImg.setImage(new Image(getClass().getResourceAsStream("../../" + character.getCharacter().getImage())));
        characterEnergy.setText(character.getCharacter().getEnergy() + "/" + character.getCharacter().getEnergy());
        bar.getHp().setText(character.getCharacter().getHp() + "/" + character.getCharacter().getHp());
        relicBar.setRelicIconImage(character.getCharacter().getRelicsOfPlayer().getRelics().get(0).getImage());
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
                cardBox.getChildren().remove(cardBox.getChildren().get(index));
                leftEnergy = leftEnergy - card.getEnergyCost();
                characterEnergy.setText(leftEnergy + "/" + charEnergy);
                cards.remove(index);
            }
        });
    }

    private void refreshScreen() {
        monsterHP.setText((monster.getMonsters().getMonsters().get(2).getHp() + "") );
        characterHP.setText((character.getCharacter().getHp() + "") );
        updatePotions();
    }

    private void playCard(Card card) throws IOException {
        if(monster.getMonsters().getMonsters().get(2).getHp() > 0) {
            character.getCharacter().getCardsOfPlayer().addDiscardList(card);
            updateDiscardJson();
            int updatedHP = monster.getMonsters().getMonsters().get(2).getHp() - card.getDamage();
            updatedHP -= character.getCharacter().getRelicsOfPlayer().getRelics().get(0).getAttackBoost();
            updatedHP -= charAttackBoost;
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
            newCharacterHP += charDefence;
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
        relicBar = new RelicBar();
        bar.getPotionButton1().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( character.getCharacter().getPotionsOfPlayer().getPotions().size() > 0 ){
                usePotion(1);
                updatePotions();
            }
        });
        bar.getPotionButton2().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( character.getCharacter().getPotionsOfPlayer().getPotions().size() > 1 ){
                usePotion(2);
                updatePotions();
            }
        });
        bar.getPotionButton3().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( character.getCharacter().getPotionsOfPlayer().getPotions().size() > 2 ){
                usePotion(3);
                updatePotions();
            }
        });
        AnchorPane.getChildren().add(bar.getTopBar());
        //AnchorPane.getChildren().add(relicBar.getRelicBar());                                                      BURASI RELIC BAR
        drawButton.setGraphic(drawCard);
        drawButton.setStyle("-fx-background-color: transparent;");
        discardButton.setGraphic(discardCard);
        discardButton.setStyle("-fx-background-color: transparent;");
        endTurnButton.setGraphic(endTurnImg);
        endTurnButton.setStyle("-fx-background-color: transparent;");
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
        Media file = new Media(new File("src/sample/play/irmakAski.wav").toURI().toString());
        player = new MediaPlayer(file);
        MediaView mediaView = new MediaView(player);
        player.setAutoPlay(true);
        player.play();
    }

    void updatePotions(){
        int characterPotionNumber = character.getCharacter().getPotionsOfPlayer().getPotions().size();
        if ( characterPotionNumber == 0 ){
            bar.setPotionImage1("IMAGES/PotionImages/NoPotion.png");
            bar.setPotionImage2("IMAGES/PotionImages/NoPotion.png");
            bar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
        }
        else if ( characterPotionNumber == 1 ) {
            bar.setPotionImage1(character.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            bar.setPotionImage2("IMAGES/PotionImages/NoPotion.png");
            bar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
        }
        else if ( characterPotionNumber == 2 ){
            bar.setPotionImage1(character.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            bar.setPotionImage2(character.getCharacter().getPotionsOfPlayer().getPotions().get(1).getImage());
            bar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
        }
        else if ( characterPotionNumber == 3 ){
            bar.setPotionImage1(character.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            bar.setPotionImage2(character.getCharacter().getPotionsOfPlayer().getPotions().get(1).getImage());
            bar.setPotionImage3(character.getCharacter().getPotionsOfPlayer().getPotions().get(2).getImage());
        }
    }

    void usePotion(int index){
        Potion potionToUse = character.getCharacter().getPotionsOfPlayer().getPotions().get(index - 1);
        System.out.println(potionToUse.toString());
        character.getCharacter().setHp(character.getCharacter().getHp() + potionToUse.getHpBoost());
        charAttackBoost += potionToUse.getAttackBoost();
        charDefence += potionToUse.getDefendBoost();
        character.getCharacter().getPotionsOfPlayer().getPotions().remove(index - 1);
        refreshScreen();
    }
}