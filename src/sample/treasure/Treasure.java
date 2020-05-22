package sample.treasure;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sample.Constants;
import sample.Methods;

import java.io.IOException;

public class Treasure {

    @FXML
    private ImageView imageView;

    @FXML
    private Text infoText;

    @FXML
    private Button continueButton;


    public void openChest(MouseEvent mouseEvent) {

        continueButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("map/map.fxml",continueButton,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // define the range
        int rand = (int)(Math.random() * 4) + 1;
        rand = 1;

        switch (rand) {
            case 1:
                imageView.setImage(new Image(getClass().getResourceAsStream("../../" + Constants.usableCards.getCardDeck().getCardList().get(0).getImage())));
                infoText.setText("You got a card with " + Constants.usableCards.getCardDeck().getCardList().get(0).getDamage() + " damage, " + Constants.usableCards.getCardDeck().getCardList().get(0).getDefence() + " defence");
                Methods.removeCardFromShop(0);
                break;
            case 2:
                imageView.setImage(new Image(getClass().getResourceAsStream("../../" + Constants.allPotions.getPotions().getPotions().get(0).getImage())));
                infoText.setText("You got a potion with " + Constants.allPotions.getPotions().getPotions().get(0).getAttackBoost() + " attack boost, " + Constants.allPotions.getPotions().getPotions().get(0).getDefenceBoost() + " defence boost");
                Methods.removePotionFromShop(0);
                break;
            case 3:
                imageView.setImage(new Image(getClass().getResourceAsStream("../../" + Constants.allRelics.getRelics().getRelics().get(0).getImage())));
                infoText.setText("You got a relic with " + Constants.allRelics.getRelics().getRelics().get(0).getAttackBoost() + " attack boost, " + Constants.allRelics.getRelics().getRelics().get(0).getDefenceBoost() + " defence boost");
                Methods.removeRelicFromShop(0);
                break;
            case 4:
                imageView.setImage(new Image(getClass().getResourceAsStream("../../IMAGES/play/earnGold.png")));
                infoText.setText("You got 150 golds");
                Methods.addGold(150);
                break;
        }
    }
}
