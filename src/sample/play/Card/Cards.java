package sample.play.Card;

import java.util.ArrayList;

public class Cards {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Cards(ArrayList<Card> cardList){
        for ( int i = 0 ; i < cardList.size() ; i++ )
            this.cards.add(cardList.get(i));
    }

    public Cards(Cards cards) {
        for ( int i = 0 ; i < cards.getCards().size() ; i++ )
            this.cards.add(cards.getCards().get(i));
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < cards.size() ; i++ ) {
            output += "Cards{" +
                    "cardList=" + cards.get(i).toString() +
                    '}' + "\n";
        }
        return output;
    }
}
