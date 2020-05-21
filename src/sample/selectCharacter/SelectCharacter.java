package sample.selectCharacter;

import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sample.Constants;
import sample.Methods;

public class SelectCharacter {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private ImageView imageView;

    @FXML
    private Button backBtn;

    @FXML
    private HBox Hbox;

    @FXML
    private Button selectBtn;




    public void initialize(){
        addButtons();
    }

    private void addButtons() {
        ImageView ironcladButton = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/ironcladButton.png")));
        ImageView defectButton = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/defectButton.png")));
        ImageView watcherButton = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/watcherButton.png")));


        imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/ironcladPortrait.png")));

        Button ironcladChar = new Button();
        ironcladChar.setGraphic(ironcladButton);
        ironcladChar.setStyle("-fx-background-color: transparent;");

        ironcladButton.onMouseEnteredProperty().set((MouseEvent t) -> {
            imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/ironcladPortrait.png")));
        });


        Button defectChar = new Button();
        defectChar.setGraphic(defectButton);
        defectChar.setStyle("-fx-background-color: transparent;");

        defectButton.onMouseEnteredProperty().set((MouseEvent t) -> {
            imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/defectPortrait.png")));
        });


        Button watcherChar = new Button();
        watcherChar.setGraphic(watcherButton);
        watcherChar.setStyle("-fx-background-color: transparent;");

        watcherButton.onMouseEnteredProperty().set((MouseEvent t) -> {
            imageView.setImage(new Image(getClass().getResourceAsStream("../../Images/play/watcherPortrait.png")));
        });



        ironcladButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Constants.mainCharacter = new ParseCharacterJSONObjects("Ironclad");
                Methods.changeScreen("play/play.fxml", backBtn,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        defectChar.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Constants.mainCharacter = new ParseCharacterJSONObjects("Defect");
                Methods.changeScreen("play/play.fxml", backBtn,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        watcherChar.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Constants.mainCharacter = new ParseCharacterJSONObjects("Watcher");
                Methods.changeScreen("play/play.fxml", backBtn,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Hbox.getChildren().addAll(ironcladButton, defectButton, watcherButton);


    }


}

