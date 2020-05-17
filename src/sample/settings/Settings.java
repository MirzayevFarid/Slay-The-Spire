package sample.settings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.play.Play;
import sun.audio.AudioPlayer;


public class Settings {
    private boolean muted = false;

    @FXML
    Slider volumeSlider;

    public void volumeSlided(MouseEvent mouseEvent){
        System.out.println("volume slider: " + volumeSlider.getValue());
    }

    @FXML
    RadioButton mute;

    public void muteClicked(MouseEvent mouseEvent){
        System.out.println("mute is selected: " + mute.isSelected());
        if(muted) {
            AudioPlayer.player.start(Play.getMusic());
            muted = false;
        }
        else{
            AudioPlayer.player.stop(Play.getMusic());
            muted = true;
        }

    }

    @FXML
    Button quit;

    public void quitClicked(MouseEvent mouseEvent){
        System.out.println("quit is pressed: " + !(quit.isPressed()));
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();


    }


}
