package sample.settings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.play.Play;



public class Settings {
    private boolean muted = false;

    @FXML
    Slider volumeSlider;

    @FXML
    RadioButton mute;

    @FXML
    Button quit;

    public void volumeSlided(MouseEvent mouseEvent){
        System.out.println("volume slider: " + volumeSlider.getValue());
        int level = (int)((volumeSlider.getValue()) / 10);
        switch (level) {
            case 0:
                Play.player.setVolume(0.05);
                System.out.println(Play.player.getVolume());
                break;
            case 1:
                Play.player.setVolume(0.1);
                System.out.println(Play.player.getVolume());
                break;
            case 2:
                Play.player.setVolume(0.2);
                System.out.println(Play.player.getVolume());
                break;
            case 3:
                Play.player.setVolume(0.3);
                System.out.println(Play.player.getVolume());
                break;
            case 4:
                Play.player.setVolume(0.4);
                System.out.println(Play.player.getVolume());
                break;
            case 5:
                Play.player.setVolume(0.5);
                System.out.println(Play.player.getVolume());
                break;
            case 6:
                Play.player.setVolume(0.6);
                System.out.println(Play.player.getVolume());
                break;
            case 7:
                Play.player.setVolume(0.7);
                System.out.println(Play.player.getVolume());
                break;
            case 8:
                Play.player.setVolume(0.8);
                System.out.println(Play.player.getVolume());
                break;
            case 9:
                Play.player.setVolume(0.9);
                System.out.println(Play.player.getVolume());
                break;
            case 10:
                Play.player.setVolume(1);
                System.out.println(Play.player.getVolume());
        }
        mute.setSelected(false);
        Play.player.play();
    }

    public void muteClicked(MouseEvent mouseEvent){
        System.out.println("mute is selected: " + mute.isSelected());
        if(muted) {
            // AudioPlayer.player.start(Play.getMusic());
            Play.player.play();
            muted = false;
            mute.setSelected(false);
        }
        else{
            //AudioPlayer.player.stop(Play.getMusic());
            Play.player.pause();
            muted = true;
            mute.setSelected(true);
        }
    }

    public void quitClicked(MouseEvent mouseEvent){
        System.out.println("quit is pressed: " + !(quit.isPressed()));
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }
}
