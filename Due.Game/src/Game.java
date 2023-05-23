import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE


    public static void main(String[] args) {
        Game game = new Game();
        game.gameLoop();
    }

    public Card currentCard = new Deck(1).deckCards.get(0);

    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mit wie vielen Karten willst du spielen?");
        int quantitty = scanner.nextInt();

        Deck p1 = new Deck(quantitty);
        Deck p2 = new Deck(quantitty);


        while (p1.deckCards.size() > 0) {
            System.out.println("\n" + "Karten von Spieler:\n" + p1 + "\n");

            System.out.println(PURPLE_BOLD_BRIGHT + "Oberste Karte" + RESET + "\n" + currentCard + "\n");
            System.out.println("Willst du eine Karte legen (l) oder abheben(a)?");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("a")) {
                pickUpCard(p1);
            } else if (choice.equalsIgnoreCase("l")) {
                if (availableDeckCard(p1)) {
                    System.out.println("Welche Karte willst du legen?");
                    int numOfCard = scanner.nextInt();
                    while (numOfCard < 1 && numOfCard > p1.deckCards.size() || (canThrowCard(p1.deckCards.get(numOfCard - 1)) == false)) {
                        System.out.println("Ungültige Zahl oder Karte! Gib neue Karte an!");
                        numOfCard = scanner.nextInt();
                    }
                    throwCard(p1, p1.deckCards.get(numOfCard - 1));
                } else {
                    System.out.println("\n" + RED + "Du hast keine verfügbaren Karten zum spielen! Heb eine Karte ab!" + RESET + "\n");
                }
            }
        }
        if (p1.deckCards.size() == 0) {
            System.out.println("Spieler 1 hat gewonnen!");
        } else {
            System.out.println("Computer hat gewonnen!");
        }
    }

    public boolean canThrowCard(Card card) {
        if (card.getColor().equals(currentCard.getColor()) || card.getNumber() == currentCard.getNumber()) {
            return true;
        } else {
            return false;
        }
    }

    public void throwCard(Deck deck, Card card) {
        currentCard = card;
        deck.deckCards.remove(card);
    }

    public void pickUpCard(Deck deck) {
        deck.deckCards.add(new Deck(1).deckCards.get(0));
    }

    public void throwAvailableCard(Deck deck) {
        for (int i = 0; i < deck.deckCards.size(); i++) {
            if (canThrowCard(deck.deckCards.get(i))) {
                throwCard(deck, deck.deckCards.get(i));
            }
        }

    }


    public boolean availableDeckCard(Deck deck) {
        for (int i = 0; i < deck.deckCards.size(); i++) {
            if (canThrowCard(deck.deckCards.get(i))) {
                return true;
            }

        }
        return false;
    }
}
