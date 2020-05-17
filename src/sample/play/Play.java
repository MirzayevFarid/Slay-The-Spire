package sample.play;

import Components.Card.Card;
import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Monster.ParseMonsterJSONObjects;
import Components.TopBar;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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

    @FXML
    public void endTurn(){
        int damage = monster.getMonsters().getMonsters().get(2).getAttackPoint();
        int newCharacterHP = character.getCharacter().getHp() - damage;
        character.getCharacter().setHp(newCharacterHP);
        characterEnergy.setText(character.getCharacter().getEnergy() + "/" + character.getCharacter().getEnergy());
        refreshScreen();
    }



    ImageView drawCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/drawImage.png")));
    ImageView discardCard = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/discardImage.png")));
    ImageView endTurnImg = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/endTurnImage.png"),150,150,false,false));
    ParseMonsterJSONObjects monster;
    ParseCharacterJSONObjects character;
    ArrayList<Card> cards = new ArrayList<>();
    static InputStream file;
    static AudioStream musicc;


    {
        try {
            monster = new ParseMonsterJSONObjects();
            character = new ParseCharacterJSONObjects("Ironclad");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static AudioStream getMusic() {
        return musicc;
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
        saveCharacterData();
        characterHP.setText(String.valueOf(character.getCharacter().getHp()));
        characterImg.setImage(new Image(getClass().getResourceAsStream("../../" + character.getCharacter().getImage())));
        characterEnergy.setText(character.getCharacter().getEnergy() + "/" + character.getCharacter().getEnergy());
    }

    private void addCards(ParseCharacterJSONObjects character) {
        for(int i = 0; i<= 4; i++) {
            Card card = character.getCharacter().getCardsOfPlayer().getCardList().get(i);
            character.getCharacter().getCardsOfPlayer().getCardList().remove(i);
            cards.add(card);
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
    }

    private void playCard(Card card) throws IOException {
            character.getCharacter().getCardsOfPlayer().addDiscardList(card);
            updateDiscardJson();
            int updatedHP = monster.getMonsters().getMonsters().get(2).getHp() - card.getDamage();
            monster.getMonsters().getMonsters().get(2).setHp(updatedHP);
    }


    private void addListeners() {
        drawButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("play/drawPile/DrawPile.fxml", drawButton);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        discardButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("play/discardPile/DiscardPile.fxml", discardButton);
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


    private void updateDiscardJson() throws IOException {
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

    void saveCharacterData() throws IOException {
        int index = 0;
        FileWriter writer = null;
        File file = new File("src/sample/play/drawPile/JSONFiles");
        deleteFolder(file);
        for(Card card: character.getCharacter().getCardsOfPlayer().getCardList()){
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

    public static void music() throws IOException {
        file = new FileInputStream("C:\\Users\\Burcu\\Documents\\1A-SS\\src\\sample\\play\\irmakAski.wav");
        musicc = new AudioStream(file);
        AudioPlayer.player.start(musicc);



    }

}