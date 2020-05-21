package sample.selectCharacter;

import Components.Card.ParseCardJSONObjects;
import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Relic.ParseRelicJSONObjects;
import Components.RelicBar;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.Constants;
import sample.Methods;

import java.io.IOException;

public class SelectCharacter {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private ImageView imageView;

    @FXML
    private StackPane stackPane;

    @FXML
    private HBox Hbox;

    @FXML
    private VBox Vbox;

    ImageView ironcladButtonImage = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/ironcladButton.png")));
    ImageView defectButtonImage = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/defectButton.png")));
    ImageView watcherButtonImage = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/watcherButton.png")));
    ImageView quitButtonImage = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/quit.png"),200,100,false, false));


    public void initialize(){
        addCharButtons();
    }

    private void addCharButtons() {

        // Back Button
        Button backBtn = new Button("Back");
        Text text = new Text("Back");
        backBtn.setGraphic(quitButtonImage);
        backBtn.setStyle("-fx-background-color: transparent;");
        text.setId("Back");

        stackPane.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("mainMenu/mainMenu.fxml", backBtn, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stackPane.getChildren().addAll(backBtn, text);
        imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/ironcladPortrait.png")));


        addListeners();
        Hbox.getChildren().addAll(ironcladButtonImage, defectButtonImage, watcherButtonImage);
    }


    private void updateInfo(ParseCharacterJSONObjects newChar, String desc, int index) throws Exception {
        Vbox.getChildren().clear();
        Text charName = new Text(newChar.getCharacter().getName());
        charName.setId("Name");

        // HBOX
        HBox charValues = new HBox(10);

        ImageView heartIcon = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/heart.png")));
        Text charHP = new Text("HP: " + newChar.getCharacter().getHp());
        charHP.setId("HP");
        HBox.setMargin(charHP,new Insets(15,5,0,0));

        ImageView goldIcon = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/gold.png")));
        Text charGOLD = new Text("Gold: " + newChar.getCharacter().getGold());
        charGOLD.setId("Gold");
        HBox.setMargin(charGOLD,new Insets(15,5,0,0));


        charValues.getChildren().addAll(heartIcon,charHP, goldIcon, charGOLD);

        Text charDesc  = new Text(desc);
        charDesc.setId("Desc");


        // HBOX
        HBox charRelics = new HBox(5);
        ParseRelicJSONObjects newRelic = new ParseRelicJSONObjects();
        newChar.getCharacter().addRelic(newRelic.getRelics().getRelics().get(index));
        RelicBar relicBar = new RelicBar(newChar);
        Text charRelic = new Text("" + newChar.getCharacter().getRelicsOfPlayer().getRelics().get(index).getName());
        charRelic.setId("Relic");
        HBox.setMargin(charRelic,new Insets(15,0,0,0));
        charRelics.getChildren().addAll(relicBar.getRelicBar(), charRelic);

        Vbox.getChildren().addAll(charName, charValues, charDesc,charRelics);
    }


    private void addListeners() {
        ironcladButtonImage.onMouseEnteredProperty().set((MouseEvent t) -> {
            try {
                ParseCharacterJSONObjects newChar = new ParseCharacterJSONObjects("Ironclad");

                updateInfo(newChar,"The remaining soldier of the Ironclads.\nSold his soul to harness demonic energies", 0);

                imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/ironcladPortrait.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ironcladButtonImage.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Constants.mainCharacter = new ParseCharacterJSONObjects("Ironclad");
                Constants.usableCards = new ParseCardJSONObjects("Ironclad");
                Methods.changeScreen("play/play.fxml", quitButtonImage,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        defectButtonImage.onMouseEnteredProperty().set((MouseEvent t) -> {
            try {
                ParseCharacterJSONObjects newChar = new ParseCharacterJSONObjects("Defect");

                updateInfo(newChar,"The remaining soldier of the Ironclads.\nSold his soul to harness demonic energies", 0);

                imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/defectPortrait.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        defectButtonImage.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Constants.mainCharacter = new ParseCharacterJSONObjects("Defect");
                Constants.usableCards = new ParseCardJSONObjects("Defect");
                Methods.changeScreen("play/play.fxml", quitButtonImage,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        watcherButtonImage.onMouseEnteredProperty().set((MouseEvent t) -> {
            try {
                ParseCharacterJSONObjects newChar = new ParseCharacterJSONObjects("Watcher");

                updateInfo(newChar,"The remaining soldier of the Ironclads.\nSold his soul to harness demonic energies", 0);

                imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/watcherPortrait.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        watcherButtonImage.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Constants.mainCharacter = new ParseCharacterJSONObjects("Watcher");
                Constants.usableCards = new ParseCardJSONObjects("Watcher");
                Methods.changeScreen("play/play.fxml", quitButtonImage,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Button ironcladChar = new Button();
        ironcladChar.setGraphic(ironcladButtonImage);
        ironcladChar.setStyle("-fx-background-color: transparent;");

        Button defectChar = new Button();
        defectChar.setGraphic(defectButtonImage);
        defectChar.setStyle("-fx-background-color: transparent;");

        Button watcherChar = new Button();
        watcherChar.setGraphic(watcherButtonImage);
        watcherChar.setStyle("-fx-background-color: transparent;");
    }



}

