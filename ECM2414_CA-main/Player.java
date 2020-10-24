import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Player implements Runnable {
    private int ID;
    private ArrayList<Card> hand;
    File PlayerAction;

    public Player(int ID) throws IOException {
        this.ID = ID;
        this.hand = new ArrayList<>();
        this.PlayerAction = new File("Player "+this.ID+" Action.txt");
        this.PlayerAction.createNewFile();
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addHand(Card card) {
        this.hand.add(card);
    }

    public void removeHand(int index) throws IndexOutOfBoundsException{
        this.hand.remove(index);
    }

    public void drawAndDiscard(int n) throws IOException {
        Deck d1;
        Deck d2;
        if(this.ID == n){
            d1 = GameManager.DeckList.get(n);
            d2 = GameManager.DeckList.get(0);
        }else{
            d1 = GameManager.DeckList.get(this.ID);
            d2 = GameManager.DeckList.get(this.ID+1);
        }

        // draw card from d1
        synchronized(d1){
            this.addHand((Card) d1.getCards().peek());
            this.outputPlayerDraw((Card) d1.getCards().remove(),d1);
        }

        // discard card to d2
        synchronized (d2){
            Random r = new Random();
            int index = r.nextInt(this.hand.size());
            d2.getCards().add(this.hand.get(index));
            this.outputPlayerDiscard(this.hand.get(index),d2);
            this.removeHand(index);

        }
    }

    public void outputPlayerDraw(Card card,Deck deck) throws IOException {
        FileWriter fw = new FileWriter(this.PlayerAction);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Player "+this.ID+" draws a "+ card.getValue() + " from Deck " + deck.getID() + ".");
    }

    public void outputPlayerDiscard(Card card, Deck deck) throws IOException{
        FileWriter fw = new FileWriter(this.PlayerAction);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Player "+this.ID+" discards a "+ card.getValue() + " to Deck " + deck.getID() + ".");
    }

    public void outputDeckState() throws IOException { //可能有错，纠正就交给黄姐了 haode
        for(int i=0;i<GameManager.DeckList.size();i++){
            StringBuffer output = new StringBuffer();
            for(int j=0;j<=GameManager.DeckList.get(i).getCards().size();j++){
                Card OutputCard = (Card)GameManager.DeckList.get(i).getCards().remove();
                output.append(OutputCard.getValue());
                output.append(",");
            }

            File FinalState = new File("Deck " + GameManager.DeckList.get(i).getID() + " final state.txt");
            FinalState.createNewFile();
            FileWriter fw = new FileWriter(FinalState);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Deck " + GameManager.DeckList.get(i).getID() + "'s final state: " + output);
        }

    }

    public void run(){
        //check if the player wins
        while (!GameManager.IfWin &&(hand.get(0).getValue() != hand.get(1).getValue() || hand.get(1).getValue() != hand.get(2).getValue()
                || hand.get(2).getValue() != hand.get(3).getValue())){
            //try{
                //drawAndDiscard(还需要个n); get n from main method
            //}catch(IOException e){
                //e.printStackTrace();
            //}

        }
        //add code to notify other threads
        GameManager.IfWin = true;
        try {
            outputDeckState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
