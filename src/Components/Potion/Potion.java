package Components.Potion;

public class Potion {
    private String name;
    private int attackBoost;
    private int defenceBoost;
    private int hpBoost;
    private String image;

    public Potion(String name, int attackBoost, int defenceBoost, int hpBoost, String image) {
        this.name = name;
        this.attackBoost = attackBoost;
        this.defenceBoost = defenceBoost;
        this.hpBoost = hpBoost;
        this.image = image;
    }

    public Potion() {
        this.name = "";
        this.attackBoost = 0;
        this.defenceBoost = 0;
        this.hpBoost = 0;
    }
    public Potion(Potion otherPotion){
        this.name = otherPotion.name;
        this.attackBoost = otherPotion.attackBoost;
        this.defenceBoost = otherPotion.defenceBoost;
        this.hpBoost = otherPotion.hpBoost;
        this.image = otherPotion.image;
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    public void setAttackBoost(int attackBoost) {
        this.attackBoost = attackBoost;
    }

    public int getDefenceBoost() {
        return defenceBoost;
    }

    public void setDefenceBoost(int defenceBoost) {
        this.defenceBoost = defenceBoost;
    }

    public int getHpBoost() {
        return hpBoost;
    }

    public void setHpBoost(int hpBoost) {
        this.hpBoost = hpBoost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToolTipString() {
        return
                ("Name: " + name + "\n" +
                        "Attack Boost: " + attackBoost + "\n" +
                        "Defence Boost: " + defenceBoost + "\n" +
                        "HP Boost: " + hpBoost);
    }

    @Override
    public String toString() {
        return "Potion{" +
                "attackBoost=" + attackBoost +
                ", defendBoost=" + defenceBoost +
                ", hpBoost=" + hpBoost +
                '}';
    }
}