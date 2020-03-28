package sample.mainMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {

    @FXML
    private Text btnPlay;

    @FXML
    private Text btnCompendium;

    @FXML
    private Text btnSettings;

    @FXML
    private Text btnQuit;


    public void playClicked(MouseEvent mouseEvent) throws IOException {
        changeScreen("../map/map.fxml");
    }

    public void compendiumClicked(MouseEvent mouseEvent) throws IOException {
        changeScreen("../compendium/compendium.fxml");
    }

    public void settingsClicked(MouseEvent mouseEvent) throws IOException {
        changeScreen("../settings/settings.fxml");
    }

    public void quitClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }

    public void changeScreen(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
