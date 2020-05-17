package Components.Card;

import java.util.ArrayList;

public class Cards {
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private ArrayList<Card> discardList = new ArrayList<Card>();
    private ArrayList<Card> drawList = new ArrayList<Card>();

    public Cards(ArrayList<Card> cardList){
        for ( int i = 0 ; i < cardList.size() ; i++ ) {
            this.cardList.add(cardList.get(i));
            this.drawList.add(cardList.get(i));
        }
    }

    public Cards(Cards cards) {
        this.cardList = cards.cardList;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public ArrayList<Card> getDiscardList() {
        return discardList;
    }


    public ArrayList<Card> getDrawList() {
        return drawList;
    }

    public void addDiscardList(Card card) {
        discardList.add(card);
    }

    public void addDrawList(Card card) {
        drawList.add(card);
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
