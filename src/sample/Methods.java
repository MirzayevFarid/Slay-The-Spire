package sample;

import Components.Card.Card;
import Components.Card.ParseCardJSONObjects;
import Components.Character.Character;
import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Monster.ParseMonsterJSONObjects;
import Components.Potion.ParsePotionJSONObjects;
import Components.Potion.Potion;
import Components.Relic.ParseRelicJSONObjects;
import Components.Relic.Relic;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Methods {

    public static Character mainChar;


    public static Character getMainCharacter() {
        return Constants.mainCharacter.getCharacter();
    }

    public void addCard(Card card){
        Constants.mainCharacter.getCharacter().getCardsOfPlayer().getCardList().add(card);
    }

    public void removeCard(int index){
        Constants.mainCharacter.getCharacter().getCardsOfPlayer().getCardList().remove(index);
    }

    public void addRelic(Relic relic){
        Constants.mainCharacter.getCharacter().getRelicsOfPlayer().getRelics().add(relic);
    }

    public void removeRelic(int index){
        Constants.mainCharacter.getCharacter().getRelicsOfPlayer().getRelics().remove(index);
    }

    public void addPotion(Potion potion){
        Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().add(potion);
    }

    public void removePotion(int index){
        Constants.mainCharacter.getCharacter().getPotionsOfPlayer().getPotions().remove(index);
    }

    public void addHP(int hp){
        Constants.mainCharacter.getCharacter().setHp(Constants.mainCharacter.getCharacter().getHp() + hp);
    }

    public void decreaseHP(int hp){
        Constants.mainCharacter.getCharacter().setHp(Constants.mainCharacter.getCharacter().getHp() - hp);
    }

    public void addEnergy(int energy){
        Constants.mainCharacter.getCharacter().setEnergy(Constants.mainCharacter.getCharacter().getEnergy() + energy);
    }

    public void decreaseEnergy(int energy){
        Constants.mainCharacter.getCharacter().setEnergy(Constants.mainCharacter.getCharacter().getEnergy() - energy);
    }

    public void addGold(int gold){
        Constants.mainCharacter.getCharacter().setGold(Constants.mainCharacter.getCharacter().getGold() + gold);
    }

    public void decreaseGold(int gold){
        Constants.mainCharacter.getCharacter().setGold(Constants.mainCharacter.getCharacter().getGold() - gold);
    }

    public void removeCardFromShop(int index){
        Card card = new Card(Constants.usableCards.getCardDeck().getCardList().get(index));
        Constants.usableCards.getCardDeck().getCardList().remove(index);
        addCard(card);
    }

    public void removeRelicFromShop(int index){
        Relic relic = new Relic(Constants.allRelics.getRelics().getRelics().get(index));
        Constants.allRelics.getRelics().getRelics().remove(index);
        addRelic(relic);
    }

    public void removePotionFromShop(int index){
        Potion potion = new Potion(Constants.allPotions.getPotions().getPotions().get(index));
        Constants.allPotions.getPotions().getPotions().remove(index);
        addPotion(potion);
    }

    public static void changeScreen(String path, Node node, boolean hide) throws IOException {
        FXMLLoader loader = new FXMLLoader(Methods.class.getResource(path));
        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        if(hide)
            node.getScene().getWindow().hide();
        stage.setResizable(false);
        stage.show();
    }
}
