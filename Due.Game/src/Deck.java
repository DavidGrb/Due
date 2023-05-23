import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RESET = "\033[0m";  // Text Reset

    List<Card> deckCards = new ArrayList<>();
    String[] colors = {RED + "Red" + RESET, BLUE + "Blue" + RESET, GREEN + "Green" + RESET, YELLOW + "Yellow" + RESET};

    public Deck(int cards) {
        //füllt ein deck mit zufällig generierten karten
        for (int i = 0; i < cards; i++) {
            int rndNum = (int) Math.floor(Math.random() * (9 + 1));
            int rndColor = (int) Math.floor(Math.random() * (3 + 1));
            deckCards.add(new Card(colors[rndColor], rndNum));
        }
    }


    @Override
    public String toString() {
        return deckCards + "";
    }
}
