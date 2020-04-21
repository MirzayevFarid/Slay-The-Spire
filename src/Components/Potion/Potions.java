package Components.Potion;


import java.util.ArrayList;
public class Potions {
    private ArrayList<Potion> potions = new ArrayList<Potion>();

    public Potions(ArrayList<Potion> potions) {
        for ( int i = 0 ; i < potions.size() ; i++ )
            this.potions.add(potions.get(i));
    }
    public Potions(Potions otherPotions) {
        for ( int i = 0 ; i < otherPotions.potions.size() ; i++ )
            this.potions.add(otherPotions.getPotions().get(i));
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public void setPotions(ArrayList<Potion> potions) {
        this.potions = potions;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < potions.size(); i++) {
            output += "Potions{" +
                    "potions=" + potions.get(i).toString() +
                    '}' + "\n";
        }
        return output;
    }
}
