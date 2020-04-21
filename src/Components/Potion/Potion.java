package Components.Potion;

public class Potion {
    //Properties of the Card Class       Author: Okan Alp Unver
    private int attackBoost;
    private int defendBoost;
    private int hpBoost;

    //Methods of the Card Class         Author: Okan Alp Unver

    public Potion(int attackBoost, int defendBoost, int hpBoost) {
        this.attackBoost = attackBoost;
        this.defendBoost = defendBoost;
        this.hpBoost = hpBoost;
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
    }
    public int getAttackBoost() {
        return attackBoost;
    }

    public int getDefendBoost() {
        return defendBoost;
    }

    public int getHpBoost() {
        return hpBoost;
    }

    public void setAttackBoost(int attackBoost) {
        this.attackBoost = attackBoost;
    }

    public void setDefendBoost(int defendBoost) {
        this.defendBoost = defendBoost;
    }

    public void setHpBoost(int hpBoost) {
        this.hpBoost = hpBoost;
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