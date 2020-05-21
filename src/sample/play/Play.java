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
    ParseCharacterJSONObjects mainCharacter;
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
        mainCharacter = Constants.mainCharacter;
        characterEnergy.setId("characterEnergy");
        File drawFile = new File("src/sample/play/drawPile/JSONFiles");
        deleteFolder(drawFile);
        File discardFile = new File("src/sample/play/discardPile/JSONFiles");
        deleteFolder(discardFile);
        createCharacter();
        addElements();
        createMonster();
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
        initialCharHP = mainCharacter.getCharacter().getHp();
        addCards(mainCharacter);
        mainCharacter.getCharacter().addPotion(potions.getPotions().getPotions().get(0));
        mainCharacter.getCharacter().addPotion(potions.getPotions().getPotions().get(1));
        mainCharacter.getCharacter().addPotion(potions.getPotions().getPotions().get(2));
        mainCharacter.getCharacter().addRelic(relics.getRelics().getRelics().get(0));
        mainCharacter.getCharacter().addRelic(relics.getRelics().getRelics().get(1));
        mainCharacter.getCharacter().addRelic(relics.getRelics().getRelics().get(2));
        updateDrawJson();
        characterHP.setText(initialCharHP + "/" + initialCharHP);
        characterImg.setImage(new Image(getClass().getResourceAsStream("../../" + mainCharacter.getCharacter().getImage())));
        characterEnergy.setText(mainCharacter.getCharacter().getEnergy() + "/" + mainCharacter.getCharacter().getEnergy());
        Methods.mainChar = mainCharacter.getCharacter();
    }

    /**
     * Getting cards from character's card list and showing them on combat screen
     * @param character
     */
    private void addCards(ParseCharacterJSONObjects character) {
        for(int i = 0; i < 5 - cards.size(); i++) {
            Card card = character.getCharacter().getCardsOfPlayer().getCardList().get(i);
            character.getCharacter().getCardsOfPlayer().getDrawList().remove(i);
            character.getCharacter().getCardsOfPlayer().getCardList().remove(i);
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
        characterHP.setText((mainCharacter.getCharacter().getHp() + "/")+ initialCharHP );
        topBar.getGold().setText("" + mainCharacter.getCharacter().getGold());
        characterHP.setText((mainCharacter.getCharacter().getHp() + "/")+ initialCharHP );
        updatePotions();
    }


    /**
     * This method is going to execute when player clicks the cards.
     * Monsters hp will change based on card's damage property.
     * @param card represents the card which user played
     * @throws IOException
     */
    // TODO: update if case by adding relics, potions damage addition
    private void playCard(Card card) throws IOException {
        if(card.getDamage() < monster.getMonsters().getMonsters().get(2).getHp()) {
            mainCharacter.getCharacter().getCardsOfPlayer().addDiscardList(card);
            updateDiscardJson();
            int updatedHP = monster.getMonsters().getMonsters().get(2).getHp() - card.getDamage();
            updatedHP -= mainCharacter.getCharacter().getRelicsOfPlayer().getRelics().get(0).getAttackBoost();
            updatedHP -= charAttackBoost;
            monster.getMonsters().getMonsters().get(2).setHp(updatedHP);
            charDefence += card.getDefence();
        }
        else {
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
            int newCharacterHP = mainCharacter.getCharacter().getHp() - damage + charDefence;
            mainCharacter.getCharacter().setHp(newCharacterHP);
            characterEnergy.setText(mainCharacter.getCharacter().getEnergy() + "/" + mainCharacter.getCharacter().getEnergy());
            topBar.getHp().setText(newCharacterHP + "/" + initialCharHP);
            topBar.getGold().setText("" + mainCharacter.getCharacter().getGold());
            exposeCards();
            refreshScreen();

            if(mainCharacter.getCharacter().getHp() <= 0){
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
            Cards charCards =  mainCharacter.getCharacter().getCardsOfPlayer();

            for(int i = 0; i < cards.size(); i++){
                charCards.addDiscardList(cards.get(i));
            }
            updateDiscardJson();
            cards.clear();
            cardBox.getChildren().clear();

            if(charCards.getCardList().size() < 5){
                for(int i = 0; i < charCards.getDiscardList().size(); i++){
                    mainCharacter.getCharacter().addCard(charCards.getDiscardList().get(i));
                    mainCharacter.getCharacter().getCardsOfPlayer().getDrawList().add(charCards.getDiscardList().get(i));
                }
                charCards.getDiscardList().clear();
                updateDiscardJson();
            }

            addCards(mainCharacter);
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

        relicBar = new RelicBar(mainCharacter);
        topBar.getHp().setText(mainCharacter.getCharacter().getHp() + "/" + mainCharacter.getCharacter().getHp());
        topBar.getGold().setText(mainCharacter.getCharacter().getGold() + "");
        relicBar = new RelicBar(mainCharacter);
        topBar.getHp().setText(mainCharacter.getCharacter().getHp() + "/" + mainCharacter.getCharacter().getHp());

        bars.getChildren().add(topBar.getTopBar());
        bars.getChildren().add(relicBar.getRelicBar());

        AnchorPane.getChildren().add(bars);

        updatePotions();
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
            if ( mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size() > 0 ){
                usePotion(1);
                updatePotions();
            }
        });
        topBar.getPotionButton2().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size() > 1 ){
                usePotion(2);
                updatePotions();
            }
        });
        topBar.getPotionButton3().onMouseClickedProperty().set((MouseEvent t) -> {
            if ( mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size() > 2 ){
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
        for(Card card: mainCharacter.getCharacter().getCardsOfPlayer().getDiscardList()){
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
        for(Card card: mainCharacter.getCharacter().getCardsOfPlayer().getDrawList()){
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
        return mainCharacter.getCharacter().getCardsOfPlayer().getCardList();
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
        int characterPotionNumber = mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().size();
        if ( characterPotionNumber == 0 ){
            topBar.setPotionImage1("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText1("No Potion");
            topBar.setPotionImage2("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText2("No Potion");
            topBar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText3("No Potion");
        }
        else if ( characterPotionNumber == 1 ) {
            topBar.setPotionImage1(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            topBar.setToolTipText1(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getToolTipString());
            topBar.setPotionImage2("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText2("No Potion");
            topBar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText3("No Potion");
        }
        else if ( characterPotionNumber == 2 ){
            topBar.setPotionImage1(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            topBar.setToolTipText1(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getToolTipString());
            topBar.setPotionImage2(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getImage());
            topBar.setToolTipText2(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getToolTipString());
            topBar.setPotionImage3("IMAGES/PotionImages/NoPotion.png");
            topBar.setToolTipText3("No Potion");
        }
        else if ( characterPotionNumber == 3 ){
            topBar.setPotionImage1(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getImage());
            topBar.setToolTipText1(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(0).getToolTipString());
            topBar.setPotionImage2(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getImage());
            topBar.setToolTipText2(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(1).getToolTipString());
            topBar.setPotionImage3(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(2).getImage());
            topBar.setToolTipText3(mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(2).getToolTipString());
        }
    }

    void usePotion(int index){
        Potion potionToUse = mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().get(index - 1);
        mainCharacter.getCharacter().setHp(mainCharacter.getCharacter().getHp() + potionToUse.getHpBoost());
        charAttackBoost += potionToUse.getAttackBoost();
        charDefence += potionToUse.getDefenceBoost();
        mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().remove(index - 1);
        refreshScreen();
    }
}