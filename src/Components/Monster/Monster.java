package Components.Monster;
import java.util.concurrent.ThreadLocalRandom;

public class Monster {
    private int attackPoint;
    private int defencePoint;
    private int hp;
    private int level;


    public Monster() {
        this.attackPoint = 0;
        this.defencePoint = 0;
        this.hp = 0;
        this.level = 0;
    }
    public Monster(Monster otherMonster) {
        this.attackPoint = otherMonster.attackPoint;
        this.defencePoint = otherMonster.defencePoint;
        this.hp = otherMonster.hp;
        this.level = otherMonster.level;
    }
    public void initialMonster() {

        this.level = level;

        if(level == 1){
            this.attackPoint = ThreadLocalRandom.current().nextInt(2, 5 + 1);
            this.defencePoint = ThreadLocalRandom.current().nextInt(2, 5 + 1);
            this.hp = ThreadLocalRandom.current().nextInt(50, 100 + 1);
        }
        else if(level == 2){
            this.attackPoint = ThreadLocalRandom.current().nextInt(5, 10 + 1);
            this.defencePoint = ThreadLocalRandom.current().nextInt(5, 10 + 1);
            this.hp = ThreadLocalRandom.current().nextInt(120, 200 + 1);
        }
        else if(level == 3){
            this.attackPoint = ThreadLocalRandom.current().nextInt(10, 15 + 1);
            this.defencePoint = ThreadLocalRandom.current().nextInt(10, 15 + 1);
            this.hp = ThreadLocalRandom.current().nextInt(220, 300 + 1);

        }
        else if(level == 4){
            this.attackPoint = ThreadLocalRandom.current().nextInt(15, 20 + 1);
            this.defencePoint = ThreadLocalRandom.current().nextInt(15, 20 + 1);
            this.hp = ThreadLocalRandom.current().nextInt(320, 400 + 1);

        }
        else if(level == 5){
            this.attackPoint = ThreadLocalRandom.current().nextInt(20, 25 + 1);
            this.defencePoint = ThreadLocalRandom.current().nextInt(20, 25 + 1);
            this.hp = ThreadLocalRandom.current().nextInt(420, 500 + 1);
        }
    }
    public void attack(){
        if(level == 1){
            this.attackPoint = ThreadLocalRandom.current().nextInt(2, 5 + 1);
        }
        else if(level == 2){
            this.attackPoint = ThreadLocalRandom.current().nextInt(5, 10 + 1);
        }
        else if(level == 3){
            this.attackPoint = ThreadLocalRandom.current().nextInt(10, 15 + 1);
        }
        else if(level == 4){
            this.attackPoint = ThreadLocalRandom.current().nextInt(15, 20 + 1);
        }
        else if(level == 5){
            this.attackPoint = ThreadLocalRandom.current().nextInt(20, 25 + 1);
        }
    }
    public void defence(){
        if(level == 1){
            this.defencePoint = ThreadLocalRandom.current().nextInt(2, 5 + 1);
        }
        else if(level == 2){
            this.defencePoint = ThreadLocalRandom.current().nextInt(5, 10 + 1);
        }
        else if(level == 3){
            this.defencePoint = ThreadLocalRandom.current().nextInt(10, 15 + 1);
        }
        else if(level == 4){
            this.defencePoint = ThreadLocalRandom.current().nextInt(15, 20 + 1);
        }
        else if(level == 5){
            this.defencePoint = ThreadLocalRandom.current().nextInt(20, 25 + 1);
        }
    }
    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefencePoint() {
        return defencePoint;
    }

    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "attackPoint=" + attackPoint +
                ", defencePoint=" + defencePoint +
                ", hp=" + hp +
                ", level=" + level +
                '}';
    }
}
