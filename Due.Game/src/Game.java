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

    }

    public void pickUpCard(Deck deck){
        deck.deckCards.add(new Deck(1).deckCards.get(0));
    }

    public



}
