package Components.Potion;

public class Potion {
    private int attackBoost;
    private int defendBoost;
    private int hpBoost;
    private String image;

    public Potion(int attackBoost, int defendBoost, int hpBoost, String image) {
        this.attackBoost = attackBoost;
        this.defendBoost = defendBoost;
        this.hpBoost = hpBoost;
        this.image = image;
    }

    public Potion() {
        this.attackBoost = 0;
        this.defendBoost = 0;
        this.hpBoost = 0;
    }
    public Potion(Potion otherPotion){
        this.attackBoost = otherPotion.attackBoost;
        this.defendBoost = otherPotion.defendBoost;
        this.hpBoost = otherPotion.hpBoost;
        this.image = otherPotion.image;
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    public void setAttackBoost(int attackBoost) {
        this.attackBoost = attackBoost;
    }

    public int getDefendBoost() {
        return defendBoost;
    }

    public void setDefendBoost(int defendBoost) {
        this.defendBoost = defendBoost;
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

    @Override
    public String toString() {
        return "Potion{" +
                "attackBoost=" + attackBoost +
                ", defendBoost=" + defendBoost +
                ", hpBoost=" + hpBoost +
                '}';
    }
}