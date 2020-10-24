import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CardGame {

    public static void readPad(String value, int n) throws IOException {

        FileReader a = new FileReader(value);
        BufferedReader b = new BufferedReader(a);

        for (int c = 0; c < 8 * n; c++) {
            Card k = new Card(Integer.parseInt(b.readLine()));
            GameManager.pack.add(k);
        }

    }


    public static void generatePlayerAndDecks(int n) {
       for (int player = 0; player < n; n++) {

           try { new Thread(new Player(player)).start(); }
           catch (IOException e) {e.printStackTrace();}
           new Deck (player); ！

       }

    }
    //🐷老师冲鸭！！
    //🐷老师冲鸭！！
    //🐷老师冲鸭！！
    //🐷老师冲鸭！！
    //🐷老师冲鸭！！
    //🐷老师冲鸭！！

    public static void main(String args[]) {
    }
}


