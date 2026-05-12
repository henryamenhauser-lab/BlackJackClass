public class Player {

    public Card[] hand;
    public int numCards;

    public Player() {
        hand = new Card[12];
        numCards = 0;

    }

    public void addCard(Card newCard) {
        hand[numCards] = newCard;
        numCards++;
    }

    public void printHand() {
        for (int i = 0; i < numCards; i++) {
            hand[i].printCard();
        }
    }

    public int getTotal() {
        int total = 0;
        for (int i= 0; i < numCards; i++){
            total += hand[i].geBlackjackValue();
        }
        return total;
    }

}
