package sample.mainMenu;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Methods;
import sample.play.Play;

import java.io.IOException;

public class MainMenu {

    @FXML
    private Text txtPlay;

    @FXML
    private Text txtCompendium;

    @FXML
    private Text txtSettings;

    @FXML
    private Text txtQuit;


    public void playClicked(MouseEvent mouseEvent) throws IOException {
        Methods.changeScreen("selectCharacter/selectCharacter.fxml", txtPlay, true);
    }

    public void compendiumClicked(MouseEvent mouseEvent) throws IOException {
        Methods.changeScreen("compendium/compendium.fxml", txtCompendium, false);
    }

    public void settingsClicked(MouseEvent mouseEvent) throws IOException {
        Methods.changeScreen("settings/settings.fxml", txtSettings,false);
    }

    public void quitClicked(MouseEvent mouseEvent) {
        Play.player.stop();
        Stage stage = (Stage) txtQuit.getScene().getWindow();
        stage.close();
    }
}
