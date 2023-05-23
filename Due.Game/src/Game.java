public class Game {
    public static void main(String[] args) {

    }
    Card currentCard = new Deck(1).deckCards.get(0);
    public static void gameLoop() {

        Deck p1 = new Deck(7);
        Deck p2 = new Deck(7);

        while (true) {

        }
    }


    public boolean canThrowCard(Card card){
        if(card.getColor().equals(currentCard.getColor()) || card.getNumber()== currentCard.getNumber()){
            return true;
        }else{
            return false;
        }
    }

    public void throwCard(Deck deck, Card card){
        currentCard = card;
        deck.deckCards.remove(card);
    }

    public void pickUpCard(Deck deck){
        deck.deckCards.add(new Deck(1).deckCards.get(0));
    }

    public void throwAvailableCard(Deck deck){
        for (int i = 0; i < deck.deckCards.size(); i++) {
            if(canThrowCard(deck.deckCards.get(i))){
                throwCard(deck,deck.deckCards.get(i));
            }
        }

    }



}
