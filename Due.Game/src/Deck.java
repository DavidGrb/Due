import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    public static void main(String[] args) {
        //test
        Deck test = new Deck(3);
        System.out.println(test);
    }

    List<Card> deckCards = new ArrayList<>();
    String[] colors = {"Red", "Blue", "Green", "Yellow"};

    public Deck(int cards) {
        //füllt ein deck mit zufällig generierten karten
        for (int i = 0; i < cards; i++) {
            int rndNum = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
            int rndColor = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0);

            deckCards.add(new Card(colors[rndColor], rndNum));

        }
    }


    @Override
    public String toString() {
        return "Deck{" +
                "deckCards=" + deckCards +
                ", " +
        '}';
    }
}
