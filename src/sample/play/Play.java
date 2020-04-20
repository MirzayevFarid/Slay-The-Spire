package sample.play;

import Components.TopBar;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Play {
    @FXML
    AnchorPane AnchorPane;

    public void initialize(){
        AnchorPane.getChildren().add(new TopBar().backgroundImage);
    }
}
