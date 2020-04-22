package sample.settings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;


public class Settings {

    @FXML
    Slider volumeSlider;

    public void volumeSlided(MouseEvent mouseEvent){
        System.out.println("volume slider: " + volumeSlider.getValue());
    }

    @FXML
    RadioButton mute;

    public void muteClicked(MouseEvent mouseEvent){
        System.out.println("mute is selected: " + mute.isSelected());
    }

    @FXML
    Button quit;

    public void quitClicked(MouseEvent mouseEvent){
        System.out.println("quit is pressed: " + !(quit.isPressed()));

    }


}
