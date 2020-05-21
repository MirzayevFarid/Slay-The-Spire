package Components.Card;

public class Card {

    //Properties of the Card Class       Author: Nihat Bartu Serttas
    private String name;
    private String character;
    private int energyCost;
    private int value;
    private int damage;
    private int defence;
    private String description;
    private String image;

    //Methods of the Card Class         Author: Nihat Bartu Serttas

    //Empty Constructor

    public Card() {
        this.name = "";
        this.character = "";
        this.energyCost = 0;
        this.value = 0;
        this.damage = 0;
        this.defence = 0;
        this.description = "";
        this.image = "";
    }

    //Default Constructor

    public Card(String name, String character, int energyCost, int value, int damage, int defence, String description, String image) {
        this.name = name;
        this.character = character;
        this.energyCost = energyCost;
        this.value = value;
        this.damage = damage;
        this.defence = defence;
        this.description = description;
        this.image = image;
    }

    //Copy Constructor

    public Card(Card existingCard){
        this.name = existingCard.name;
        this.character = existingCard.character;
        this.energyCost = existingCard.energyCost;
        this.value = existingCard.value;
        this.damage = existingCard.damage;
        this.defence = existingCard.defence;
        this.description = existingCard.description;
        this.image = existingCard.image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterType() {
        return character;
    }

    public void setCharacterType(String characterType) {
        this.character = characterType;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name=" + name + '\'' +
                "characterType=" + character +
                ", energyCost=" + energyCost +
                " value=" + value +
                ", damage=" + damage +
                ", defence=" + defence +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}' + "\n";
    }
}
