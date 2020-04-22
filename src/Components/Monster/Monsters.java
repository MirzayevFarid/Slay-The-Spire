package Components.Monster;
import Components.Potion.Potion;
import Components.Potion.Potions;

import java.util.ArrayList;
public class Monsters {
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    public Monsters(ArrayList<Monster> monsters) {
        for ( int i = 0 ; i < monsters.size() ; i++ )
            this.monsters.add(monsters.get(i));
    }
    public Monsters(Monsters otherMonster) {
        for ( int i = 0 ; i < otherMonster.monsters.size() ; i++ )
            this.monsters.add(otherMonster.getMonsters().get(i));
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < monsters.size(); i++) {
            output += "Monsters{" +
                    "monsters=" + monsters.get(i).toString() +
                    '}' + "\n";
        }
        return output;
    }
}
