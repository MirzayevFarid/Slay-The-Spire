package Components.Character;

import Components.Card.Card;
import Components.Card.Cards;
import Components.Potion.Potion;
import Components.Potion.Potions;
import Components.Relic.Relic;
import Components.Relic.Relics;

import java.util.ArrayList;

public class Character {
    //Properties
    private String name;
    private int energy;
    private int hp;
    private int gold;
    private String image;

    private Cards cardsOfPlayer = new Cards(new ArrayList<>());
    private Relics relicsOfPlayer = new Relics(new ArrayList<>());
    private Potions potionsOfPlayer = new Potions(new ArrayList<>());

    //Constructors

    public Character() {
        this.name = "";
        this.energy = 0;
        this.hp = 0;
        this.gold = 0;
        this.image = "";
    }

    public Character(String name, int energy, int hp, int gold, String image) {
        this.name = name;
        this.energy = energy;
        this.hp = hp;
        this.gold = gold;
        this.image = image;
    }

    public Character(String name, int energy, int hp, int gold, String image, Cards cardsOfPlayer, Relics relicsOfPlayer, Potions potionsOfPlayer) {
        this.name = name;
        this.energy = energy;
        this.hp = hp;
        this.gold = gold;
        this.image = image;
        for(Card card: cardsOfPlayer.getCardList())
            this.cardsOfPlayer.getCardList().add(card);
        for(Relic relic: relicsOfPlayer.getRelics())
            this.relicsOfPlayer.getRelics().add(relic);
        for(Potion potion: potionsOfPlayer.getPotions())
            this.potionsOfPlayer.getPotions().add(potion);
    }

    public Character(Character character) {
        this.name = character.name;
        this.energy = character.energy;
        this.hp = character.hp;
        this.gold = character.gold;
        this.image = character.image;
        for(Card card: character.cardsOfPlayer.getCardList())
            this.cardsOfPlayer.getCardList().add(card);
        for(Relic relic: character.relicsOfPlayer.getRelics())
            this.relicsOfPlayer.getRelics().add(relic);
        for(Potion potion: character.potionsOfPlayer.getPotions())
            this.potionsOfPlayer.getPotions().add(potion);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Cards getCardsOfPlayer() {
        return cardsOfPlayer;
    }

    public void setCardsOfPlayer(Cards cardsOfPlayer) {
        this.cardsOfPlayer = cardsOfPlayer;
    }

    public Relics getRelicsOfPlayer() {
        return relicsOfPlayer;
    }

    public void setRelicsOfPlayer(Relics relicsOfPlayer) {
        this.relicsOfPlayer = relicsOfPlayer;
    }

    public Potions getPotionsOfPlayer() {
        return potionsOfPlayer;
    }

    public void setPotionsOfPlayer(Potions potionsOfPlayer) {
        this.potionsOfPlayer = potionsOfPlayer;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public void addCard(Card card){
        this.cardsOfPlayer.getCardList().add(card);
    }

    public void addRelic(Relic relic){
        this.relicsOfPlayer.getRelics().add(relic);
    }

    public void addPotion(Potion potion){
        this.potionsOfPlayer.getPotions().add(potion);
    }



    @Override
    public String toString() {
        return "Character Name: The " + name + "\n" + '\'' +
                "Starting Energy: " + energy + "\n" +
                "Starting Health Points: " + hp + "\n" +
                "Starting Relic: ";
    }
}
