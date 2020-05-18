package Components.Relic;

public class Relic {

    // Properties
    private String name;
    private int attackBoost;
    private int defenceBoost;
    private int hpBoost;
    private int energyBoost;
    private String image;

    public Relic(String name) {
        this.name = name;
        this.image = "";
        this.attackBoost = 0;
        this.defenceBoost = 0;
        this.hpBoost = 0;
        this.energyBoost = 0;
    }

    public Relic(String name, int attackBoost, int defenceBoost, int hpBoost, int energyBoost, String image) {
        this.name = name;
        this.attackBoost = attackBoost;
        this.defenceBoost = defenceBoost;
        this.hpBoost = hpBoost;
        this.energyBoost = energyBoost;
        this.image = image;
    }

    public Relic(String name, Relic relic, String image){
        this.name = name;
        this.attackBoost = relic.attackBoost;
        this.defenceBoost = relic.defenceBoost;
        this.hpBoost = relic.hpBoost;
        this.energyBoost = relic.energyBoost;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getEnergyBoost() {
        return energyBoost;
    }

    public void setEnergyBoost(int energyBoost) {
        this.energyBoost = energyBoost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Relic{" +
                "name='" + name + '\'' +
                ", attackBoost=" + attackBoost +
                ", defenceBoost=" + defenceBoost +
                ", hpBoost=" + hpBoost +
                ", energyBoost=" + energyBoost +
                ", image=" + image +
                '}';
    }
}
