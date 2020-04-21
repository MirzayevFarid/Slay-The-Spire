package Components.Relic;

import java.util.ArrayList;

public class Relics {
    private ArrayList<Relic> relics = new ArrayList<Relic>();

    public Relics(ArrayList<Relic> relics) {
        for ( int i = 0 ; i < relics.size() ; i++ )
            this.relics.add(relics.get(i));
    }

    public Relics(Relics relics) {
        for ( int i = 0 ; i < relics.getRelics().size() ; i++ )
            this.relics.add(relics.getRelics().get(i));
    }

    public ArrayList<Relic> getRelics() {
        return relics;
    }

    @Override
    public String toString() {
        String output = "";
        for ( int i = 0 ; i < relics.size() ; i++ ) {
            output += "Cards{" +
                    "cardList=" + relics.get(i).toString() +
                    '}' + "\n";
        }
        return output;
    }
}
