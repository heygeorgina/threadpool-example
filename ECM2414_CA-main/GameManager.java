import java.util.ArrayList;

public class GameManager {
    public static volatile boolean IfWin = false;

    public static ArrayList<Card> pack = new ArrayList<>();
    public static ArrayList<Player> PlayerList = new ArrayList<>();
    public static ArrayList<Deck> DeckList = new ArrayList<>();

    public static void initialPack(){ //read the input file and fill the pack list

    }

    public static void initialAllocate(int n){
        try{
            for(int i=0;i<pack.size();i++){
                int ModResult = i % n;
                PlayerList.get(ModResult).addHand(pack.get(i));
            }
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

}
