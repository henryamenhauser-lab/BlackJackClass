import java.util.Scanner;

public class Main {

    public Card [] deck;
    public int deckPosition;

    public static void main(String[] args) {
        Main blackjack = new Main();
    }

    public Main() {
        Scanner input = new Scanner(System.in);

        System.out.println("How Many Players? (1-4)");
        int numPlayers = input.nextInt();
        input.nextLine();

        if (numPlayers > 4) numPlayers =4;
        if (numPlayers < 1) numPlayers =1;


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

        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player();
        }

        Player dealer = new Player();

        for (int i = 0; i < numPlayers; i++) {

            Card c1 = dealCard();
            c1.isUp = true;

            Card c2 = dealCard();
            c2.isUp = true;

            players[i].addCard(c1);
            players[i].addCard(c2);
        }

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + " Hand:");
            players[i].printHand();
            System.out.println("Total: " + players[i].getTotal());
            System.out.println();
        }

        Card dealerC1 = dealCard();
        dealerC1.isUp = true;

        Card dealerC2 = dealCard();
        dealerC2.isUp = false;

        dealer.addCard(dealerC1);
        dealer.addCard(dealerC2);

        System.out.println("Dealer Hand:");
        dealer.printHand();
        System.out.println();


        for (int i = 0; i < numPlayers; i++) {

            boolean turn = true;

            while (turn) {

                System.out.println("Player " + (i + 1) + ": Hit or Stay?");


                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("hit")) {

                    Card newCard = dealCard();
                    newCard.isUp = true;
                    players[i].addCard(newCard);

                    System.out.println();
                    players[i].printHand();
                    System.out.println("Total: " + players[i].getTotal());

                    if (players[i].getTotal() > 21) {
                        System.out.println("Bust!");
                        turn = false;
                    }

                } else if (choice.equalsIgnoreCase("stay")) {
                    turn = false;
                }
            }
        }

        dealer.hand[1].isUp = true;

        System.out.println();
        System.out.println("Dealer reveals:");
        System.out.println();
        dealer.printHand();
        System.out.println("Total: " + dealer.getTotal());

        while (dealer.getTotal() < 17) {
            Card newCard = dealCard();
            newCard.isUp = true;
            dealer.addCard(newCard);
            System.out.println();
            System.out.println("Dealer hits:");
            dealer.printHand();
            System.out.println("Total: " + dealer.getTotal());
        }

        if (dealer.getTotal() > 21) {
            System.out.println("Dealer Busts. Player Wins!");
            return;
        }

        System.out.println();
        System.out.println("Results:");
        System.out.println("Dealer: " + dealer.getTotal());

        for (int i = 0; i < numPlayers; i++) {

            int pTotal = players[i].getTotal();
            int dTotal = dealer.getTotal();

            System.out.println("Player " + (i + 1) + ": " + pTotal);

            if (pTotal > dTotal && pTotal <= 21) System.out.println("Player Wins!");
            if (dTotal > pTotal && dTotal <= 21) System.out.println("Dealer Wins!");
            if (pTotal == dTotal) System.out.println("Push!");
        }
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
