package sample.compendium;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Methods;

import java.io.IOException;

public class Compendium {

    @FXML
    Button ironcladCardsButton;

    @FXML
    Button defectCardsButton;

    @FXML
    Button watcherCardsButton;

    @FXML
    Button relicsButton;

    @FXML
    Button potionsButton;

    public void initialize(){
        addListeners();
    }

    private void addListeners(){
        ironcladCardsButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("compendium/Ironclad/cardView.fxml", ironcladCardsButton,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        defectCardsButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("compendium/Defect/cardView.fxml", defectCardsButton,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        watcherCardsButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("compendium/Watcher/cardView.fxml", watcherCardsButton,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        potionsButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("compendium/Potions/potionView.fxml", potionsButton,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        relicsButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("compendium/Relics/relicView.fxml", relicsButton,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
