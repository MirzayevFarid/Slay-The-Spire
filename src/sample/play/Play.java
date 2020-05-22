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
import sample.Constants;
import sample.Main;
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


    /**
     * Defining and initializing objects
     */
    ImageView drawCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/drawImage.png")));
    ImageView discardCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/discardImage.png")));
    ImageView endTurnImg = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/endTurnImage.png"),150, 150, true, false));
    ParseMonsterJSONObjects monster;
    ParseRelicJSONObjects relics;
    ParsePotionJSONObjects potions;
    ArrayList<Card> cards = new ArrayList<>();
    public static MediaPlayer player;
    TopBar topBar;
    RelicBar relicBar;

    int initialCharHP;
    int initialMonsterHP;
    int charAttackBoost = 0;
    int charDefence = 0;
    {
        try {
            monster = new ParseMonsterJSONObjects();
            relics = new ParseRelicJSONObjects();
            potions = new ParsePotionJSONObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initialize() throws Exception {
        characterEnergy.setId("characterEnergy");
        File drawFile = new File("src/sample/play/drawPile/JSONFiles");
        deleteFolder(drawFile);
        File discardFile = new File("src/sample/play/discardPile/JSONFiles");
        deleteFolder(discardFile);
        for(int i = 0; i < Constants.mainCharacter.getCharacter().getCardsOfPlayer().getCardList().size(); i++){
            Constants.mainCharacter.getCharacter().getCardsOfPlayer().getDrawList().add(Constants.mainCharacter.getCharacter().getCardsOfPlayer().getCardList().get(i));
        }
        createCharacter();
        addElements();
        createMonster();
        updatePotions();
    }


    /**
     * Creating monster and setting image
     * @throws Exception
     */
    private void createMonster() throws Exception {
        initialMonsterHP = monster.getMonsters().getMonsters().get(2).getHp();
        monsterImg.setImage(new Image(getClass().getResourceAsStream("../../" + monster.getMonsters().getMonsters().get(2).getImage())));
        monsterHP.setText(initialMonsterHP + "/" + initialMonsterHP);
    }

    /**
     * Creating character, setting character cards, hp, and image
     * @throws Exception
     */
    private void createCharacter() throws Exception {
        initialCharHP = Constants.mainCharacter.getCharacter().getHp();
        addCards(Constants.mainCharacter);
        Constants.mainCharacter.getCharacter().addPotion(potions.getPotions().getPotions().get(0));
        Constants.mainCharacter.getCharacter().addPotion(potions.getPotions().getPotions().get(1));
        Constants.mainCharacter.getCharacter().addPotion(potions.getPotions().getPotions().get(2));
        Constants.mainCharacter.getCharacter().addRelic(relics.getRelics().getRelics().get(0));
        Constants.mainCharacter.getCharacter().addRelic(relics.getRelics().getRelics().get(1));
        Constants.mainCharacter.getCharacter().addRelic(relics.getRelics().getRelics().get(2));
        updateDrawJson();
        characterHP.setText(initialCharHP + "/" + initialCharHP);
        characterImg.setImage(new Image(getClass().getResourceAsStream("../../" + Constants.mainCharacter.getCharacter().getImage())));
        characterEnergy.setText(Constants.mainCharacter.getCharacter().getEnergy() + "/" + Constants.mainCharacter.getCharacter().getEnergy());

    }

    /**
     * Getting cards from character's card list and showing them on combat screen
     * @param character
     */
    private void addCards(ParseCharacterJSONObjects character) {
        for(int i = 0; i < 5 - cards.size(); i++) {
            System.out.println("ADD CARDS" + i);
            Card card = character.getCharacter().getCardsOfPlayer().getDrawList().get(i);
            character.getCharacter().getCardsOfPlayer().getDrawList().remove(i);
            cards.add(card);
            i--;
            ImageView cardView = new ImageView(new Image(getClass().getResourceAsStream("../../" + card.getImage())));
            // Adding click listener for every card image
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

        // Adding click listener to the cardbox for removing card when player uses card
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

    /**
     * Updating monster and character's hp data on the screen
     */
    private void refreshScreen() {
        monsterHP.setText((monster.getMonsters().getMonsters().get(2).getHp() + "/") + initialMonsterHP );
        characterHP.setText((Constants.mainCharacter.getCharacter().getHp() + "/")+ initialCharHP );
        topBar.getGold().setText("" + Constants.mainCharacter.getCharacter().getGold());
        characterHP.setText((Constants.mainCharacter.getCharacter().getHp() + "/")+ initialCharHP );
        updatePotions();
    }


    /**
     * This method is going to execute when player clicks the cards.
     * Monsters hp will change based on card's damage property.
     * @param card represents the card which user played
     * @throws IOException
     */
    private void playCard(Card card) throws IOException {
        if(card.getDamage() < monster.getMonsters().getMonsters().get(2).getHp()) {
            Constants.mainCharacter.getCharacter().getCardsOfPlayer().addDiscardList(card);
            updateDiscardJson();
            int updatedHP = monster.getMonsters().getMonsters().get(2).getHp() - card.getDamage();
            updatedHP -= Constants.mainCharacter.getCharacter().getRelicsOfPlayer().getRelics().get(0).getAttackBoost();
            updatedHP -= charAttackBoost;
            monster.getMonsters().getMonsters().get(2).setHp(updatedHP);
            charDefence += card.getDefence();
        }
        else {
            monster.getMonsters().getMonsters().get(2).setHp(0);
            victory();
        }
    }


    private void victory() {
        popUp("You Won The Game","map/map.fxml");
    }


    /**
     * Adding mouse listeners to the buttons on the screen. Draw, discard and endTurn buttons.
     */
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
            int newCharacterHP = Constants.mainCharacter.getCharacter().getHp() - damage + charDefence;
            Constants.mainCharacter.getCharacter().setHp(newCharacterHP);
            characterEnergy.setText(Constants.mainCharacter.getCharacter().getEnergy() + "/" + Constants.mainCharacter.getCharacter().getEnergy());
            topBar.getHp().setText(newCharacterHP + "/" + initialCharHP);
            topBar.getGold().setText("" + Constants.mainCharacter.getCharacter().getGold());
            exposeCards();
            refreshScreen();

            if(Constants.mainCharacter.getCharacter().getHp() <= 0){
                popUp("Game Over","mainMenu/mainMenu.fxml");
            }
        });
    }

    /**
     * Popup screen that pops up when either game win or over
     * @param text is a message of popup
     * @param path represents where to be directed when user clicks exit button on popup
     */
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


    /**
     * This method will be executed when the players turn ends.
     * If there is enough card in draw card part, they will load to play screen.
     * Otherwise all cards in discardPile will be loaded to drawPile and play screen
     */
    private void exposeCards() {
        try {
            Cards charCards =  Constants.mainCharacter.getCharacter().getCardsOfPlayer();

            for(int i = 0; i < cards.size(); i++){
                charCards.addDiscardList(cards.get(i));
            }
            updateDiscardJson();
            cards.clear();
            cardBox.getChildren().clear();

            if(charCards.getDrawList().size() < 5){
                for(int i = 0; i < charCards.getDiscardList().size(); i++){
                    System.out.println(i);
                    Constants.mainCharacter.getCharacter().getCardsOfPlayer().getDrawList().add(charCards.getDiscardList().get(i));
                }
                charCards.getDiscardList().clear();
                updateDiscardJson();
            }


            addCards(Constants.mainCharacter);
            updateDrawJson();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding buttons and top bar to the screen
     */
    void addElements(){
        VBox bars = new VBox(-63);
        topBar = new TopBar();

        relicBar = new RelicBar(Constants.mainCharacter);
        topBar.getHp().setText(Constants.mainCharacter.getCharacter().getHp() + "/" + Constants.mainCharacter.getCharacter().getHp());
        topBar.getGold().setText(Constants.mainCharacter.getCharacter().getGold() + "");


        bars.getChildren().add(topBar.getTopBar());
        bars.getChildren().add(relicBar.getRelicBar());

        AnchorPane.getChildren().add(bars);

        addPotionListeners();

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


    private void addPotionListeners() {
        topBar.getPotionButton1().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size() > 0 ){
                usePotion(1);
                updatePotions();
            }
        });
        topBar.getPotionButton2().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size() > 1 ){
                usePotion(2);
                updatePotions();
            }
        });
        topBar.getPotionButton3().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size() > 2 ){
                usePotion(3);
                updatePotions();
            }
        });
    }


    void updateDiscardJson() throws IOException {
        int index = 0;
        FileWriter writer = null;
        File file = new File("src/sample/play/discardPile/JSONFiles");
        deleteFolder(file);
        for(Card card: Constants.mainCharacter.getCharacter().getCardsOfPlayer().getDiscardList()){
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
        for(Card card: Constants.mainCharacter.getCharacter().getCardsOfPlayer().getDrawList()){
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
        return Constants.mainCharacter.getCharacter().getCardsOfPlayer().getCardList();
    }

    public MediaPlayer getPlayer(){
        return player;
    }

    public static void music() throws IOException {
        Media file = new Media(new File("src/sample/play/irmakAski.wav").toURI().toString());
        player = new MediaPlayer(file);
        MediaView mediaView = new MediaView(player);
        player.setAutoPlay(true);
        player.setVolume(0.5);

        player.play();


    }

    void updatePotions(){
        int characterPotionNumber = Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size();
        if ( characterPotionNumber == 0 ){
            topBar.setPotionImage1("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText1("No Potion");
            topBar.setPotionImage2("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText2("No Potion");
            topBar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText3("No Potion");
        }
        else if ( characterPotionNumber == 1 ) {
            topBar.setPotionImage1(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            topBar.setToolTipText1(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getToolTipString());
            topBar.setPotionImage2("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText2("No Potion");
            topBar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText3("No Potion");
        }
        else if ( characterPotionNumber == 2 ){
            topBar.setPotionImage1(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            topBar.setToolTipText1(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getToolTipString());
            topBar.setPotionImage2(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getImage());
            topBar.setToolTipText2(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getToolTipString());
            topBar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText3("No Potion");
        }
        else if ( characterPotionNumber == 3 ){
            topBar.setPotionImage1(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            topBar.setToolTipText1(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getToolTipString());
            topBar.setPotionImage2(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getImage());
            topBar.setToolTipText2(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getToolTipString());
            topBar.setPotionImage3(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(2).getImage());
            topBar.setToolTipText3(Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(2).getToolTipString());
        }
    }

    void usePotion(int index){
        Potion potionToUse = Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(index - 1);
        Constants.mainCharacter.getCharacter().setHp(Constants.mainCharacter.getCharacter().getHp() + potionToUse.getHpBoost());
        charAttackBoost += potionToUse.getAttackBoost();
        charDefence += potionToUse.getDefenceBoost();
        Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().remove(index - 1);
        refreshScreen();
    }
}