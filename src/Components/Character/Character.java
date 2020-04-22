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
    private Cards cardsOfPlayer = new Cards(new ArrayList<>());
    private Relics relicsOfPlayer = new Relics(new ArrayList<>());
    private Potions potionsOfPlayer = new Potions(new ArrayList<>());

    //Constructors

    public Character() {
        this.name = "";
        this.energy = 0;
    }

    public Character(String name, int energy, int hp) {
        this.name = name;
        this.energy = energy;
        this.hp = hp;
    }

    public Character(String name, int energy, int hp, Cards cardsOfPlayer, Relics relicsOfPlayer, Potions potionsOfPlayer) {
        this.name = name;
        this.energy = energy;
        this.hp = hp;
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
        return "Character{" + "\n" +
                "name='" + name + "\n" + '\'' +
                ", energy=" + energy + "\n" +
                ", hp=" + hp + "\n" +
                ", cardsOfPlayer=" + cardsOfPlayer.toString() + "\n" +
                ", relicsOfPlayer=" + relicsOfPlayer.toString() + "\n" +
                ", potionsOfPlayer=" + potionsOfPlayer.toString() + "\n" +
                '}' + "\n";
    }
}
