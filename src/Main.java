import java.util.Scanner;

public class Main {

    public Card [] deck;
    public int deckPosition;

    public static void main(String[] args) {
        Main blackjack = new Main();
    }

    public Main() {
        Scanner input = new Scanner(System.in);

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

        Card playerCard1 = dealCard();
        playerCard1.isUp = true;

        Card playerCard2 = dealCard();
        playerCard2.isUp = true;

        player.addCard(playerCard1);
        player.addCard(playerCard2);

        Card dealerCard1 = dealCard();
        dealerCard1.isUp = true;

        Card dealerCard2 = dealCard();
        dealerCard2.isUp = false;

        dealer.addCard(dealerCard1);
        dealer.addCard(dealerCard2);

        System.out.println("Player Hand:");
        player.printHand();
        System.out.println("Total: " + player.getTotal());

        System.out.println();

        System.out.println("Dealer Hand:");
        dealer.printHand();

        boolean playing = true;

        while (playing) {
            System.out.println();
            System.out.println("Type Hit or Stay");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("hit")) {

                Card newCard = dealCard();
                newCard.isUp = true;

                player.addCard(newCard);

                System.out.println("Player Hand:");
                player.printHand();
                System.out.println("Total: " + player.getTotal());

                if (player.getTotal() > 21) {
                    System.out.println("Bust! Dealer wins.");
                    return;
                }
            } else if (choice.equalsIgnoreCase("stay")) {
                playing = false;
            }
        }

        dealer.hand[1].isUp = true;

        System.out.println();
        System.out.println("Dealer reveals:");
        dealer.printHand();
        System.out.println("Total: " + dealer.getTotal());

        while (dealer.getTotal() < 17) {
            Card newCard = dealCard();
            newCard.isUp = true;
            dealer.addCard(newCard);

            System.out.println("Dealer hits:");
            dealer.printHand();
            System.out.println("Total: " + dealer.getTotal());
        }

        if (dealer.getTotal() > 21) {
            System.out.println("Dealer Busts. Player Wins!");
            return;
        }

        if (player.getTotal() > dealer.getTotal()) {
            System.out.println("Player Wins!");
        } else if (dealer.getTotal() > player.getTotal()) {
            System.out.println("Dealer wins");
        } else {
            System.out.println("Push");
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
