import java.util.*;
import java.util.Queue;

public class Deck {
    private Queue<Card> cards;
    private int ID;

    public Deck(int ID){
        this.ID = ID;
        this.cards = new LinkedList<>() ;
    }

    public int getID() {
        return ID;
    }

    public Queue getCards(){
        return this.cards;
    }

    public void addCards(Card card) {
        this.cards.add(card);
    }

    public void removeCards(){
        this.cards.remove();
    }
}
