package sample.rest;

import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Monster.ParseMonsterJSONObjects;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rest {
    @FXML
    ImageView background;
    ParseCharacterJSONObjects character;

    {
        try {
            character = new ParseCharacterJSONObjects("Ironclad");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        background.setImage(new Image(getClass().getResourceAsStream("../../IMAGES/play/rest background.jpg")));
        background.setPreserveRatio(false);
        background.setFitWidth(1440);
        background.setFitHeight(900);
        System.out.println(character.getCharacter().getHp());
        int hpToBeAdded = (int) ((character.getCharacter().getHp() * 15) / 100);
        int fullHp = character.getCharacter().getHp() + hpToBeAdded;
        character.getCharacter().setHp(fullHp);
        System.out.println(character.getCharacter().getHp());
    }
}
