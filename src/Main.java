public class Main {

    public Card [] deck;
    public int deckPosition;

    public static void main(String[] args) {
        Main blackjack = new Main();
    }

    public Main() {
        deck = new Card[52];

        for (int i =0; i<deck.length; i++) {
            if (i<13) {
                deck[i] = new Card(i % 13,"Spades",false);
            } else if (i<26) {
                deck[i] = new Card(i % 13,"Clubs",false);
            } else if (i<39) {
                deck[i] = new Card(i % 13,"Diamonds",false);
            } else if (i<52) {
                deck[i] = new Card(i % 13,"Hearts",false);
            }
        }

        shuffle();
        deckPosition = 0;

        Player player = new Player();
        Player dealer = new Player();

        player.addCard(dealCard());
        player.addCard(dealCard());

        dealer.addCard(dealCard());

        System.out.println("Player Hand:");
        player.printHand();
        System.out.println("Total: " + player.getTotal());

        System.out.println();

        System.out.println("Dealer Hand:");
        dealer.printHand();
        System.out.println("Total: " + dealer.getTotal());



    }

    public void printDeck() {
        System.out.println("Deck:");
        for (int i = 0; i < deck.length; i++) {
            deck[i].printCard();
        }
    }

    public void shuffle() {
        for (int i = 0; i< deck.length; i++) {
            int randNum = (int)(Math.random()*52);
            Card holdCard = deck[randNum];
            deck[randNum] = deck[i];
            deck[i] = holdCard;
        }
    }

    public Card dealCard(){
        Card nextCard = deck[deckPosition];
        deckPosition++;
        return nextCard;
    }

}
