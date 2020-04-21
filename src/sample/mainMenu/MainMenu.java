package sample.mainMenu;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Methods;

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
        Methods.changeScreen("play/play.fxml", txtPlay);
    }

    public void compendiumClicked(MouseEvent mouseEvent) throws IOException {
        Methods.changeScreen("map/map.fxml", txtCompendium);
    }

    public void settingsClicked(MouseEvent mouseEvent) throws IOException {
        Methods.changeScreen("settings/settings.fxml", txtSettings);
    }

    public void quitClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) txtQuit.getScene().getWindow();
        stage.close();
    }
}
