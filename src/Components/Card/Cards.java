package sample.play.Card;

import java.util.ArrayList;

public class Cards {
    private ArrayList<Card> cardList = new ArrayList<Card>();

    public Cards(ArrayList<Card> cardList){
        for ( int i = 0 ; i < cardList.size() ; i++ )
            this.cardList.add(cardList.get(i));
    }

    public Cards(Cards cards) {
        this.cardList = cards.cardList;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    @Override
    public String toString() {
        String output = "";
        for ( int i = 0 ; i < cardList.size() ; i++ ) {
            output += "Cards{" +
                    "cardList=" + cardList.get(i).toString() +
                    '}' + "\n";
        }
        return output;
    }
}
